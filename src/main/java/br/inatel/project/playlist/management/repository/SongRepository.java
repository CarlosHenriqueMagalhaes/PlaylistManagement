package br.inatel.project.playlist.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.project.playlist.management.model.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

}
