package br.inatel.project.playlist.management;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * PlaylistManagerAPI
 * @author Carlos Magalhães
 * @since 1.0
 */
@SpringBootApplication
public class PlaylistManagerApplication implements CommandLineRunner {
	@Autowired
	private SongRepository songRepository;
	@Autowired
	private PlaylistRepository playlistRepository;

	public static void main(String[] args) {
		SpringApplication.run(PlaylistManagerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Song sgn1 = new Song(null, "O caminho do bem","Tim Maia", "30000","Tim Maia Racional", "Nacional, soul, 70s");
		songRepository.saveAll(List.of(sgn1));
		Playlist pl1 = new Playlist(null,"Default Playlist");
		playlistRepository.saveAll(List.of(pl1));
	}
}
	
