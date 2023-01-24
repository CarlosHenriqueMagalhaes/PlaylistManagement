package br.inatel.project.playlist.management.service;

import java.util.List;
import java.util.Optional;

import br.inatel.project.playlist.management.exception.NullObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;
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
	@Autowired
	private PlaylistSongRepository plSgRepo;

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
			return repo.save(obj);
	}

	// Method PUT - Change a customer's Playlist name by ID
		public Playlist update(Playlist obj) {
			try {
				Playlist newObj = find(obj.getId());
				updateData(newObj, obj);
				return repo.save(newObj);
			} catch (Exception e) {
				throw new NullObjectNotFoundException("The playlist id:" + obj.getId() +" does not exist");
			}
		}

		// PUT helper method (allows changing Playlist name)
		private void updateData(Playlist newObj, Playlist obj) {
				newObj.setPlaylistName(obj.getPlaylistName());
		}

	// helper method that instantiates a playlist from a DTO (used in the POST and PUT)
	public Playlist fromDTO(PlaylistDTO objDto) {
		return new Playlist(objDto.getPlaylistId(), objDto.getPlaylistName());
	}

	// Delete a Playlist (DELETE)
	public String delete(Integer id) {
		find(id);
		plSgRepo.deleteAllInBatch();
		repo.deleteById(id);
		return ("The playlist id: " + id + " is successfully deleted");
	}
//A linha : plSgRepo.deleteAllInBatch(); garante que pela lista associativa eu removo as musicas da playlist antes
// de deleta-la, assim as musicas não são deletadas do banco de dados, e somente da playlist

	// saveAndFlush
	public Playlist saveAndFlush(Playlist playInsert) {
		return repo.saveAndFlush(playInsert);
	}
}
