package br.inatel.project.playlist.management.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistResource {

	@Autowired
	private PlaylistService playlistService;

	@GetMapping ("/{id}")
	public ResponseEntity<Playlist> find(@PathVariable Integer id) {
		Playlist obj = playlistService.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
