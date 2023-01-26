package br.inatel.project.playlist.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.SongRepository;
import br.inatel.project.playlist.management.service.PlaylistService;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

	private Playlist playlist;
	
	@InjectMocks
	private PlaylistService service;
	
	@Mock
	private PlaylistRepository repo;

	List<Playlist> plList = new ArrayList<>();

	@Before
	public void init() {
		playlist = Playlist.builder()
				.id(1)
				.playlistName("The Best")
				.build();
	}

	@Test
	public void givenValidPlaylistId_WhenGetPlaylistById_ShouldReturnPlaylist() {
		when(repo.findById(1)).thenReturn(Optional.of(playlist));
		assertEquals(playlist.getId(),1);
	}
	
	@Test
	public void givenAllPlaylists_WhenGetListAllPlaylist_ShouldReturnListOfPlaylist(){
		when(repo.findAll()).thenReturn(plList);
		assertEquals(List.of(),plList);
	}

	@Test
	public void givenInsertANewPlaylists_WhenPostANewPlaylist_ShouldReturnANewPlaylist(){
		when(repo.save(playlist)).thenReturn(playlist);
		assertEquals(playlist.getId(),1);
	}

}


