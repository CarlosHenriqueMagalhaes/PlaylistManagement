package br.inatel.project.playlist.management.repository;

import br.inatel.project.playlist.management.domain.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.Song;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

//    select * from song where song.music = :music and song.artist = :artist;
    Song findByMusicAndArtist (String music, String artist);

}
