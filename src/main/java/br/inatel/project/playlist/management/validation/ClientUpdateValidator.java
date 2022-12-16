package br.inatel.project.playlist.management.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.inatel.project.playlist.management.domain.Client;
import br.inatel.project.playlist.management.dto.ClientDTO;
import br.inatel.project.playlist.management.exception.FieldMessage;
import br.inatel.project.playlist.management.repository.ClientRepository;

//Classe Validador da Anotação:

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	//A função HttpServletRequest que permite obter o parâmetro da URI!
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository repo;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	// isValid é um método da ConstraintValidator, que verifica se nosso tipo (o
	// ClienteDTO) no caso se ele vai ser válido ou não!(ele retorna true
	//  ou false por isso ele é um boolean!
	
	// Uma lista vazia é instanciada de objetos do tipo FieldMessage (verifique essa
	// classe) que foi criada no resource exception para carregar o nome do 
	// campo e a mensagem de erro desse campo
	
	@Override
	public boolean isValid (ClientDTO objDto, ConstraintValidatorContext context) {
		//Pega um map de variaveis de URI que estão na requisição:
		@SuppressWarnings("unchecked")
		Map<String , String> map =(Map<String , String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		// O HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) pega um map de variáveis da URI que estão na requisiçao
		//resumindo o caminho que uso para acessar http://localhost:8099/clientes/2 ele pega a chave id que é o 2
		//nesse exemplo. Integer.parseInt converte para Inteiro
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista:
		
		//método para validação do e-mail ao usar update:
		
		//esse método serve para que se eu for alterar o email de um cliente, para um 
		//email que ja tenha outro cliente cadastrado, exiba um erro e não cadastra
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email" ,"already existing email"));
		}
		
		//método da classe

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
		
		// O método isValid retorna verdadeiro, porém se houver algum erro essa lista
		// não vai estar vazia e o meu pétodo vai retornar Falso!
		// O for: é para percorrer minha lista de FieldMessage e para cada objeto na
		// minha lista, eu vou adicionar um erro correspondente em minha lista de
		// erros do framework que são os comandos context.disableDefaultConstraintViolation
		// e context.buildConstraintViolationWithTemplate, então esses dois comandos me
		// permite transportar os meus erros personalizados para a lista de erros
		// do framework.Essa lista do framework é tratada e mostrada na classe
		// ResourceExceptionHandler no método MethodArgumentNotValidException
		
	}
}