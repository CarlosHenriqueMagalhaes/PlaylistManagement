package br.inatel.project.playlist.management.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class with the information provided by the external API.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Data
public class Track {
	private String name;
	private String mbid;
	private String duration;
	private Album album;
	@JsonProperty("toptags")
	private TopTags topTags;
}
