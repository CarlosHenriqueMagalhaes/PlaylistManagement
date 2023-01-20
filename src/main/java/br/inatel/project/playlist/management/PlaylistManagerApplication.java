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

		Song sgn1 = new Song(null, "Que Beleza","Tim Maia", "30000","Que Beleza", "Nacional");
//		Song sgn2 = new Song(null, "Last Kiss", "Pearl Jam", "Grunge");
//		Song sgn3 = new Song(null, "Silent Running", "Gorillaz", "Rock Alternativo");
//		Song sgn4 = new Song(null, "Man in the box", "Alice in Chains", "Grunge");
//		Song sgn5 = new Song(null, "Dance of Death", "Iron Maiden", "Metal");
//		Song sgn6 = new Song(null, "Somewhere I belong", "Linkin Park", "Rock Alternativo");
//		Song sgn7 = new Song(null, "Invisible", "Linkin Park", "Rock Alternativo");
		songRepository.saveAll(List.of(sgn1));
//		songRepository.saveAll(Arrays.asList(sgn1, sgn2, sgn3, sgn4, sgn5, sgn6, sgn7));
	}

}
	
