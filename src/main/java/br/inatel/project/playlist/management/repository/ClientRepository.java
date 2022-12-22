package br.inatel.project.playlist.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	// método "findBy" automaticamente o Spring Data vai detectar buscar e
	// implementar
	// o método, nesse caso para o Email
	//@Transactional(readOnly = true)
	//Client findByEmail(String email);
	
	public Optional<Client> findByEmail (String email);

}
