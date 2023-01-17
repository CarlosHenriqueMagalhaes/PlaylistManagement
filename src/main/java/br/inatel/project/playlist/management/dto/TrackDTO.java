package br.inatel.project.playlist.management.dto;
//https://rapidapi.com/theaudiodb/api/theaudiodb

//https://theaudiodb.com/

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data transfer object) for Stock Manager
 * 
 * @author carlos.magalhaes
 * @since
 */

public class TrackDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("idTrack")
	private String idTrack;// id da musica no banco externo
	private String strTrack;// nome da musica
	private String strAlbum;// nome do album
	private String strArtist;// nome do artista ou banda
	private String strGenre;// genero da musica

	public String getIdTrack() {
		return idTrack;
	}

	public void setIdTrack(String idTrack) {
		this.idTrack = idTrack;
	}

	public String getStrTrack() {
		return strTrack;
	}

	public void setStrTrack(String strTrack) {
		this.strTrack = strTrack;
	}

	public String getStrAlbum() {
		return strAlbum;
	}

	public void setStrAlbum(String strAlbum) {
		this.strAlbum = strAlbum;
	}

	public String getStrArtist() {
		return strArtist;
	}

	public void setStrArtist(String strArtist) {
		this.strArtist = strArtist;
	}

	public String getStrGenre() {
		return strGenre;
	}

	public void setStrGenre(String strGenre) {
		this.strGenre = strGenre;
	}

}
