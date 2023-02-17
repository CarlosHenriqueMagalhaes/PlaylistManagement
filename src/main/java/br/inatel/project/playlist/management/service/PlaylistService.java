package br.inatel.project.playlist.management.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.PlaylistManagerDTO;
import br.inatel.project.playlist.management.exception.NullObjectNotFoundException;
import br.inatel.project.playlist.management.exception.ObjectNotFoundException;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class. Project logic implemented here.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository repo;
    @Autowired
    private PlaylistSongRepository plSgRepo;

    /**
     * find one playlist for id (GET)
     *
     * @param id
     * @return A Playlist
     */
    public Playlist find(Integer id) {
        Optional<Playlist> playlist = repo.findById(id);
        return playlist.orElseThrow(() -> new ObjectNotFoundException(
                "ObjectNotFound! This Playlist Id:" + id + ", does not exist!"));
    }

    /**
     * find all registered playlists (GET)
     *
     * @return All Playlist
     */
    public List<Playlist> findAllPlaylist() {
        return repo.findAll();
    }

    /**
     * find all Songs in my bank (GET) with Pageable
     *
     * @param page
     * @return
     */
    public Page<Playlist> findAllPlaylistsPageable(Pageable page) {
        return (repo.findAll(page));
    }

    /**
     * Insert a new Playlist (POST)
     *
     * @param playlist
     * @return Create a new Playlist
     */
    public Playlist insert(Playlist playlist) {
        return repo.save(playlist);
    }

    /**
     * Method PATCH - Change a customer's Playlist name by ID
     *
     * @param playlistId
     * @return Rename a PlaylistName
     */
    public Playlist update(Integer playlistId, PlaylistManagerDTO playlistDTO) {
        Playlist playlist = null;
        try {
            playlist = find(playlistId);
            if (playlist != null) {
                playlist.setPlaylistName(playlistDTO.getPlaylistName());
                return repo.save(playlist);
            }
        } catch (Exception e) {
            throw new NullObjectNotFoundException("The playlist id:" + playlistId + " does not exist");
        }
        return playlist;
    }

    /**
     * helper method that instantiates a playlist from a DTO (used in the POST method)
     *
     * @param playlistDto
     * @return playlistDto
     */
    public Playlist fromDTO(PlaylistDTO playlistDto) {
        return new Playlist(playlistDto.getPlaylistId(), playlistDto.getPlaylistName());
    }

    /**
     * Delete a Playlist (DELETE)
     * ABOUT the line : plSgRepo.deleteAllInBatch();
     * ensures that by the associative list I remove the songs from the playlist before
     * to delete it, so the songs are not deleted from the database, and only from the playlist
     *
     * @param id
     * @return msg "The playlist id:-- is successfully deleted"
     */
    public String delete(Integer id) {
        find(id);
        plSgRepo.deleteAllInBatch();
        repo.deleteById(id);
        return ("The playlist id: " + id + " is successfully deleted");
    }

    /**
     * saveAndFlush
     *
     * @param playInsert
     * @return Help to save Playlist In repository
     */
    public Playlist saveAndFlush(Playlist playInsert) {
        return repo.saveAndFlush(playInsert);
    }
}
