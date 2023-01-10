package br.inatel.project.playlist.management.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "music")
	private String music;

	@Column(name = "artist")
	private String artist;

	@Column(name = "kindOfMusic")
	private String kindOfMusic;

	@ManyToMany
	@JoinTable(name = "Song_Playlist", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private List<Playlist> playlists = new ArrayList<>();

	// Constructors

	public Song() {
		super();
	}

	public Song(Integer id, String music, String artist, String kindOfMusic) {
		super();
		this.id = id;
		this.music = music;
		this.artist = artist;
		this.kindOfMusic = kindOfMusic;
	}

	// Getters and setters

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

	@JsonIgnore
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	// hashCode Equals

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(id, other.id);
	}

}
