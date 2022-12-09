package br.inatel.project.playlist.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository repo;

	//busca uma playlist pelo id Get
	public Playlist find(Integer id) {
		Optional<Playlist> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"ObjectNotFound! This Playlist Id:" + id + ", does not exist! " + "Type: " + Playlist.class.getName()));
	}
	
	//busca todas as playlists
	public List<Playlist> findAllPlaylist() {
		return repo.findAll();
	}

}
