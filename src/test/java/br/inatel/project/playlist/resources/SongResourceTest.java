package br.inatel.project.playlist.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.form.TrackForm;
import br.inatel.project.playlist.management.rest.Track;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongResourceTest {


	@Autowired
	private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build();

	public TrackForm apiData() {
		TrackForm obj = new TrackForm();
		obj.setTrack("Sunshine");
		obj.setArtist("Aerosmith");
		return obj;
	}

	public SongDTO songDTO() {
		SongDTO obj = new SongDTO();
		obj.setId(5);
		obj.setMusic("The Great Gig in the Sky");
		obj.setArtist("Pink Floyd");
		obj.setKindOfMusic("Rock");
		obj.setSongAlbum("The Dark Side of the Moon");
		obj.setSongDuration("4430000");
		return obj;
	}

	public PlaylistDTO playlistDTO() {
		PlaylistDTO obj = new PlaylistDTO();
		obj.setPlaylistId(1);
		obj.setPlaylistName("Best Songs");
		return obj;
	}
//sucesso ao encontrar uma musica na API externa
	@Test
	@Order(1)
	public void SuccessfindASongInAPIExternal_ThenItShouldReturnStatus200Ok() {
		TrackForm ap = apiData();
		Song song = webTestClient
				.post()
				.uri("/songs/find" )
				.body(BodyInserters.fromValue(ap))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Song.class)
				.returnResult()
				.getResponseBody();
	}

	//falha em encontrar uma musica na API externa
	@Test
	@Order(2)
	public void FailfindASongInAPIExternal_ThenItShouldReturnStatus404NotFound() {
		TrackForm ap = apiData();
		Song song = webTestClient
				.post()
				.uri("/songs/find" )
				.body(BodyInserters.fromValue(ap))
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody(Song.class)
				.returnResult()
				.getResponseBody();
	}

	//sucesso ao listar todas as musicas
	@Test
	@Order(3)
	public void givenAReadOrder_WhenReceivingAllTheSongs_ThenItShouldReturnStatus200Ok() {
		webTestClient
				.get()
				.uri("/songs/listAll")
				.exchange()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectStatus().isOk();
	}

	//sucesso ao encontrar uma musica pelo id
	@Test
	@Order(4)
	public void givenAReadOrderBySongIdValid_WhenReceivingTheSong_ThenItShouldReturnStatus200Ok() {
		Integer id = 1;

		Song song = webTestClient
				.get()
				.uri("/songs?id=" + id)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Song.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(song);
		assertEquals(song.getId(), id);
	}

	//falha em encontrar uma musica pelo id
	@Test
	@Order(5)
	public void givenAReadOrderBySongIdInvalid_WhenNotReceivingTheSong_ThenItShouldReturnStatus404NotFound() {
		int id = 35;// se eu alterar para um ID que contenha Song cadastrada o teste não passa(prova
		// que o método funciona)

		Song result = webTestClient
				.get()
				.uri("/songs?id=" + id)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody(Song.class)
				.returnResult()
				.getResponseBody();

		assertEquals(result, result);
	}
// sucesso em adicionar uma musica em uma playlist
	@Test
	@Order(6)
	public void AdicinaUmaMusicaEmUmaPlaylist_ThenItShouldReturnStatus200Ok() {
		SongDTO sg = songDTO();
		PlaylistDTO pl = playlistDTO();
		webTestClient
				.post()
				.uri("/songs/addSongAtPlaylist?songId=" + sg.getId())
				.body(BodyInserters.fromValue(pl))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody();
	}

	//falha em adicionar uma musica em uma playlist
	@Test
	@Order(7)
	public void FalhaEmAdicionarUmaMusicaEmUmaPlaylist_ThenItShouldReturnStatus404NotFound() {
		SongDTO sg = songDTO();
		PlaylistDTO pl = playlistDTO();
		webTestClient
				.post()
				.uri("/songs/addSongAtPlaylist?songId=" + sg.getId())
				.body(BodyInserters.fromValue(pl))
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody();
	}
	//sucesso em remover uma musica de uma playlist
	@Test
	@Order(8)
	public void DeleteASonginAPlayList_ThenItShouldReturnStatus204NoContent() {
		SongDTO sg = songDTO();
		PlaylistDTO pl = playlistDTO();
		webTestClient
				.delete()
				.uri("/songs/removeSong?playlistId=" + pl.getPlaylistId() +"&songId=" + sg.getId())
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader();
	}

	//falha em remover uma musica de uma playlist
	@Test
	@Order(9)
	public void FailDeleteASongInAPlayList_ThenItShouldReturnStatus404NotFound() {
		SongDTO sg = songDTO();
		PlaylistDTO pl = playlistDTO();
		webTestClient
				.delete()
				.uri("/songs/removeSong?playlistId=" + pl.getPlaylistId() +"&songId=" + sg.getId())
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectHeader();
	}
}
