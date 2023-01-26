package br.inatel.project.playlist.management.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the information the user needs to find and save in Data base a new song
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackForm {
	private String artist;
	private String track;

}
