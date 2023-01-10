package br.inatel.project.playlist.management.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.inatel.project.playlist.management.dto.PlaylistDTO;

@Entity
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotEmpty(message = "filling in this field is mandatory")
	@Column(unique = true, name = "playlistName")
	private String playlistName;

	@ManyToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
	private List<Song> songs = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = true)
	@JsonBackReference
	private Client client;

	// Constructors

	public Playlist() {
		super();
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
