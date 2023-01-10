package br.inatel.project.playlist.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	// "findBy" method automatically Spring Data will detect fetch and
	// to implement the method, in this case for the Email

	public Optional<Client> findByEmail(String email);

	// I changed this method to Optional (described above)
	// @Transactional(readOnly = true)
	// Client findByEmail(String email);

}
