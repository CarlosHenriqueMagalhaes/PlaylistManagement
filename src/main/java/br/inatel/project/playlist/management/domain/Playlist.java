package br.inatel.project.playlist.management.domain;

import br.inatel.project.playlist.management.dto.PlaylistDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that will be saved in the database.
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "filling in this field is mandatory")
    @Column(name = "playlistName")
    private String playlistName;
    @ManyToMany(mappedBy = "playlists")
    private List<Song> songs = new ArrayList<>();

    /**
     * Constructors
     */
    public Playlist(Integer id, String playlistName) {
        this.id = id;
        this.playlistName = playlistName;
    }

    /**
     * create a playlist through a DTO object
     */
    public Playlist(PlaylistDTO playlistDTO) {
        this.playlistName = playlistDTO.getPlaylistName();
    }
}
