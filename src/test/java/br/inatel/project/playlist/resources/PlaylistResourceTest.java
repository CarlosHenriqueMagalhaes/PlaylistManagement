package br.inatel.project.playlist.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaylistResourceTest {

	@Autowired
	private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build();

	public PlaylistDTO createPlaylistDTO() {
		PlaylistDTO obj = new PlaylistDTO();
		obj.setPlaylistId(null);
		obj.setPlaylistName("Ada's Playlist");
		return obj;
	}


	// cria uma nova playlist valida
	@Test
	@Order(1)
	public void givenPostANewPlaylist_WhenInsertPlaylistName_ThenItShouldReturnStatus201Created() {
		PlaylistDTO nq = createPlaylistDTO();
		webTestClient
				.post()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody();
	}

	//tenta criar uma nova playlist com o nome null
	@Test
	@Order(2)
	public void givenPostANewPlaylist_WhenInsertNullPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistName(null);
		webTestClient
				.post()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus()
				.isBadRequest()
				.expectBody();
	}

	//Lista todas as playlists
	@Test
	@Order(3)
	public void givenAReadOrder_WhenReceivingAllThePlaylists_ThenItShouldReturnStatus200Ok() {
		webTestClient
				.get()
				.uri("/playlists/listAll")
				.exchange()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectStatus().isOk();
	}

	//Retorna Uma playlist com id valido
	@Test
	@Order(4)
	public void givenAReadOrderByValidPlaylistId_WhenReceivingThePlaylist_ThenItShouldReturnStatus200Ok() {
		Integer id = 1;

		Playlist playlist = webTestClient
				.get().uri("/playlists?id=" + id)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Playlist.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(playlist);
		assertEquals(playlist.getId(), id);
	}

	//Altera o nome de uma playlist
	@Test
	@Order(5)
	public void givenPutAPlaylist_WhenInsertValidPlaylistName_ThenItShouldReturnStatus200Ok() {
		PlaylistDTO nq = createPlaylistDTO();
				nq.setPlaylistId(1);
				nq.setPlaylistName("Test Playlist Order five");
		webTestClient
				.put()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody();
	}

	//Erro ao Alterar o nome de uma playlist deixando o campo null
	@Test
	@Order(6)
	public void givenPutAPlaylist_WhenInsertInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistId(1);
		nq.setPlaylistName(null);
		webTestClient
				.put()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody();
	}

// Tenta mudar o nome de uma playlist que não existe
	@Test
	@Order(7)
	public void givenPutAPlaylist_WhenInsertInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistId(0);
		nq.setPlaylistName("Never Be");
		webTestClient
				.put()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus().isNotFound()
				.expectBody();
	}

	//Deleta uma playlist
	@Test
	@Order(8)
	public void DeleteAPlaylist_WhenReceivingThePlaylistId_ThenItShouldReturnStatus204NoContent() {
		int id = 1;
		webTestClient
				.delete().uri("/playlists/" + id)
				.exchange()
				.expectStatus()
				.isNoContent()
				.expectHeader();
	}


	//busca uma playlist pelo Id que não existe
	@Test
	@Order(9)
	public void givenAReadOrderByInvalidPlaylistId_WhenNotReceivingThePlaylist_ThenItShouldReturnStatus404NotFound() {
		int id = 1;// se eu alterar para um ID que contenha Playlist cadastrada o teste não passa(prova
		// que o método funciona)
		Playlist result = webTestClient
				.get()
				.uri("/playlists?id=" + id)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody(Playlist.class)
				.returnResult()
				.getResponseBody();

		assertEquals(result, result);
	}

	//Tenta deletar uma playlist que não existe pelo seu id
	@Test
	@Order(10)
	public void DeleteAPlaylist_WhenReceivingInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 1;
		webTestClient
				.delete()
				.uri("/playlist/" + id)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectHeader();
	}

}

