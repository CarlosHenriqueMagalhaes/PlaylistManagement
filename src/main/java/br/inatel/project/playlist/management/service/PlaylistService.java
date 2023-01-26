package br.inatel.project.playlist.management.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.exception.NullObjectNotFoundException;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service class. Project logic implemented here.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository repo;
	@Autowired
	private PlaylistSongRepository plSgRepo;



	/**
	 * find one playlist for id (GET)
	 * @param id
	 * @return A Playlist
	 */
	public Playlist find(Integer id) {
		Optional<Playlist> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"ObjectNotFound! This Playlist Id:" + id + ", does not exist! " + "Type: " + Playlist.class.getName()));
	}

	/**
	 * find all registered playlists (GET)
	 * @return All Playlist
	 */
	public List<Playlist> findAllPlaylist() {
		return repo.findAll();
	}

	/**
	 * Insert a new Playlist (POST)
	 * @param obj
	 * @return Create a new Playlist
	 */
	public Playlist insert(Playlist obj) {
		obj.setId(null);
			return repo.save(obj);
	}

	/**
	 * Method PUT - Change a customer's Playlist name by ID
	 * @param obj
	 * @return Rename a PlaylistName
	 */
		public Playlist update(Playlist obj) {
			try {
				Playlist newObj = find(obj.getId());
				updateData(newObj, obj);
				return repo.save(newObj);
			} catch (Exception e) {
				throw new NullObjectNotFoundException("The playlist id:" + obj.getId() +" does not exist");
			}
		}


	/**
	 * PUT helper method (allows changing Playlist name)
	 * @param newObj
	 * @param obj
	 */
		private void updateData(Playlist newObj, Playlist obj) {
				newObj.setPlaylistName(obj.getPlaylistName());
		}

	/**
	 * helper method that instantiates a playlist from a DTO (used in the POST method)
	 * @param objDto
	 * @return objDto
	 */
	public Playlist fromDTO(PlaylistDTO objDto) {
		return new Playlist(objDto.getPlaylistId(), objDto.getPlaylistName());
	}

	/**
	 * Delete a Playlist (DELETE)
	 * @param id
	 * @return msg "The playlist id:-- is successfully deleted"
	 */
	public String delete(Integer id) {
		find(id);
		plSgRepo.deleteAllInBatch();
		repo.deleteById(id);
		return ("The playlist id: " + id + " is successfully deleted");
	}
//A linha : plSgRepo.deleteAllInBatch(); ensures that by the associative list I remove the songs from the playlist before
// to delete it, so the songs are not deleted from the database, and only from the playlist

	/**
	 * saveAndFlush
	 * @param playInsert
	 * @return Help to save Playlist In repository
	 */
	public Playlist saveAndFlush(Playlist playInsert) {
		return repo.saveAndFlush(playInsert);
	}
}
