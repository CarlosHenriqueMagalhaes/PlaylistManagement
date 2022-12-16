package br.inatel.project.playlist.management.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.inatel.project.playlist.management.dto.PlaylistDTO;

@Entity
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String playlistName;

	@ManyToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
	private List<Song> songs = new ArrayList<>();

//	@ManyToOne
//	@JoinColumn(name= "user_id", nullable = true)
//	@JsonBackReference
//	private User user;

	// Constructors

	public Playlist() {
		super();
	}

	public Playlist(Integer id, String playlistName, Client user) {
		super();
		this.id = id;
		this.playlistName = playlistName;
//		this.user = user;
	}
	
	public Playlist(Integer id, String playlistName) {
		super();
		this.id = id;
		this.playlistName = playlistName;
	}

	// create a playlist through a DTO object 
	public Playlist(PlaylistDTO playlistDTO) {
		this.playlistName = playlistDTO.getPlaylistName();
	}

	// Getters and Setters
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

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	// HashCode and Equals

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
		Playlist other = (Playlist) obj;
		return Objects.equals(id, other.id);
	}

}
