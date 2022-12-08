package br.inatel.project.playlist.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.project.playlist.management.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

}
