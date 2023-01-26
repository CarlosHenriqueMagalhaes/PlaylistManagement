package br.inatel.project.playlist.management.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.form.TrackForm;
import br.inatel.project.playlist.management.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongResource {

	@Autowired
	private SongService songService;

	@Autowired
	private PlaylistService playlistService;

	@GetMapping
	public ResponseEntity<Song> find(@RequestParam Integer id) {
		Song obj = songService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// find all Songs in my bank (GET)
	@GetMapping("/listAll")
	public ResponseEntity<List<SongDTO>> findAll() {
		List<Song> list = songService.findAllSongs();
		List<SongDTO> listDTO = list.stream().map(SongDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// add a song to a playlist (POST)
	@PostMapping("/addSongAtPlaylist")
	public ResponseEntity<?> insert(@Valid @RequestParam Integer songId,
									   @RequestParam Integer playlistId) {
		songService.addSongToPlaylist(songId, playlistId);
		Playlist pl = playlistService.find(playlistId);
		return ResponseEntity.ok().body(pl);
	}

	// Remove a song in a playlist
	@DeleteMapping("/removeSong")
	public ResponseEntity<?> delete(@RequestParam("playlistId") Integer playlistId,
									@RequestParam("songId") Integer songId) throws Exception {
		songService.removeSongToPlaylist(playlistId, songId);
		return ResponseEntity.noContent().build();
	}

	//POST que busca na API externa a musica e dados sobre ela persistindo a música na base de dados
	@PostMapping("/findSong")
	public ResponseEntity<?> getTrack (@RequestBody TrackForm form)throws Exception{
		TrackDTO trackDTO =  songService.getTrack(form);
		songService.addSongToBase(trackDTO);
		return ResponseEntity.ok(trackDTO);
	}
}