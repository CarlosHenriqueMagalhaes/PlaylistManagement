package br.inatel.project.playlist.management.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.inatel.project.playlist.management.domain.Client;
import br.inatel.project.playlist.management.dto.ClientDTO;
import br.inatel.project.playlist.management.exception.FieldMessage;
import br.inatel.project.playlist.management.repository.ClientRepository;

//Annotation Validator Class:

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

	// The HttpServletRequest function that lets you get the URI parameter!
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClientRepository repo;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	// isValid is a method of ConstraintValidator, which checks whether our type (the
	// ClienteDTO) in case if it will be valid or not! (it returns true
	// ou false por isso ele é um boolean!

	// An empty list is instantiated of objects of type FieldMessage (check this class)
	// which was created in the resource exception to load the field name 
	// and the error message of that field

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		// Get a map of URI variables that are in the request:
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		// O HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) takes a map of URI variables
		// that are in the request summarizing the path
		// I use to access http://localhost:8099/clientes/2 it takes the key id which is 2 in this example. 
		// Integer.parseInt converts to Integer
		
		List<FieldMessage> list = new ArrayList<>();

		// method for email validation when using update:

		// this method is so that if I change the email of a customer, to an email that already
		// has another customer registered, it displays an error and does not register
		Optional<Client> clienteOptional = repo.findByEmail(objDto.getEmail());
		// Client aux = repo.findByEmail(objDto.getEmail());
		if (clienteOptional != null && clienteOptional.isPresent() && !clienteOptional.get().getId().equals(uriId)) {
			list.add(new FieldMessage("email", "already existing email"));
		}

		// class method

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

		// The isValid method returns true, however if there is an error this list will not be empty 
		// and my method will return False!
		
		// The for: is to loop through my FieldMessage list and for each object in my list,
		// I'm going to add a corresponding error in my framework error list which is 
		// context.disableDefaultConstraintViolation and
		// context.buildConstraintViolationWithTemplate,  so these two commands
		// get me allows transporting my custom errors to the framework's error list. 
		
		// This framework list is handled and shown in the ResourceExceptionHandler class
		// in the MethodArgumentNotValidException method

	}
}