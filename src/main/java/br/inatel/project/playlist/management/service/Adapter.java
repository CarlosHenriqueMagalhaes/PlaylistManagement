package br.inatel.project.playlist.management.service;

import br.inatel.project.playlist.management.rest.Rest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
/**
 * This class consumes the external API.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Service
public class Adapter {

	@Value("${API-Key}")
	private String key;

	@Value("${API-Url}")
	private String url;

	/**
	 * this method returns a Song instance with all its information
	 * @param track
	 * @param artist
	 * @return A Song innstace with all informations
	 */


	public Rest getRest(String track, String artist) {
		return WebClient.builder().baseUrl(url).build().get()
				.uri(uriBuilder -> uriBuilder
						.queryParam("api_key", key)
						.queryParam("artist", artist)
						.queryParam("track", track)
						.queryParam("format", "json")
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Rest.class)
				.block();
	}
}
