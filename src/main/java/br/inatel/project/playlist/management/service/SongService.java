package br.inatel.project.playlist.management.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.exception.NullObjectNotFoundException;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.form.TrackForm;
import br.inatel.project.playlist.management.mapper.Mapper;
import br.inatel.project.playlist.management.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

	@Autowired
	private SongRepository repo;
	@Autowired
	private PlaylistService playService;
	@Autowired
	private PlaylistSongService playlistSongService;
	@Autowired
	private Adapter adapterService;

	// find one song by id (GET)
	public Song find(Integer id) {
		Optional<Song> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("ObjectNotFound! This Song Id:" + id
				+ ", does not exist or is not registered! " + "Type: " + Song.class.getName()));
	}

	// find all Songs in my bank (GET)
	public List<Song> findAllSongs() {
		return repo.findAll();
	}

	// add a song to a playlist (POST)
	public void addSongToPlaylist(@Valid Integer songId, PlaylistDTO playlistDTO) {
		// search for music by id:
		Optional<Song> songOptional = repo.findById(songId);
//       make sure the music exists
		songOptional.orElseThrow(() -> new ObjectNotFoundException("ObjectNotFound! This Song Id:" + songId
				+ ", does not exist or is not registered! " + "Type: " + Song.class.getName()));
		//

		if (songOptional != null && songOptional.isPresent()) {
			Song song = songOptional.get();
			// this "if" is used when the user informs an existing playlist
			if (playlistDTO.getId() != null) {
				Playlist playlist = playService.find(playlistDTO.getId());
				if (playlist != null) {
					playlist.getSongs().add(songOptional.get());
					song.getPlaylists().add(playlist);
					playlist = playService.saveAndFlush(playlist);
					song = repo.save(song);
				}
			} else {
				throw new ObjectNotFoundException(
						"this playlist does not exist, you must create a playlist before inserting a song or insert the song in an existing playlist");

			}

		}

	}

	// Remove a song in a playlist
	public String removeSongToPlaylist(Integer playlistId, Integer songId) throws Exception {

		try {
			// Call playlistSongService
			playlistSongService.findByPlayIdAndSongId(playlistId, songId);
			Playlist playlist = playService.find(playlistId);
			Song song = find(songId);
			playlist.getSongs().remove(song);
			song.getPlaylists().remove(playlist);

			playlist = playService.saveAndFlush(playlist);
			song = repo.saveAndFlush(song);

			return ("The song id: " + songId + " has been successfully removed from the playlist id: " + playlistId);

		} catch (Exception e) {
			throw new NullObjectNotFoundException("This song is not in this playlist or this playlist does not exist");
		}
	}

//	POST que busca na API externa a musica e dados sobre ela
	public TrackDTO getTrack(TrackForm form) throws Exception {
		try {
			String artist = form.getArtist();
			String track = form.getTrack();
			return Mapper.convertRestToDto(adapterService.getRest(track, artist));
		} catch (Exception e) {
			throw new NullObjectNotFoundException("The artist and track fields must exist and cannot be null.");
		}
	}

	public Song saveSong(TrackDTO trackDTO) {
		Song song = new Song(trackDTO);
		return repo.save(song);
	}

	public void addSongToBase(TrackDTO trackDTO) {
		//fazer a verificação se a musica do artista tal existe no banco
		//se não existir salva

		Song song = repo.findByMusicAndArtist(trackDTO.getTitle(), trackDTO.getArtist());

		if ( song == null){
			saveSong(trackDTO);
		}
	}
}
