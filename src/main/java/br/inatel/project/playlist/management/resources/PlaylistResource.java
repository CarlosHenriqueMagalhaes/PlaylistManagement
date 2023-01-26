package br.inatel.project.playlist.management.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistResource {
	@Autowired
	private PlaylistService playlistService;

	// find one playlist for id (GET)
	@GetMapping
	public ResponseEntity<Playlist> find(@RequestParam Integer id) {
		Playlist obj = playlistService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// find all registered playlists (GET)
	@GetMapping("listAll")
	public ResponseEntity<List<PlaylistDTO>> findAll() {
		List<Playlist> list = playlistService.findAllPlaylist();
		List<PlaylistDTO> listDTO = list.stream().map(PlaylistDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// Insert a new Playlist (POST)
	@PostMapping
	public ResponseEntity<Playlist> insert(@Valid @RequestBody PlaylistDTO objDto) {
		Playlist obj = playlistService.fromDTO(objDto);
		obj = playlistService.insert(obj);

		return ResponseEntity.created(null).body(obj);
	}

	// Method PUT - Change a customer's name of playlist
	@PutMapping
	public ResponseEntity<Playlist> update(@Valid @RequestBody PlaylistDTO objDto)throws Exception {
		Playlist obj = playlistService.fromDTO(objDto);
		obj = playlistService.update(obj);
		return ResponseEntity.ok(obj);
		}
			
	// Delete a playlist
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		playlistService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
