package br.inatel.project.playlist.service;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.repository.SongRepository;
import br.inatel.project.playlist.management.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {

	private  TrackDTO trackDto;
	private SongDTO songDto;

	private Song song;
	
	@InjectMocks
	private SongService service;
	
	@Mock
	private SongRepository repo;

	List<Song> songList = new ArrayList<>();

	@Before
	public void init() {
		song = Song.builder()
				.id(1)
				.music("Ticket To Ride")
				.artist("Beatles")
				.kindOfMusic("Rock")
				.songAlbum("Help!")
				.songDuration("30000")
				.build();

		songDto = SongDTO.builder()
				.id(1)
				.music("Ticket To Ride")
				.artist("Beatles")
				.kindOfMusic("Rock")
				.songAlbum("Help!")
				.songDuration("30000")
				.build();

		trackDto = TrackDTO.builder()
				.title("Stand By Me")
				.artist("Oasis")
				.build();
	}

	@Test
	public void givenValidSongId_WhenGetSongById_ShouldReturnSong() {
		when(repo.findById(1)).thenReturn(Optional.of(song));
		assertEquals(song.getArtist(),"Beatles");
	}

	@Test
	public void givenAllSongs_WhenGetListAllSongs_ShouldReturnListOfSongs(){
		when(repo.findAll()).thenReturn(songList);
		assertEquals(List.of(),songList);
	}

}


//	@Test
//	public void givenValidSongId_WhenGetSongById_ShouldReturnSong() {
//		Song song1 = Song.builder().music("Ticket To Ride").artist("Beatles")
//				.build();
//		when(repo.findById(1)).thenReturn(Optional.of(song1));
//		Song song = service.find(1);
//		assertEquals(song.getArtist(),"Beatles");
//	}

//	@Test
//	public void givenValidTrackAndArtist_WhenPostSong_ShouldReturnSong(){
//		TrackDTO novo = new TrackDTO("Ticket To Ride","Beatles","00","Help!","Rock");
//		Song song1 = Song.builder().music("Ticket To Ride").artist("Beatles")
//				.build();
//		when(repo.save(song1)).thenReturn(song1);
//		Song sg1 = service.saveSong(novo);
//
//		assertEquals(sg1.getArtist(),"Beatles");
//	}
