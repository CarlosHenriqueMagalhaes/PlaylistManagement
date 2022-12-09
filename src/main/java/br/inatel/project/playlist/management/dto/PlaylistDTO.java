package br.inatel.project.playlist.management.dto;

import java.io.Serializable;

import br.inatel.project.playlist.management.domain.Playlist;

public class PlaylistDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String playlistName;
	
	//Constructors

	public PlaylistDTO() {
		super();
	}

	public PlaylistDTO(Playlist obj) {
		super();
		id = obj.getId();
		playlistName = obj.getPlaylistName();
	}
	
	//Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	
}
