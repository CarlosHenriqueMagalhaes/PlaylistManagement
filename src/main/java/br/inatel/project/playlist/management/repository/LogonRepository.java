package br.inatel.project.playlist.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.Logon;

@Repository
public interface LogonRepository extends JpaRepository<Logon, Integer> {

}
