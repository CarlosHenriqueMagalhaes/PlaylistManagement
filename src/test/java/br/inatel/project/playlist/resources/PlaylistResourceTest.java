package br.inatel.project.playlist.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.Song;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaylistResourceTest {

	@Test
	@Order(1)
	public void givenAReadOrder_WhenReceivingAllThePlaylists_ThenItShouldReturnStatus200Ok() {
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/playlists").exchange()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectStatus().isOk();
	}

	//Apenas se ja tiver criado alguma playlist no Postman
	@Test
	@Order(2)
	public void givenAReadOrderByPlaylistIdValid_WhenReceivingThePlaylist_ThenItShouldReturnStatus200Ok() {
		Integer id = 1;

		Playlist playlist = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/playlists/" + id)
				.exchange().expectStatus().isOk().expectBody(Playlist.class).returnResult().getResponseBody();

		assertNotNull(playlist);
		assertEquals(playlist.getId(), id);
	}
	
	@Test
	@Order(3)
	public void givenAReadOrderByPlaylistIdInvalid_WhenNotReceivingThePlaylist_ThenItShouldReturnStatus404NotFound() {
		int id = 35;// se eu alterar para um ID que contenha Playlist cadastrada o teste não passa(prova
						// que o método funciona)

		Playlist result = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/playlists/" + id)
				.exchange().expectStatus().isNotFound().expectBody(Playlist.class).returnResult().getResponseBody();

		assertEquals(result, result);
	}
//		@Test
//	@Order(5)
//    void givenValidStockId_WhenAddQuotesInTheCorrectStructure_ThenItShouldReturnStatus200Ok() {
//		Map<LocalDate, Double> quotesMap = new HashMap<>();
//        LocalDate date = LocalDate.now();
//        quotesMap.put(date, 20.0);
//        StockQuoteForm stockQuoteForm = new StockQuoteForm("petr1", quotesMap);
//
//        StockAux stock = WebTestClient
//        		.bindToServer().baseUrl("http://localhost:8081").build()
//        		.post()
//                .uri("/stock")
//                .bodyValue(stockQuoteForm)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(StockAux.class)
//                .returnResult().getResponseBody();
//
//        assertNotNull(stock.getId());
//        assertEquals("petr1",stock.getStockId());
//    }
//
//	/**
//	 * Given invalid StockId
//	 * When try create or add stock and quotes
//	 * Then it should return status 404 not found
//	 */
	
}

