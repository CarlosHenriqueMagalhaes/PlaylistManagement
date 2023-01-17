package br.inatel.project.playlist.management.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.project.playlist.management.rest.Track;

@Service
public class Adapter {

	@Value("${X-RapidAPI-Key}")
	private String key;

	@Value("${X-RapidAPI-Host}")
	private String host;

	@Value("${X-RapidAPI-URI}")
	private String uri;

	public Track getTrack(String s, String t) {
		return WebClient.builder().baseUrl(uri).build().get()
				.uri(uriBuilder -> uriBuilder.queryParam("s", s)
						.queryParam("t", t)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.header("X-RapidAPI-Key",key)
				.header("X-RapidAPI-Host",host)
				.retrieve()
				.bodyToMono(Track.class)
				.block();
	}
}
