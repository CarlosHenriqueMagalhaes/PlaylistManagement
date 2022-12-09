package br.inatel.project.playlist.management.service;

import java.util.List;
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

	// find one song by id (GET)

	public Song find(Integer id) {
		Optional<Song> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"ObjectNotFound! This Song Id:" + id + ", does not exist or is not registered! " + "Type: " + Song.class.getName()));
	}
	
//	Tentando lançar BadRequestIdException caso o id fornecido for zero
//	public Song find(Integer id) {
//		try {
//			Optional<Song> obj = repo.findById(id);
//			return obj.get();
//			
//		} catch (org.hibernate.ObjectNotFoundException e) {
//			throw new ObjectNotFoundException("ObjectNotFound! This Song Id:" + id
//					+ ", does not exist or is not registered! " + "Type: " + Song.class.getName());
//		} catch (BadRequest e) {
//			throw new BadRequestIdException("Use only numbers valid in ID");
//		}
//	}

	// find all Songs in my bank (GET)
	public List<Song> findAllSongs() {
		return repo.findAll();
	}
}
