package br.inatel.project.playlist.management.rest;

import lombok.Data;

import java.util.List;
/**
 * Class with the information provided by the external API.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Data
public class TopTags {
    private List<Tag> tag;
}
