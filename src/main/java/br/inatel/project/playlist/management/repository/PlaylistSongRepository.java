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
//	This method does the same thing as above, but it ignores the JPA and "adds" my Query:
//	@Query(value="select * from Song_Playlist sp where sp.song_id = :playlistId and sp.playlist_id = :songId ", nativeQuery= true)
//	Optional<PlaylistSong> findByPlaylistIdNative(@Param("playlistId") Integer playlistId, @Param("songId") Integer songId);
