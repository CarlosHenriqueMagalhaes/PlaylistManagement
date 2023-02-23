package br.inatel.project.playlist.management.dto;

import br.inatel.project.playlist.management.domain.Playlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Dto representing the information the user will see about playlist
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Valid
    private Integer playlistId;
    @NotNull(message = "filling in this field is mandatory")
    @NotEmpty(message = "filling in this field is mandatory")
    @Length(min = 2, max = 50, message = "The length of your playlist name must be between 2 and 50 characters")
    private String playlistName;

    /**
     * Constructors
     */
    public PlaylistDTO(Playlist playlist) {
        playlistId = playlist.getId();
        playlistName = playlist.getPlaylistName();
    }
}
