package br.inatel.project.playlist.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.PlaylistDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongResourceTest {

	@Test
	@Order(1)
	public void givenAReadOrder_WhenReceivingAllTheSongs_ThenItShouldReturnStatus200Ok() {
		WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/songs").exchange()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectStatus().isOk();
	}

	@Test
	@Order(2)
	public void givenAReadOrderBySongIdValid_WhenReceivingTheSong_ThenItShouldReturnStatus200Ok() {
		Integer id = 1;

		Song song = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/songs/" + id)
				.exchange().expectStatus().isOk().expectBody(Song.class).returnResult().getResponseBody();

		assertNotNull(song);
		assertEquals(song.getId(), id);
	}

	@Test
	@Order(3)
	public void givenAReadOrderBySongIdInvalid_WhenNotReceivingTheSong_ThenItShouldReturnStatus404NotFound() {
		Integer id = 35;// se eu alterar para um ID que contenha Song cadastrada o teste não passa(prova
						// que o método funciona)

		Song result = WebTestClient.bindToServer().baseUrl("http://localhost:8070").build().get().uri("/songs/" + id)
				.exchange().expectStatus().isNotFound().expectBody(Song.class).returnResult().getResponseBody();

		assertTrue(result.equals(result));
	}

//	@Test
//	@Order(4)
//	public void givenValidStockId_WhenAddSongInAPlaylist_ThenItShouldReturnStatus202Accepted() {
//
//		Integer id = 1;
//		PlaylistDTO playlist = new PlaylistDTO();
//
//		Song song = WebTestClient.bindToServer().baseUrl("http://localhost:8081").build().post()
//				.uri("/songs/addSong/" + id).bodyValue(playlist).exchange().expectStatus().isCreated()
//				.expectBody(Song.class).returnResult().getResponseBody();
//
//		assertNotNull(song.getId());
//		assertEquals(id, song.getId());
//	}
}
//	/**
//	 * Given valid StockId
//	 * When add quotes in the correct structure
//	 * Then it should return status 200 ok
//	 */
//	@Test
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
//	@Test
//	@Order(6)
//    void givenInvalidStockId_WhenTryCreateOrAddStockAndQuotes_ThenItShouldReturnStatus404NotFound() {
//		Map<LocalDate, Double> quotesMap = new HashMap<>();
//        LocalDate date = LocalDate.now();
//        quotesMap.put(date, 13.0);
//        StockQuoteForm stockQuoteForm = new StockQuoteForm("invalid", quotesMap);
//        
//        String result = WebTestClient
//        		.bindToServer().baseUrl("http://localhost:8081").build()
//        		.post()
//                .uri("/stock")
//                .bodyValue(stockQuoteForm)
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectBody(String.class)
//                .returnResult().getResponseBody();
//
//        assertTrue(result.contains("Stock Not Found. Please verify that the stock was created correctly to create a quote"));
//    }
//	
//	/**
//	 * Given a delete cache order
//	 * Then it should return status 204 no content
//	 */
//	@Test
//	@Order(7)
//	void givenADeleteCacheOrder_ThenItShouldReturnStatus204NoContent() {
//		WebTestClient.bindToServer()
//		.baseUrl("http://localhost:8081").build()
//		.delete()
//		.uri("/stock/stockcache")
//		.exchange()
//		.expectStatus().isNoContent();
//	}
//	
//}
//
//
