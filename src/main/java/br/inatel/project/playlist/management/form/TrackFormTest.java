package br.inatel.project.playlist.management.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackForm {

	private String artist;
	private String track;

//	public String getArtist() {
//		return artist;
//	}
//
//	public void setArtist(String band) {
//		this.artist = band;
//	}
//
//	public String getTrack() {
//		return track;
//	}
//
//	public void setTrack(String track) {
//		this.track = track;
//	}
//

}
