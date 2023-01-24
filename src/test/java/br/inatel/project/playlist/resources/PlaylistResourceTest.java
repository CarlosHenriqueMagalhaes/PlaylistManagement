package br.inatel.project.playlist.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaylistResourceTest {

	public PlaylistDTO createPlaylistDTO() {
		PlaylistDTO obj = new PlaylistDTO();
		obj.setPlaylistId(1);
		obj.setPlaylistName("Ada'sPlaylist");
		return obj;
	}

	public  PlaylistDTO createWrongPlaylistDTO() {
		PlaylistDTO obj = new PlaylistDTO();
		obj.setPlaylistId(1);
		obj.setPlaylistName(null);
		return obj;
	}

	@Test
	@Order(1)
	public void givenAReadOrder_WhenReceivingAllThePlaylists_ThenItShouldReturnStatus200Ok() {
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/playlists/listAll").exchange()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectStatus().isOk();
	}

	//Apenas se ja tiver criado alguma playlist no Postman
	@Test
	@Order(2)
	public void givenAReadOrderByValidPlaylistId_WhenReceivingThePlaylist_ThenItShouldReturnStatus200Ok() {
		Integer id = 1;

		Playlist playlist = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/playlists?id=" + id)
				.exchange().expectStatus().isOk().expectBody(Playlist.class).returnResult().getResponseBody();

		assertNotNull(playlist);
		assertEquals(playlist.getId(), id);
	}

	@Test
	@Order(3)
	public void givenAReadOrderByInvalidPlaylistId_WhenNotReceivingThePlaylist_ThenItShouldReturnStatus404NotFound() {
		int id = 35;// se eu alterar para um ID que contenha Playlist cadastrada o teste não passa(prova
		// que o método funciona)

		Playlist result = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/playlists?id=" + id)
				.exchange().expectStatus().isNotFound().expectBody(Playlist.class).returnResult().getResponseBody();

		assertEquals(result, result);
	}

	@Test
	@Order(4)
	public void givenPostANewPlaylist_WhenInsertPlaylistName_ThenItShouldReturnStatus201Created() {
		PlaylistDTO nq = createPlaylistDTO();
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build()
				.post()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus().isCreated()
				.expectBody();
	}

	@Test
	@Order(5)
	public void givenPostANewPlaylist_WhenInsertNullPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		PlaylistDTO nq = createWrongPlaylistDTO();
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build()
				.post()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody();

	}

	@Test
	@Order(6)
	public void DeleteAPlaylist_WhenReceivingThePlaylistId_ThenItShouldReturnStatus200Ok() {
		int id = 1;
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build()
				.delete().uri("/playlists/" + id)
				.exchange()
				.expectStatus().isOk()
				.expectHeader();
	}

	@Test
	@Order(7)
	public void DeleteAPlaylist_WhenReceivingInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 66;
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build()
				.delete().uri("/playlist/" + id)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader();
	}

	@Test
	@Order(8)
	public void givenPutAPlaylist_WhenInsertValidPlaylistName_ThenItShouldReturnStatus204NoContent() {
		PlaylistDTO nq = createPlaylistDTO();
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build()
				.put()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus().isNoContent()
				.expectBody();
	}

	@Test
	@Order(9)
	public void givenPutAPlaylist_WhenInsertInvalidPlaylistName_ThenItShouldReturnStatus404BadRequest() {
		PlaylistDTO nq = createWrongPlaylistDTO();
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build()
				.put()
				.uri("/playlists")
				.body(BodyInserters.fromValue(nq))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody();
	}

}

