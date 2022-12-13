package br.inatel.project.playlist.management.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	private SongRepository repo;

	@Autowired
	private PlaylistService playService;

	public SongService(SongRepository repo, PlaylistService playService) {
		this.repo = repo;
		this.playService = playService;
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

	//fazer busca uma musica em uma playlist

	// add a song to a playlist (POST)
	public void addSongToPlaylist(@Valid Integer songId, PlaylistDTO playlistDTO) {
//		 busco a música pelo id método que criei sobrescrevendo o do JPA no meu repository
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
			// onde não a playllist cadastrada, ele retorna objectNotFound
			else {
				Playlist createdPlaylist = new Playlist();
				createdPlaylist.setPlaylistName(playlistDTO.getPlaylistName());
				Playlist insertedPlaylist = playService.insert(createdPlaylist);

				insertedPlaylist.getSongs().add(songOptional.get());
				song.getPlaylists().add(insertedPlaylist);

				insertedPlaylist = playService.saveAndFlush(insertedPlaylist);
				song = repo.save(song);
				
				//fazer:
				//ao colocar um id de musica invalido no postman, ele retorna o status accepted
				//e não salva nada na lista (pq não existe mesmo!), mas ele deveria retornar erro. 

			}

		}
	}
}

// remove uma musica de uma playlist
