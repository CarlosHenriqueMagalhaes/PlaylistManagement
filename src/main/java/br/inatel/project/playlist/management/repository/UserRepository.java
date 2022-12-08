package br.inatel.project.playlist.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.project.playlist.management.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
