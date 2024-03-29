package br.inatel.project.playlist.resources;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.form.TrackForm;
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
public class SongResourceTest {
    private final WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build();

    private TrackForm buildApiData() {
        TrackForm track = new TrackForm();
        track.setArtist("Metallica");
        track.setTrack("One");
        return track;
    }

    private SongDTO buildSongDTO() {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(100);
        songDTO.setMusic("The Great Gig in the Sky");
        songDTO.setArtist("Pink Floyd");
        songDTO.setKindOfMusic("Rock");
        songDTO.setSongAlbum("The Dark Side of the Moon");
        songDTO.setSongDuration("4430000");
        return songDTO;
    }

    private PlaylistDTO createPlaylistDTO() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setPlaylistId(null);
        playlistDTO.setPlaylistName("Best Songs");
        return playlistDTO;
    }

    @Test
    @Order(1)
    public void givenAPostOrder_WhenInsertAValidTrackAndAValidArtistToFindAndSaveASongFromAPIExternal_ThenItShouldReturnStatus201Created() {
        TrackForm songAp = buildApiData();
        Song song = webTestClient
                .post()
                .uri("/newSong")
                .bodyValue(songAp)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Song.class)
                .returnResult()
                .getResponseBody();
        assertEquals(songAp.getArtist(), "Metallica");
        assertEquals(songAp.getTrack(), "One");
    }

    @Test
    @Order(2)
    public void givenAPostOrder_WhenInsertAInvalidTrackAndOrAInvalidArtistToFindAndSaveASongFromAPIExternal_ThenItShouldReturnStatus404NotFound() {
        TrackForm songApi = buildApiData();
        songApi.setTrack("null");
        String result = webTestClient
                .post()
                .uri("/newSong")
                .bodyValue(songApi)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertEquals(songApi.getTrack(), "null");
        assert result != null;
        assertTrue(result.contains("The artist and track fields must exist and cannot be null."));
    }

    @Test
    @Order(3)
    public void givenAReadOrder_WhenReceivingAllTheSongs_ThenItShouldReturnStatus200Ok() {
        webTestClient
                .get()
                .uri("/songs")
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON).expectStatus().isOk();
        assertNotEquals(buildSongDTO(), null);
    }

    @Test
    @Order(4)
    public void givenAReadOrder_WhenInsertAValidSongId_ThenItShouldReturnStatus200Ok() {
        Integer id = 1;
        Song song = webTestClient
                .get()
                .uri("/song?id=" + id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Song.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(song);
        assertEquals(song.getId(), id);
        assertEquals(song.getArtist(), "Tim Maia");
        assertEquals(song.getMusic(), "O caminho do bem");
    }

    @Test
    @Order(5)
    public void givenAReadOrder_WhenInsertAInvalidId_ThenItShouldReturnStatus404NotFound() {
        int id = 0;
        String result = webTestClient
                .get()
                .uri("/song?id=" + id)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertEquals(result, result);
        assert result != null;
        assertTrue(result.contains("ObjectNotFound! This Song Id:0, does not exist or is not registered! "));
    }

    @Test
    @Order(6)
    public void givenAPostOrder_WhenInsertAValidPlaylistIdAndAValidSongId_ThenItShouldReturnStatus200Ok() {
        SongDTO song = buildSongDTO();
        song.setId(1);
        PlaylistDTO playlist = createPlaylistDTO();
        playlist.setPlaylistId(1);
        webTestClient
                .post()
                .uri("/song/" + song.getId() + "/playlist/" + playlist.getPlaylistId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
        assertEquals(song.getArtist(), "Pink Floyd");
        assertEquals(song.getMusic(), "The Great Gig in the Sky");
        assertEquals(playlist.getPlaylistName(), "Best Songs");
        assertNotNull(playlist);
    }

    @Test
    @Order(7)
    public void givenAPostOrder_WhenInsertAInvalidPlaylistIdAndOrAInvalidSongId_ThenItShouldReturnStatus404NotFound() {
        SongDTO song = buildSongDTO();
        song.setId(0);
        PlaylistDTO playlist = createPlaylistDTO();
        playlist.setPlaylistId(0);
        String result = webTestClient
                .post()
                .uri("/song/" + song.getId() + "/playlist/" + playlist.getPlaylistId())
                .bodyValue(playlist)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNotEquals(song.getArtist(), "Iron Maiden");
        assert result != null;
        assertTrue(result.contains("ObjectNotFound! This Song Id:0, does not exist or is not registered!"));
        assertEquals(playlist.getPlaylistName(), "Best Songs");
        assertNotNull(playlist);
    }

    @Test
    @Order(8)
    public void givenADeleteOrder_WhenInsertAValidPlaylistIdAndAValidSongId_ThenItShouldReturnStatus204NoContent() {
        SongDTO song = buildSongDTO();
        song.setId(1);
        PlaylistDTO playlist = createPlaylistDTO();
        playlist.setPlaylistId(1);
        webTestClient
                .delete()
                .uri("/song/" + song.getId() + "/playlist/" + playlist.getPlaylistId())
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectHeader();
        assertEquals(song.getArtist(), "Pink Floyd");
        assertEquals(song.getMusic(), "The Great Gig in the Sky");
        assertNotNull(playlist);
        assertEquals(playlist.getPlaylistName(), "Best Songs");
    }

    @Test
    @Order(9)
    public void givenADeleteOrder_WhenInsertAInvalidPlaylistIdAndAInvalidSongId_ThenItShouldReturnStatus404NotFound() {
        SongDTO song = buildSongDTO();
        song.setId(0);
        PlaylistDTO playlist = createPlaylistDTO();
        playlist.setPlaylistId(0);
        String result = webTestClient
                .delete()
                .uri("/song/" + song.getId() + "/playlist/" + playlist.getPlaylistId())
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(String.class).returnResult().getResponseBody();
        assertNotEquals(song.getArtist(), "Iron Maiden");
        assert result != null;
        assertTrue(result.contains("This song is not in this playlist or this playlist does not exist"));
    }
}
