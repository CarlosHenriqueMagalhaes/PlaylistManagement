package br.inatel.project.playlist.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

}
