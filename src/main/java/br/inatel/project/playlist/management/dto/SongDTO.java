package br.inatel.project.playlist.management.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.Valid;

import br.inatel.project.playlist.management.domain.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Valid
	private Integer id;
	private String music;
	private String artist;
	private String kindOfMusic;
	private String songAlbum;
	private String songDuration;

	// Constructors

	public SongDTO(Song obj) {
		id = obj.getId();
		music = obj.getMusic();
		artist = obj.getArtist();
		songDuration= obj.getSongDuration();
		songAlbum= obj.getSongAlbum();
		kindOfMusic = obj.getKindOfMusic();
	}

}
