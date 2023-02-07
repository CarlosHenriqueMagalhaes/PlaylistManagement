package br.inatel.project.playlist.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.form.TrackForm;
import br.inatel.project.playlist.management.repository.SongRepository;
import br.inatel.project.playlist.management.rest.Track;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.mockito.Mock;
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

import java.util.List;
//Integration Tests
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongResourceTest {

	@Autowired
	private SongRepository repo;

	@Autowired
	private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build();

	public TrackForm apiData() {
		TrackForm track = new TrackForm();
		track.setArtist("Metallica");
		track.setTrack("One");
		return track;
	}

	public SongDTO songDTO() {
		SongDTO songDTO = new SongDTO();
		songDTO.setId(100);
		songDTO.setMusic("The Great Gig in the Sky");
		songDTO.setArtist("Pink Floyd");
		songDTO.setKindOfMusic("Rock");
		songDTO.setSongAlbum("The Dark Side of the Moon");
		songDTO.setSongDuration("4430000");
		return songDTO;
	}

	public PlaylistDTO playlistDTO() {
		PlaylistDTO playlistDTO = new PlaylistDTO();
		playlistDTO.setPlaylistId(null);
		playlistDTO.setPlaylistName("Best Songs");
		return playlistDTO;
	}

//success finding a song in the external API
	@Test
	@Order(1)
	public void givenAPostOrder_WhenInsertAValidTrackAndAValidArtistToFindAndSaveASongFromAPIExternal_ThenItShouldReturnStatus201Created() {
		TrackForm songAp = apiData();
		Song song = webTestClient
				.post()
				.uri("/songs/findSong" )
				.bodyValue(songAp)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(Song.class)
				.returnResult()
				.getResponseBody();

		assertEquals(songAp.getArtist(),"Metallica");
	}

	//Failed to find a song in the external API
	@Test
	@Order(2)
	public void givenAPostOrder_WhenInsertAInvalidTrackAndOrAInvalidArtistToFindAndSaveASongFromAPIExternal_ThenItShouldReturnStatus404NotFound() {
		TrackForm songApi = apiData();
		songApi.setTrack("null");
		Song song = webTestClient
				.post()
				.uri("/songs/findSong" )
				.bodyValue(songApi)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody(Song.class)
				.returnResult()
				.getResponseBody();
	}

	//success listing all songs
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

	//success finding a song by id
	@Test
	@Order(4)
	public void givenAReadOrder_WhenInsertAValidSongId_ThenItShouldReturnStatus200Ok() {
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

	//failed to find a song by id
	@Test
	@Order(5)
	public void givenAReadOrder_WhenInsertAInvalidId_ThenItShouldReturnStatus404NotFound() {
		int id = 0;// if I change it to an ID that contains Song registered, the test does not pass (proves that the method works)

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

//success in adding a song to a playlist
	@Test
	@Order(6)
	public void givenAPostOrder_WhenInsertAValidPlaylistIdAndAValidSongId_ThenItShouldReturnStatus200Ok() {
		SongDTO song = songDTO();
		song.setId(1);
		PlaylistDTO playlist = playlistDTO();
		playlist.setPlaylistId(1);//leave an existing playlist set
		webTestClient
				.post()
				.uri("/songs/addSongAtPlaylist?playlistId=" + playlist.getPlaylistId() +"&songId=" + song.getId())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody();
	}

	//Failed to add a song to a playlist
	@Test
	@Order(7)
	public void givenAPostOrder_WhenInsertAInvalidPlaylistIdAndOrAInvalidSongId_ThenItShouldReturnStatus404NotFound() {
		SongDTO song = songDTO();
		song.setId(0);
		PlaylistDTO playlist = playlistDTO();
		playlist.setPlaylistId(0);
		webTestClient
				.post()
				.uri("/songs/addSongAtPlaylist?playlistId=" + playlist.getPlaylistId() +"&songId=" + song.getId())
				.bodyValue(playlist)
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectBody();
	}
	//success in removing a song from a playlist
	@Test
	@Order(8)
	public void givenADeleteOrder_WhenInsertAValidPlaylistIdAndAValidSongId_ThenItShouldReturnStatus204NoContent() {
		SongDTO song = songDTO();
		song.setId(1);
		PlaylistDTO playlist = playlistDTO();
		playlist.setPlaylistId(1);//leave an existing playlist set
		webTestClient
				.delete()
				.uri("/songs/removeSong?playlistId=" + playlist.getPlaylistId() +"&songId=" + song.getId())
				.exchange()
				.expectStatus()
				.isNoContent()
				.expectHeader();
	}

	//failed to remove a song from a playlist
	@Test
	@Order(9)
	public void givenADeleteOrder_WhenInsertAInvalidPlaylistIdAndAInvalidSongId_ThenItShouldReturnStatus404NotFound() {
		SongDTO song = songDTO();
		song.setId(0);
		PlaylistDTO playlist = playlistDTO();
		playlist.setPlaylistId(0);
		webTestClient
				.delete()
				.uri("/songs/removeSong?playlistId=" + playlist.getPlaylistId() +"&songId=" + song.getId())
				.exchange()
				.expectStatus()
				.isNotFound()
				.expectHeader();
	}
}
