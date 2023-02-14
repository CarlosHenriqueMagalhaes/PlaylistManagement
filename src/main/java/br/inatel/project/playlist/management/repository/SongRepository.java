package br.inatel.project.playlist.management.repository;

import br.inatel.project.playlist.management.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository of the entity which is being saved in the database.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    /**
     *select * from song where song.music = :music and song.artist = :artist;
     */
    Song findByMusicAndArtist (String music, String artist);
}
