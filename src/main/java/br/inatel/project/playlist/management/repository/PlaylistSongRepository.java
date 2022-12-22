package br.inatel.project.playlist.management.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.PlaylistSong;

@Repository
public interface PlaylistSongRepository extends JpaRepository <PlaylistSong,Integer> {
	
	
	Optional<PlaylistSong> findByPlaylistIdAndSongId(Integer playlistId, Integer songId);
	
//	@Query(value="select * from Song_Playlist sp where sp.song_id = :playlistId and sp.playlist_id = :songId ", nativeQuery= true)
//	Optional<PlaylistSong> findByPlaylistIdNativa(@Param("playlistId") Integer playlistId, @Param("songId") Integer songId);
	
	
	
}
