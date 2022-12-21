package br.inatel.project.playlist.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.project.playlist.management.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	// método "findBy" automaticamente o Spring Data vai detectar buscar e
	// implementar
	// o método, nesse caso para o Email
	@Transactional(readOnly = true)
	Client findByEmail(String email);

	

//	Optional<Client> findByListEmail(String email);
}
