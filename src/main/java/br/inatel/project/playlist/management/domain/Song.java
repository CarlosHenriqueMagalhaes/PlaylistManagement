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

import br.inatel.project.playlist.management.dto.TrackDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	@Column(name = "duration")
	private String songDuration;
	@Column(name = "album")
	private String songAlbum;
	@Column(name = "kindOfMusic")
	private String kindOfMusic;

	@ManyToMany
	@JoinTable(name = "Song_Playlist", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private List<Playlist> playlists = new ArrayList<>();

	// Constructors

	public Song(Integer id, String music,String artist,String songDuration, String songAlbum, String kindOfMusic) {
		this.id = id;
		this.music = music;
		this.songDuration = songDuration;
		this.artist = artist;
		this.songAlbum = songAlbum;
		this.kindOfMusic = kindOfMusic;
	}

	public Song(TrackDTO trackDTO) {
		this.id = null;
		this.music = trackDTO.getTitle();
		this.artist = trackDTO.getArtist();
		this.songDuration = trackDTO.getDuration();
		this.songAlbum =trackDTO.getAlbum();
		this.kindOfMusic = trackDTO.getGenre();
	}
//Getters and setters
	@JsonIgnore
	public List<Playlist> getPlaylists() {
		return playlists;
	}
}
