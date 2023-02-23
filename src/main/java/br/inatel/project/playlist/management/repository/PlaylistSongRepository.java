package br.inatel.project.playlist.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.PlaylistSong;

/**
 * Repository of the entity which is being saved in the database.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Integer> {
	Optional<PlaylistSong> findByPlaylistIdAndSongId(Integer playlistId, Integer songId);
}
