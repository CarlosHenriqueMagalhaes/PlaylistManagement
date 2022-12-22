package br.inatel.project.playlist.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.PlaylistSong;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;

@Service
public class PlaylistSongService {

	@Autowired
	private PlaylistSongRepository repo;

	public PlaylistSongService(PlaylistSongRepository repo) {
		this.repo = repo;
	}

	// find one song by id (GET)
	public PlaylistSong findByPlayIdAndSongId(Integer idPlaylist, Integer idSong) {
		Optional<PlaylistSong> obj = repo.findByPlaylistIdAndSongId(idPlaylist, idSong);
		return obj.orElseThrow(() -> new ObjectNotFoundException("This Song does not existis in this Playlist"));
	}

}
