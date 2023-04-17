package br.inatel.project.playlist.management.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.PlaylistManagerDTO;
import br.inatel.project.playlist.management.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller Playlist class. All endpoints of Playlist are built here.
 *
 * @author Carlos Magalhaes
 * @since 1.0
 */
@RestController
@RequestMapping
public class PlaylistResource {
	@Autowired
	private PlaylistService playlistService;

	/**
	 * find one playlist for id (GET)
	 * @return one playlist for id (endpoint)
	 */
	@GetMapping ("/playlist")
	public ResponseEntity<Playlist> find(@RequestParam Integer id) {
		Playlist playlist = playlistService.find(id);
		return ResponseEntity.ok().body(playlist);
	}

	/**
	 * find all registered playlists (GET)
	 * @return all registered playlists (endpoint)
	 */
	@GetMapping ("/playlists")
	public ResponseEntity<Page<Playlist>> findAll(
			@PageableDefault(sort= "id", direction= Sort.Direction.ASC, page = 0, size = 5)
			Pageable page) {
		return ResponseEntity.ok().body( playlistService.findAllPlaylistsPageable(page));
	}

	/**
	 * Insert a new Playlist (POST)
	 * @return a new Playlist (endpoint)
	 */
	@PostMapping ("/playlist")
	public ResponseEntity<Playlist> insert(@Valid @RequestBody PlaylistDTO playlistDTO) {
		Playlist playlist = playlistService.fromDTO(playlistDTO);
		playlist = playlistService.insert(playlist);
		return ResponseEntity.created(null).body(playlist);
	}

	/**
	 * Method Patch - Change a customer's name of playlist
	 * @return Change a customer's name of playlist (endpoint)
	 */
	@PatchMapping("playlist/{playlistId}")
	public ResponseEntity<Playlist> update(@Valid @PathVariable Integer playlistId,@Valid
	@RequestBody PlaylistManagerDTO playlistDTO)throws Exception {
		return ResponseEntity.ok(playlistService.update(playlistId, playlistDTO));
	}

	/**
	 * Delete a playlist
	 * @param id
	 * @return Delete a playlist (endpoint)
	 */
	@DeleteMapping("playlist/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		playlistService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
