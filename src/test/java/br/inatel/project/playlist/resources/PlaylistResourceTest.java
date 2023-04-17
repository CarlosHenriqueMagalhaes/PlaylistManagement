package br.inatel.project.playlist.resources;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@ActiveProfiles ("test")
public class PlaylistResourceTest {
    private final WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build();

    private PlaylistDTO createPlaylistDTO() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setPlaylistId(null);
        playlistDTO.setPlaylistName("Ada's Playlist");
        return playlistDTO;
    }

    @Test
    @Order(1)
    public void givenAPostOrder_WhenInsertAValidPlaylistName_ThenItShouldReturnStatus201Created() {
        PlaylistDTO playlistDTO = createPlaylistDTO();
        webTestClient
                .post()
                .uri("/playlist")
                .bodyValue(playlistDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody();
        assertNull(playlistDTO.getPlaylistId());
        assertEquals(playlistDTO.getPlaylistName(), "Ada's Playlist");
    }

    @Test
    @Order(2)
    public void givenAPostOrder_WhenInsertAInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
        PlaylistDTO playlistDTO = createPlaylistDTO();
        playlistDTO.setPlaylistName(null);
        String result = webTestClient
                .post()
                .uri("/playlist")
                .bodyValue(playlistDTO)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNotEquals(playlistDTO.getPlaylistName(), "Ada's Playlist");
        assertNull(playlistDTO.getPlaylistId());
        assert result != null;
        assertTrue(result.contains("Invalid request"));
    }

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
        assertNotEquals(createPlaylistDTO(), null);
    }

    @Test
    @Order(4)
    public void givenAPatchOrder_WhenInsertAValidPlaylistName_ThenItShouldReturnStatus200Ok() {
        int id = 1;
        PlaylistDTO playlistDTO = createPlaylistDTO();
        playlistDTO.setPlaylistId(1);
        playlistDTO.setPlaylistName("Test Playlist Order five");
        webTestClient
                .patch()
                .uri("/playlist/" + id)
                .bodyValue(playlistDTO)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
        assertEquals(playlistDTO.getPlaylistName(), "Test Playlist Order five");
    }

    @Test
    @Order(5)
    public void givenAReadOrder_WhenInsertAValidPlaylistId_ThenItShouldReturnStatus200Ok() {
        Integer id = 1;
        Playlist playlist = webTestClient
                .get().uri("/playlist?id=" + id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Playlist.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(playlist);
        assertEquals(playlist.getId(), id);
        assertEquals(playlist.getPlaylistName(), "Test Playlist Order five");
    }

    @Test
    @Order(6)
    public void givenAPatchOrder_WhenInsertAInvalidPlaylistName_ThenItShouldReturnStatus400BadRequest() {
        int id = 1;
        PlaylistDTO playlistDTO = createPlaylistDTO();
        playlistDTO.setPlaylistName(null);
        String result = webTestClient
                .patch()
                .uri("/playlist/" + id)
                .bodyValue(playlistDTO)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNotEquals(playlistDTO.getPlaylistName(), "Test Playlist Order five");
        assert result != null;
        assertTrue(result.contains("Invalid request"));
    }

    @Test
    @Order(7)
    public void givenAPatchOrder_WhenInsertInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
        int id = 0;
        PlaylistDTO playlistDTO = createPlaylistDTO();
        playlistDTO.setPlaylistName("Never Be");
        String result = webTestClient
                .patch()
                .uri("/playlist/" + id)
                .bodyValue(playlistDTO)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNull(playlistDTO.getPlaylistId());
        assertNotNull(playlistDTO);
        assert result != null;
        assertTrue(result.contains("The playlist id:0 does not exist"));
    }

    @Test
    @Order(8)
    public void givenADeleteOrder_WhenInsertAValidPlaylistId_ThenItShouldReturnStatus204NoContent() {
        int id = 2;
        webTestClient
                .delete().uri("/playlist/" + id)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectHeader();
        assertEquals(id, 2);
    }

    @Test
    @Order(9)
    public void givenAReadOrder_WhenInsertAInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
        int id = 0;
        String result = webTestClient
                .get()
                .uri("/playlist?id=" + id)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class).returnResult().getResponseBody();
        assertEquals(result, result);
        assert result != null;
        assertTrue(result.contains("ObjectNotFound! This Playlist Id:0, does not exist!"));
    }

    @Test
    @Order(10)
    public void givenADeleteOrder_WhenInsertAInvalidPlaylistId_ThenItShouldReturnStatus404NotFound() {
        int id = 0;
        webTestClient
                .delete()
                .uri("/playlist" + id)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectHeader();
        assertNotEquals(id, 2);
    }
}


