package br.inatel.project.playlist.management.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	@GetMapping("/{id}")
	public ResponseEntity<Playlist> find(@PathVariable Integer id) {
		Playlist obj = playlistService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// find all registered playlists (GET)
	@GetMapping
	public ResponseEntity<List<PlaylistDTO>> findAll() {
		List<Playlist> list = playlistService.findAllPlaylist();
		List<PlaylistDTO> listDTO = list.stream().map(PlaylistDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// Insert a new Playlist (POST)
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PlaylistDTO objDto) {
		Playlist obj = playlistService.fromDTO(objDto);
		obj = playlistService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Method PUT - Change a customer's name of playlist
	@PutMapping
	public ResponseEntity<Void> update(@Valid @RequestBody PlaylistDTO objDto)throws Exception {
		Playlist obj = playlistService.fromDTO(objDto);
		obj = playlistService.update(obj);
		return ResponseEntity.noContent().build();
		}
			
	// Delete a playlist
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		ResponseEntity.noContent().build();
		return ResponseEntity.ok(playlistService.delete(id));
	}

}
