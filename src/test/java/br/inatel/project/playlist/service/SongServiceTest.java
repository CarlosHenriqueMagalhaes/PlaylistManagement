package br.inatel.project.playlist.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.repository.SongRepository;
import br.inatel.project.playlist.management.service.Adapter;
import br.inatel.project.playlist.management.service.SongService;


@SpringBootTest
public class SongServiceTest {
		
	private SongDTO songDto;
	
	@InjectMocks
	private SongService service;
	
	@Mock
	private SongRepository repo;
	
	@Mock
	private Adapter adapter;
	
//	@BeforeEach
//	public void init() {
//		songDto = SongDTO.builder()
//				.id(1)
//				.music("Que Beleza")
//				.artist("Tim Maia")
//				.kindOfMusic("nacional")
//				.build();
//	}
	@Test
	public void deveriaencontrarumamusicapeloid() {
		Integer id = 1;
		repo.findById(id);
	}

//Testa se existe musicas no banco
	@Test
	public void TestaSeExisteAlgumaMusicaNoBanco() {
		List<Song> songs = service.findAllSongs();
		assertNotNull(songs);
	}

//Testa um ID válido
	@Test
	public void TestaMetodoQueBuscaUmaMusicaPorId() {
		Integer id = 1;
		Optional<Song> song = repo.findById(id);
		assertNotNull(song);
	}

//Testa um ID inválido
	@Test
	public void TestaMetodoQueBuscaUmaMusicaPorIdComIdInvalido() {
		Integer id = 8;
		Optional<Song> opStock = repo.findById(id);
		assertTrue(opStock.isEmpty());
	}
}
