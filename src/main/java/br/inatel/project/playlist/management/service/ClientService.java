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

	public ClientService(ClientRepository repo) {
		this.repo = repo;
	}

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
		return repo.save(obj);
	}

	public Client saveNewUser(ClientDTO userDTO) {

		Client obj = fromDTO(userDTO);
		obj.setId(null);// uso o set null aqui para ele adicionar o proximo id livre
		obj = insert(obj);
		return obj;

	}

	// saveAndFlush
	public Client saveAndFlush(Client userInsert) {
		return repo.saveAndFlush(userInsert);
	}

	// // Method PUT - Change a customer's name and password by Client ID
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// PUT helper method (allows changing user name and password)

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setPassword(obj.getPassword());
	}

	// helper method that instantiates a user from a DTO (used in the POST and in
	// the PUT)
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword());
	}

}
