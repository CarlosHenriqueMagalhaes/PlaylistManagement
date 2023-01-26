package br.inatel.project.playlist.management;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.repository.SongRepository;

@SpringBootApplication
public class PlaylistManagerApplication implements CommandLineRunner {

	@Autowired
	private SongRepository songRepository;

	public static void main(String[] args) {
		SpringApplication.run(PlaylistManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Song sgn1 = new Song(null, "O caminho do bem","Tim Maia", "30000","Tim Maia Racional", "Nacional, soul, 70s");
		songRepository.saveAll(List.of(sgn1));

	}

}
	
