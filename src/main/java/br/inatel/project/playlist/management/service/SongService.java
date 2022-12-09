package br.inatel.project.playlist.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	private SongRepository repo;

	//find one playlist for id (GET)
	public Song find(Integer id) {
		Optional<Song> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"ObjectNotFound! This Song Id:" + id + ", does not exist or is not registered! " + "Type: " + Song.class.getName()));

	}
}
