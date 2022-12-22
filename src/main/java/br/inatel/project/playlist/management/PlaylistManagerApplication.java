package br.inatel.project.playlist.management;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.repository.ClientRepository;
import br.inatel.project.playlist.management.repository.SongRepository;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class PlaylistManagerApplication implements CommandLineRunner {

//	@Autowired
//	private PlaylistRepository playlistRepository;
	@Autowired
	private SongRepository songRepository;
	
	@Autowired ClientRepository clientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PlaylistManagerApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void run(String... args) throws Exception {		
		
//	Playlist pl1 = new Playlist (null, "My Top Five");
//	Playlist pl2 = new Playlist (null, "National");
//	Playlist pl3 = new Playlist (null, "Mix List");
//	Playlist pl4 = new Playlist (null, "Rock'n'Roll Baby");
		
	Song sgn1 = new Song(null,"Que Beleza","Tim Maia","Nacional");
	Song sgn2 = new Song(null,"Last Kiss","Pearl Jam","Grunge");
	Song sgn3 = new Song(null,"Silent Running","Gorillaz","Rock Alternativo");
	Song sgn4 = new Song(null,"Man in the box","Alice in Chains","Grunge");
	Song sgn5 = new Song(null,"Dance of Death","Iron Maiden","Metal");
	Song sgn6 = new Song(null,"Somewhere I belong","Linkin Park","Rock Alternativo");
	Song sgn7 = new Song(null,"Invisible","Linkin Park","Rock Alternativo");
	
//	pl1.getSongs().addAll(Arrays.asList(sgn2,sgn4,sgn1,sgn6,sgn7));
//	pl2.getSongs().addAll(Arrays.asList(sgn1));
//	pl3.getSongs().addAll(Arrays.asList(sgn1,sgn3,sgn4,sgn2,sgn7));
//	pl4.getSongs().addAll(Arrays.asList(sgn2,sgn4,sgn5,sgn6,sgn7));
	
//	sgn1.getPlaylists().addAll(Arrays.asList(pl1,pl2,pl3));
//	sgn2.getPlaylists().addAll(Arrays.asList(pl1,pl4,pl3));
//	sgn3.getPlaylists().addAll(Arrays.asList(pl3));
//	sgn4.getPlaylists().addAll(Arrays.asList(pl1,pl4,pl3));
//	sgn5.getPlaylists().addAll(Arrays.asList(pl4));
//	sgn6.getPlaylists().addAll(Arrays.asList(pl1,pl4));
//	sgn7.getPlaylists().addAll(Arrays.asList(pl1,pl4,pl3));
//	
//	playlistRepository.saveAll(Arrays.asList(pl1,pl2,pl3,pl4));
	songRepository.saveAll(Arrays.asList(sgn1, sgn2, sgn3, sgn4, sgn5, sgn6, sgn7));
	}
	

	
}


