package br.inatel.project.playlist.management.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.PlaylistSong;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.exception.NullObjectNotFoundException;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.form.TrackForm;
import br.inatel.project.playlist.management.mapper.Mapper;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;
import br.inatel.project.playlist.management.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Service class. Project logic implemented here.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
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
	@Autowired
	private PlaylistSongRepository plSgRepo;
	@Autowired
	private PlaylistRepository plRepo;

	/**
	 * find one song by id (GET)
	 * @param id
	 * @return one song by id
	 */
	public Song find(Integer id) {
		Optional<Song> song = repo.findById(id);
		return song.orElseThrow(() -> new ObjectNotFoundException("ObjectNotFound! This Song Id:" + id
		+ ", does not exist or is not registered! "));
	}

	/**
	 * find all Songs in my bank (GET)
	 * @return All Songs present in my bank
	 */
	public List<Song> findAllSongs() {
		return repo.findAll();
	}

	/**
	 * Insert a song at Playlist
	 * @param songId
	 * @param playlistId
	 */
	public void addSongToPlaylist(@Valid Integer songId, Integer playlistId) {
		// search for music by id:
		Optional<Song> songOptional = repo.findById(songId);
		// make sure the music exists
		songOptional.orElseThrow(() -> new ObjectNotFoundException("ObjectNotFound! This Song Id:" + songId
				+ ", does not exist or is not registered!"));
		// search for Playlist by id:
		Optional<Playlist> playlistOptional = plRepo.findById(playlistId);
		//       make sure the music exists
		playlistOptional.orElseThrow(() -> new ObjectNotFoundException("ObjectNotFound! This Playlist Id:" + playlistId
				+ ", does not exist or is not registered!"));
		// check if the relationship exists
		Optional<PlaylistSong> verif = plSgRepo.findByPlaylistIdAndSongId(playlistId, songId);
		if (verif.isEmpty()){
			Song song = songOptional.get();
			Playlist playlist = playlistOptional.get();
			song.getPlaylists().add(playlist);
			playlist.getSongs().add(song);
			playlist = playService.saveAndFlush(playlist);
					song = repo.save(song);
				}
		}

	/**
	 * Remove a song in a playlist
	 * @param playlistId
	 * @param songId
	 * @return playlist without the song that was removed
	 * @throws Exception
	 */
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

	/**
	 * POST that searches the external API for music and data about it
	 * @param form
	 * @return A song from the external api
	 * @throws Exception
	 */
	public TrackDTO getTrack(TrackForm form) throws Exception {
		try {
			String artist = form.getArtist();
			String track = form.getTrack();
			return Mapper.convertRestToDto(adapterService.getLastFm(track, artist));
		} catch (Exception e) {
			throw new NullObjectNotFoundException("The artist and track fields must exist and cannot be null.");
		}
	}

	/**
	 *  Save song from API external in my bank
	 * @param trackDTO
	 * @return Song from API external
	 */
	public Song saveSong(TrackDTO trackDTO) {
		Song song = new Song(trackDTO);
		return repo.save(song);
	}

	/**
	 * check if the artist's music exists in the bank if there is no save
	 * @param trackDTO
	 */
	public void addSongToBase(TrackDTO trackDTO) {
		Song song = repo.findByMusicAndArtist(trackDTO.getTitle(), trackDTO.getArtist());
		if ( song == null){
			saveSong(trackDTO);
		}
	}
}