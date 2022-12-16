package br.inatel.project.playlist.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.Client;
import br.inatel.project.playlist.management.dto.ClientDTO;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	// find one user for id (GET)
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"ObjectNotFound! This User Id:" + id + ", does not exist! " + "Type: " + Client.class.getName()));
	}

	// find all registered users (GET)
	public List<Client> findAllUser() {
		return repo.findAll();
	}

	// Insert a new User (POST)
	public Client insert(Client obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Client saveNewUser(ClientDTO userDTO) {

		Client obj = fromDTO(userDTO);
		obj = insert(obj);
		return obj;

	}

	// helper method that instantiates a user from a DTO (used in the POST)
	public Client fromDTO(ClientDTO objDto) {
		return new Client(null, objDto.getName(), objDto.getEmail(), objDto.getPassword());
	}

	// saveAndFlush
	public Client saveAndFlush(Client userInsert) {
		return repo.saveAndFlush(userInsert);
	}
}
