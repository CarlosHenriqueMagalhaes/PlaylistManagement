package br.inatel.project.playlist.management.dto;
//https://rapidapi.com/theaudiodb/api/theaudiodb

//https://theaudiodb.com/

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data transfer object) for Stock Manager
 * 
 * @author carlos.magalhaes
 * @since
 */
@Data
@Builder
public class TrackDTO implements Serializable {

    private String title;
    private String artist;
    private String duration;
    private String album;
    private String genre;




}
