package br.inatel.project.playlist.management.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.inatel.project.playlist.management.domain.Client;
import br.inatel.project.playlist.management.dto.ClientDTO;
import br.inatel.project.playlist.management.service.ClientService;

@RestController
@RequestMapping("/users")
public class ClientResource {

	@Autowired
	private ClientService clientService;

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

		Client obj = clientService.saveNewUser(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
