package br.inatel.project.playlist.management.mapper;

import br.inatel.project.playlist.management.dto.TrackDTO;
import br.inatel.project.playlist.management.rest.FilterTrack;
import br.inatel.project.playlist.management.rest.Tag;

import java.util.stream.Collectors;
/**
 * This class maps Entity to Dto
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
public class Mapper {

    public static TrackDTO convertRestToDto(FilterTrack track){

        return TrackDTO.builder()
                .title(track.getTrack().getName())
                .artist(track.getTrack().getAlbum().getArtist())
                .duration(track.getTrack().getDuration())
                .album(track.getTrack().getAlbum().getTitle())
                .genre(track.getTrack().getTopTags().getTag().stream().map(Tag::getName).collect(Collectors.joining(", ")))
                .build();
    }
}
