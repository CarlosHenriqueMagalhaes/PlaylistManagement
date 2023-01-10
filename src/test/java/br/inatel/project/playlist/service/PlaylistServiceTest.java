package br.inatel.project.playlist.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.service.PlaylistService;

@SpringBootTest
public class PlaylistServiceTest {

	private PlaylistService service;

	@MockBean
	private PlaylistRepository repo;

	@Captor
	private ArgumentCaptor<Playlist> captor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.service = new PlaylistService(repo);
	}
	
	private Playlist createPlaylist() {
		Playlist playlist = Mockito.mock(Playlist.class);
//		Mockito.when(playlist.getId().thenReturn(Integer id));
		return playlist;
	}

	// Tests:

	@Test
	@DisplayName("Busca todas as playlists")
	void testFindAllPlaylists() {
		service.findAllPlaylist();
		assertNotNull(repo);
	}
	
	@Test
	@DisplayName("Deve deletar uma Playlist")
	void deleteOnePlaylistbyId() {
		Integer id = (1);

		Optional<Playlist> playlist = Optional.of(createPlaylist());
		Mockito.when(repo.findById(ArgumentMatchers.eq(id))).thenReturn(playlist);
		
		service.delete(id);
	}
	
	@Test
	@DisplayName("Deve buscar uma Playlist")
	void FindOnePlaylistbyId() {
		Integer id = (1);

		Optional<Playlist> playlist = Optional.of(createPlaylist());
		Mockito.when(repo.findById(ArgumentMatchers.eq(id))).thenReturn(playlist);
		
		service.find(id);
	}
	

}
