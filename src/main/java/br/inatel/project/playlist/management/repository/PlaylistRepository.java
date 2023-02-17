package br.inatel.project.playlist.management.repository;

import br.inatel.project.playlist.management.domain.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository of the entity which is being saved in the database.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> , JpaSpecificationExecutor<Playlist> {
    Page<Playlist> findAll(Pageable pageable);
}
