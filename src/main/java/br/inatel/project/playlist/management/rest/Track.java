package br.inatel.project.playlist.management.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Track {

	private String name;
	private String mbid;
	private String duration;
	private Album album;
	@JsonProperty("toptags")
	private TopTags topTags;

}
