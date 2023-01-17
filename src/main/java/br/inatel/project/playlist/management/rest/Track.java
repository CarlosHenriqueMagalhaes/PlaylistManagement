package br.inatel.project.playlist.management.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

	@JsonProperty("idTrack")
	private String idTrack;// id da musica no banco externo
	@JsonProperty("strTrack")
	private String strTrack;// nome da musica
	@JsonProperty("strAlbum")
	private String strAlbum;// nome do album
	@JsonProperty("strArtist")
	private String strArtist;// nome do artista ou banda
	@JsonProperty("strGenre")
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
