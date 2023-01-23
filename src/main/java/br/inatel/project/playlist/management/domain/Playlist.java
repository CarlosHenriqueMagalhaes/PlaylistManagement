package br.inatel.project.playlist.management.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import br.inatel.project.playlist.management.dto.PlaylistDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotEmpty(message = "filling in this field is mandatory")
	@Column(name = "playlistName") //(unique = true) 
	private String playlistName;

	@ManyToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
	private List<Song> songs = new ArrayList<>();

	// Constructors
	public Playlist(Integer id, String playlistName) {
		this.id = id;
		this.playlistName = playlistName;
	}

	// create a playlist through a DTO object
	public Playlist(PlaylistDTO playlistDTO) {
		this.playlistName = playlistDTO.getPlaylistName();
	}
}
