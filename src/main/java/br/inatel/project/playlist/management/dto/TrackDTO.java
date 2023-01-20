package br.inatel.project.playlist.management.dto;

//https://www.last.fm/api/show/album.getInfo

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackDTO implements Serializable {

    private String title;
    private String artist;
    private String duration;
    private String album;
    private String genre;




}
