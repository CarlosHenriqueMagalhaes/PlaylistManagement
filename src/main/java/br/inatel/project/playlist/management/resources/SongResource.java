package br.inatel.project.playlist.management.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.form.TrackForm;
import br.inatel.project.playlist.management.service.PlaylistService;
import br.inatel.project.playlist.management.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller Song class. All endpoints of Song are built here.
 *
 * @author Carlos Magalhaes
 * @since 1.0
 */
@RestController
@RequestMapping
public class SongResource {
	@Autowired
	private SongService songService;
	@Autowired
	private PlaylistService playlistService;

	/**
	 * Find a song by id
	 * @param id
	 * @return A song (endpoint)
	 */
	@GetMapping ("/song")
	public ResponseEntity<Song> find(@RequestParam Integer id) {
		Song song = songService.find(id);
		return ResponseEntity.ok().body(song);
	}

	/**
	 * find all Songs in my bank (GET)
	 * @return all Songs in my bank (endpoint)
	 */
	@GetMapping ("/songs")
	public ResponseEntity<Page<Song>> findAll(
			@PageableDefault(sort= "id", direction= Sort.Direction.ASC, page = 0, size = 5)
			Pageable page) {
		return ResponseEntity.ok().body(songService.findAllSongsPageable(page));
	}

	/**
	 * add a song to a playlist (POST)
	 * @param songId
	 * @param playlistId
	 * @return add a song to a playlist (endpoint)
	 */
	@PostMapping("/song/{songId}/playlist/{playlistId}")
	public ResponseEntity<?> insert(@Valid @PathVariable  Integer songId,
									@PathVariable Integer playlistId) {
		songService.addSongToPlaylist(songId, playlistId);
		Playlist playlist = playlistService.find(playlistId);
		return ResponseEntity.ok().body(playlist);
	}

	/**
	 * Remove a song in a playlist
	 * @param playlistId
	 * @param songId
	 * @return Remove a song in a playlist (endpoint)
	 * @throws Exception
	 */
	@DeleteMapping("/song/{songId}/playlist/{playlistId}")
	public ResponseEntity<Void> delete(@PathVariable Integer playlistId,
									@PathVariable Integer songId) throws Exception {
		songService.removeSongToPlaylist(playlistId, songId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * POST that searches the external API for music and data about it, persisting the music in the database
	 * @param form
	 * @return A Playlist
	 * @throws Exception
	 */
	@PostMapping("/newSong")
	public ResponseEntity<?> getTrack (@RequestBody TrackForm form)throws Exception{
		TrackDTO trackDTO =  songService.getTrack(form);
		songService.addSongToBase(trackDTO);
		return ResponseEntity.created(null).body(trackDTO);
	}
}