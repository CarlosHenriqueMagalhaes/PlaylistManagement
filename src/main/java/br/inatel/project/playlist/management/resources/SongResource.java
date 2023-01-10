package br.inatel.project.playlist.management.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongResource {

	@Autowired
	private SongService songService;

	public SongResource(SongService songService) {
		super();
		this.songService = songService;
	}

	// find one Song by id (GET)
	@GetMapping("/{id}")
	public ResponseEntity<Song> find(@PathVariable Integer id) {
		Song obj = songService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// find all Songs in my bank (GET)
	@GetMapping
	public ResponseEntity<List<SongDTO>> findAll() {
		List<Song> list = songService.findAllSongs();
		List<SongDTO> listDTO = list.stream().map(obj -> new SongDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// add a song to a playlist (POST)
	@PostMapping("/addSong/{songId}")
	public ResponseEntity<Void> insert(@Valid @PathVariable("songId") Integer songId,
			@RequestBody PlaylistDTO playlistDTO) {
		songService.addSongToPlaylist(songId, playlistDTO);
		return ResponseEntity.accepted().build();
	}

	// Remove a song in a playlist
	@DeleteMapping("/removeSong")
	public ResponseEntity<?> delete(@RequestParam("playlistId") Integer playlistId,
			@RequestParam("songId") Integer songId) throws Exception {
		ResponseEntity.noContent().build();
		return ResponseEntity.ok(songService.removeSongToPlaylist(playlistId, songId));
	}

}
