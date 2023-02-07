package br.inatel.project.playlist.management.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller Playlist class. All endpoints of Playlist are built here.
 *
 * @author Carlos Magalhaes
 * @since 1.0
 */
@RestController
@RequestMapping("/playlists")
public class PlaylistResource {
	@Autowired
	private PlaylistService playlistService;

	/**
	 * find one playlist for id (GET)
	 * @return one playlist for id (endpoint)
	 */
	@GetMapping ("/playlist")
	public ResponseEntity<Playlist> find(@RequestParam Integer id) {
		Playlist obj = playlistService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * find all registered playlists (GET)
	 * @return all registered playlists (endpoint)
	 */
	@GetMapping
	public ResponseEntity<List<PlaylistDTO>> findAll() {
		List<Playlist> list = playlistService.findAllPlaylist();
		List<PlaylistDTO> listDTO = list.stream().map(PlaylistDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	 /**
	 * Insert a new Playlist (POST)
	 * @return a new Playlist (endpoint)
	 */
	@PostMapping
	public ResponseEntity<Playlist> insert(@Valid @RequestBody PlaylistDTO objDto) {
		Playlist obj = playlistService.fromDTO(objDto);
		obj = playlistService.insert(obj);

		return ResponseEntity.created(null).body(obj);
	}

	/**
	 * Method Patch - Change a customer's name of playlist
	 * @return Change a customer's name of playlist (endpoint)
	 */
	@PatchMapping
	public ResponseEntity<Playlist> update(@Valid @RequestBody PlaylistDTO objDto)throws Exception {
		Playlist obj = playlistService.fromDTO(objDto);
		obj = playlistService.update(obj);
		return ResponseEntity.ok(obj);
		}

//	@PatchMapping("/{playlistId}")
//	@JsonView(View.Patch.class)
//	public ResponseEntity<?> update (@PathVariable Integer playlistId, @RequestBody String playlistName) throws Exception{
//		PlaylistDTO playDTO = new PlaylistDTO();
//		playDTO.setPlaylistId(playlistId);
//		playDTO.setPlaylistName(playlistName);
//		Playlist obj = playlistService.fromDTO(playDTO);
//		obj = playlistService.update(obj);
//		return ResponseEntity.ok(obj);
//	}

	/**
	 * Delete a playlist
	 * @param id
	 * @return Delete a playlist (endpoint)
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		playlistService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
