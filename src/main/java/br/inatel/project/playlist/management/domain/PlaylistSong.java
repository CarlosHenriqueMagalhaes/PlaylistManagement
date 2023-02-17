package br.inatel.project.playlist.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity that will be saved in the database.
 * this class is the representation of a relational table between Song and Playlist
 *
 * @author Carlos Magalhães
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Song_Playlist")
public class PlaylistSong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "song_id")
    private Integer songId;
    @Column(name = "playlist_id")
    private Integer playlistId;
}
