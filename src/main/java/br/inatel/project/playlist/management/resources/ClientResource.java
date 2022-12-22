package br.inatel.project.playlist.management.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.inatel.project.playlist.management.domain.Client;
import br.inatel.project.playlist.management.dto.ClientDTO;
import br.inatel.project.playlist.management.repository.ClientRepository;
import br.inatel.project.playlist.management.service.ClientService;

@RestController
@RequestMapping("/users")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository repo;

	@Autowired
	private final PasswordEncoder encoder;

	public ClientResource(ClientService clientService, PasswordEncoder encoder) {
		this.clientService = clientService;
		this.encoder = encoder;
	}

	// find one user for id (GET)
	@GetMapping("/{id}")
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client obj = clientService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// find all registered User (GET)
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> list = clientService.findAllUser();
		List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// Insert a new Client (POST)
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody ClientDTO objDto) {
		objDto.setPassword(encoder.encode(objDto.getPassword()));// criptografa a senha
		Client obj = clientService.saveNewUser(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Method PUT - Change a customer's name and password by Client ID

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id) {
		objDto.setPassword(encoder.encode(objDto.getPassword()));// criptografa a senha
		Client obj = clientService.fromDTO(objDto);
		obj.setId(id);
		obj = clientService.update(obj);
		return ResponseEntity.noContent().build();
	}

	// Validate Password
	@GetMapping("/validatePassword")
	public ResponseEntity<Boolean> validatePassword(@RequestParam String email, @RequestParam String password) {

		Optional<Client> optClient = repo.findByEmail(email);
		if (optClient.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		Client client = optClient.get();
		boolean valid = encoder.matches(password, client.getPassword());//maches compara senha aberta com compactada

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);

	}
}
