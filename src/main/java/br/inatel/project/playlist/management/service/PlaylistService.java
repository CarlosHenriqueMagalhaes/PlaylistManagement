package br.inatel.project.playlist.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository repo;

	// find one playlist for id (GET)
	public Playlist find(Integer id) {
		Optional<Playlist> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"ObjectNotFound! This Playlist Id:" + id + ", does not exist! " + "Type: " + Playlist.class.getName()));
	}

	// find all registered playlists (GET)
	public List<Playlist> findAllPlaylist() {
		return repo.findAll();
	}

	// Insert a new Playlist (POST)
	public Playlist insert(Playlist obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	// add metodo de retornar erro caso o nome da playlist ja exista

	// helper method that instantiates a playlist from a DTO (used in the POST)
	public Playlist fromDTO(PlaylistDTO objDto) {
		return new Playlist(objDto.getId(), objDto.getPlaylistName());
	}

	// Delete a Playlist (DELETE)
	public String delete(Integer id) {
		find(id);
		repo.deleteById(id);
		return ("The playlist id: " + id + " is successfully deleted");
	}

	//saveAndFlush
	public Playlist saveAndFlush(Playlist playInsert) {
		return repo.saveAndFlush(playInsert);
	}
}
