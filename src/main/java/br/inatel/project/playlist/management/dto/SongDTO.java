package br.inatel.project.playlist.management.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.Valid;

import br.inatel.project.playlist.management.domain.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto representing the information the user will see about Song
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
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

	/**
	 *Constructors
	 */
	public SongDTO(Song song) {
		id = song.getId();
		music = song.getMusic();
		artist = song.getArtist();
		songDuration= song.getSongDuration();
		songAlbum= song.getSongAlbum();
		kindOfMusic = song.getKindOfMusic();
	}
}
