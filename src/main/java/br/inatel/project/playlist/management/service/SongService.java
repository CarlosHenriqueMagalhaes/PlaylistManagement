package br.inatel.project.playlist.management.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.exception.NullObjectNotFoundException;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	private SongRepository repo;

	@Autowired
	private PlaylistService playService;

	@Autowired
	private PlaylistSongService playlisSongService;

	public SongService(SongRepository repo, PlaylistService playService, PlaylistSongService playlisSongService) {
		this.repo = repo;
		this.playService = playService;
		this.playlisSongService = playlisSongService;
	}

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
//		 busco a música pelo id 
		Optional<Song> songOptional = repo.findById(songId);

		if (songOptional != null && songOptional.isPresent()) {
			Song song = songOptional.get();
			// esse if é usado qdo o usuário informa uma playlist existente
			if (playlistDTO.getId() != null) {
				Playlist playlist = playService.find(playlistDTO.getId());
				if (playlist != null) {
					playlist.getSongs().add(songOptional.get());
					song.getPlaylists().add(playlist);
					playlist = playService.saveAndFlush(playlist);
					song = repo.save(song);
				}
			}

			// esse else é utilizado para salvar uma playlist antes de fazer a associação da
			// música a playlist ela só ocorre se o id for null, se eu colocar um id
			// onde não há playlist cadastrada, ele retorna objectNotFound
			else {
				Playlist createdPlaylist = new Playlist();
				createdPlaylist.setPlaylistName(playlistDTO.getPlaylistName());
				Playlist insertedPlaylist = playService.insert(createdPlaylist);

				insertedPlaylist.getSongs().add(songOptional.get());
				song.getPlaylists().add(insertedPlaylist);

				insertedPlaylist = playService.saveAndFlush(insertedPlaylist);
				song = repo.save(song);

				// fazer:
				// ao colocar um id de musica invalido no postman, ele retorna o status accepted
				// e não salva nada na lista (pq não existe mesmo!), mas ele deveria retornar
				// erro.

			}

		}

	}

	// Remove a song in a playlist
	public String removeSongToPlaylist(Integer playlistId, Integer songId) throws Exception {

		try {
			
			// chamar o service novo
			playlisSongService.findByPlayIdAndSongId(playlistId, songId);

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
}
