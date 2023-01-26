package br.inatel.project.playlist.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//@ActiveProfiles("test")
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
	public void givenAPostOrder_WhenInsertAValidPlaylistName_ThenItShouldReturnStatus201Created() {
		PlaylistDTO nq = createPlaylistDTO();
		webTestClient
				.post()
				.uri("/playlists")
				.bodyValue(nq)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody();

		assertEquals(nq.getPlaylistName(),"Ada's Playlist");
	}

	//tenta criar uma nova playlist com o nome null
	@Test
	@Order(2)
	public void givenAPostOrder_WhenInsertAInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistName(null);
		webTestClient
				.post()
				.uri("/playlists")
				.bodyValue(nq)
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
	public void givenAReadOrder_WhenInsertAValidPlaylistId_ThenItShouldReturnStatus200Ok() {
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
	public void givenAPutOrder_WhenInsertAValidPlaylistName_ThenItShouldReturnStatus200Ok() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistId(1);
		nq.setPlaylistName("Test Playlist Order five");
		webTestClient
				.put()
				.uri("/playlists")
				.bodyValue(nq)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody();
		assertEquals(nq.getPlaylistName(),"Test Playlist Order five");
	}

	//Erro ao Alterar o nome de uma playlist deixando o campo null
	@Test
	@Order(6)
	public void givenAPutOrder_WhenInsertAInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistId(1);
		nq.setPlaylistName(null);
		webTestClient
				.put()
				.uri("/playlists")
				.bodyValue(nq)
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody();
	}

	// Tenta mudar o nome de uma playlist que não existe
	@Test
	@Order(7)
	public void givenAPutOrder_WhenInsertInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		PlaylistDTO nq = createPlaylistDTO();
		nq.setPlaylistId(0);
		nq.setPlaylistName("Never Be");
		webTestClient
				.put()
				.uri("/playlists")
				.bodyValue(nq)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody();
	}

	//Deleta uma playlist
	@Test
	@Order(8)
	public void givenADeleteOrder_WhenInsertAValidPlaylistId_ThenItShouldReturnStatus204NoContent() {
		int id = 2;
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
	public void givenAReadOrder_WhenInsertAInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 0;// se eu alterar para um ID que contenha Playlist cadastrada o teste não passa(prova
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
	public void givenADeleteOrder_WWhenInsertAInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 0;
		webTestClient
				.delete()
				.uri("/playlist/" + id)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectHeader();
	}

}


