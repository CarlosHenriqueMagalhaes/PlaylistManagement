package br.inatel.project.playlist.management.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.project.playlist.management.domain.Playlist;

public class PlaylistDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer PlaylistId;

	@NotNull(message = "filling in this field is mandatory")
	@NotEmpty(message = "filling in this field is mandatory")
	@Length(min = 2, max = 50, message = "The length of your playlist name must be between 2 and 50 characters")
	private String playlistName;

	// Constructors

	public PlaylistDTO() {
	}

	public PlaylistDTO(Playlist obj) {
		PlaylistId = obj.getId();
		playlistName = obj.getPlaylistName();
	}
	
	// Getters and setters


	public Integer getPlaylistId() {
		return PlaylistId;
	}

	public void setPlaylistId(Integer playlistId) {
		this.PlaylistId = playlistId;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

}
