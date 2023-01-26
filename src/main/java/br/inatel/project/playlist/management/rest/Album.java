package br.inatel.project.playlist.management.rest;

import lombok.Data;
/**
 * Class with the information provided by the external API.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Data
public class Album {
    private String artist;
    private String title;
}
