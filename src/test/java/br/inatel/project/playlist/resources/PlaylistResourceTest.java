package br.inatel.project.playlist.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

//Integration Tests
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaylistResourceTest {
	@Autowired
	private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build();

	public PlaylistDTO createPlaylistDTO() {
		PlaylistDTO playlistDTO = new PlaylistDTO();
		playlistDTO.setPlaylistId(null);
		playlistDTO.setPlaylistName("Ada's Playlist");
		return playlistDTO;
	}

	//create a new valid playlist
	@Test
	@Order(1)
	public void givenAPostOrder_WhenInsertAValidPlaylistName_ThenItShouldReturnStatus201Created() {
		PlaylistDTO playlistDTO = createPlaylistDTO();
		webTestClient
				.post()
				.uri("/playlists")
				.bodyValue(playlistDTO)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody();
		assertEquals(playlistDTO.getPlaylistName(),"Ada's Playlist");
	}

	//try to create a new playlist with the name null
	@Test
	@Order(2)
	public void givenAPostOrder_WhenInsertAInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		PlaylistDTO playlistDTO = createPlaylistDTO();
		playlistDTO.setPlaylistName(null);
		webTestClient
				.post()
				.uri("/playlists")
				.bodyValue(playlistDTO)
				.exchange()
				.expectStatus()
				.isBadRequest()
				.expectBody();
		assertNotEquals(playlistDTO.getPlaylistName(),"Ada's Playlist");
	}

	//List all playlists
	@Test
	@Order(3)
	public void givenAReadOrder_WhenReceivingAllThePlaylists_ThenItShouldReturnStatus200Ok() {
		webTestClient
				.get()
				.uri("/playlists")
				.exchange()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectStatus().isOk();
	}

	//Returns a playlist with a valid id
	@Test
	@Order(4)
	public void givenAReadOrder_WhenInsertAValidPlaylistId_ThenItShouldReturnStatus200Ok() {
		Integer id = 1;

		Playlist playlist = webTestClient
				.get().uri("/playlists/playlist?id=" + id)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Playlist.class)
				.returnResult()
				.getResponseBody();
		assertNotNull(playlist);
		assertEquals(playlist.getId(), id);
	}

	//change the name of a playlist
	@Test
	@Order(5)
	public void givenAPatchOrder_WhenInsertAValidPlaylistName_ThenItShouldReturnStatus200Ok() {
		int id = 1;
		PlaylistDTO playlistDTO = createPlaylistDTO();
		playlistDTO.setPlaylistId(1);
		playlistDTO.setPlaylistName("Test Playlist Order five");
		webTestClient
				.patch()
				.uri("/playlists/playlist/" + id)
				.bodyValue(playlistDTO)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody();
		assertEquals(playlistDTO.getPlaylistName(),"Test Playlist Order five");
	}

	//Error when changing the name of a playlist leaving the field null
	@Test
	@Order(6)
	public void givenAPatchOrder_WhenInsertAInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
		int id = 1;
		PlaylistDTO playlistDTO = createPlaylistDTO();
		playlistDTO.setPlaylistName(null);
		webTestClient
				.patch()
				.uri("/playlists/playlist/"+id)
				.bodyValue(playlistDTO)
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody();
		assertNotEquals(playlistDTO.getPlaylistName(),"Test Playlist Order five");
	}

	// Try to rename a playlist that doesn't exist
	@Test
	@Order(7)
	public void givenAPatchOrder_WhenInsertInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 0;
		PlaylistDTO playlistDTO = createPlaylistDTO();
		playlistDTO.setPlaylistName("Never Be");
		webTestClient
				.patch()
				.uri("/playlists/playlist/"+id)
				.bodyValue(playlistDTO)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody();
		assertNotNull(playlistDTO);
	}

	//delete a playlist
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
		assertEquals(id,2);
	}

	//search for a playlist by id that does not exist
	@Test
	@Order(9)
	public void givenAReadOrder_WhenInsertAInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 0;// if I change it to an ID that contains a registered Playlist, the test does not pass (proves that the method works)
		Playlist result = webTestClient
				.get()
				.uri("/playlists/playlist?id=" + id)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody(Playlist.class)
				.returnResult()
				.getResponseBody();
		assertEquals(result, result);
	}

	//Tries to delete a playlist that doesn't exist by its id
	@Test
	@Order(10)
	public void givenADeleteOrder_WWhenInsertAInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
		int id = 0;
		webTestClient
				.delete()
				.uri("/playlists" + id)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectHeader();
		assertNotEquals(id,2);
	}
}


