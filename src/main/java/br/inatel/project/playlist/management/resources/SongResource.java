package br.inatel.project.playlist.management.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongResource {

	@Autowired
	private SongService playlistService;

	//busca uma playlist pelo id
	@GetMapping ("/{id}")
	public ResponseEntity<Song> find(@PathVariable Integer id) {
		Song obj = playlistService.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
