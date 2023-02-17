package br.inatel.project.playlist.management.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Dto representing the information the user will see about Track/Song in Api
 * link API: (https://www.last.fm/api/show/album.getInfo)
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Data
@Builder
public class TrackDTO {
    private String title;
    private String artist;
    private String duration;
    private String album;
    private String genre;

    public TrackDTO(String title, String artist, String duration, String album, String genre) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.genre = genre;
    }
}
