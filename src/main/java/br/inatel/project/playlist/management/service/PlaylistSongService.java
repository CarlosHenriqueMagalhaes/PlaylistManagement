package br.inatel.project.playlist.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.PlaylistSong;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;

/**
 * Service class. Project logic implemented here.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Service
public class PlaylistSongService {
	@Autowired
	private PlaylistSongRepository repo;

	/**
	 * find one song by id (GET)
	 * @param idPlaylist
	 * @param idSong
	 * @return relationship between Playlist and Song
	 */
	public PlaylistSong findByPlayIdAndSongId(Integer idPlaylist, Integer idSong) {
		Optional<PlaylistSong> playlistSong = repo.findByPlaylistIdAndSongId(idPlaylist, idSong);
		return playlistSong.orElseThrow(() -> new ObjectNotFoundException("This Song does not exist in this Playlist"));
	}
}
