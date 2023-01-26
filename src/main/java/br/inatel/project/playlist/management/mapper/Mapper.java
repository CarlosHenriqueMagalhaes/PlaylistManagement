package br.inatel.project.playlist.management.mapper;

import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.rest.Rest;
import br.inatel.project.playlist.management.rest.Tag;

import java.util.stream.Collectors;
/**
 * This class maps Entity to Dto
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
public class Mapper {

    public static TrackDTO convertRestToDto(Rest rest){

        return TrackDTO.builder()
                .title(rest.getTrack().getName())
                .artist(rest.getTrack().getAlbum().getArtist())
                .duration(rest.getTrack().getDuration())
                .album(rest.getTrack().getAlbum().getTitle())
                .genre(rest.getTrack().getTopTags().getTag().stream().map(Tag::getName).collect(Collectors.joining(", ")))
                .build();
    }
}
