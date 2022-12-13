package br.inatel.project.playlist.management.dto;

import java.io.Serializable;

import javax.validation.Valid;

import br.inatel.project.playlist.management.domain.Song;

public class SongDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;
	
	private String music;
	
	private String artist;
	
	private String kindOfMusic;
	
	//Constructors

	public SongDTO() {
		super();
	}

	public SongDTO(Song obj) {
		
		id = obj.getId();
		music = obj.getMusic();
		artist = obj.getArtist();
		kindOfMusic = obj.getKindOfMusic();
	}
	
	//Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getKindOfMusic() {
		return kindOfMusic;
	}

	public void setKindOfMusic(String kindOfMusic) {
		this.kindOfMusic = kindOfMusic;
	}


}
