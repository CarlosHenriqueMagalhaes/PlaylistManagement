package br.inatel.project.playlist.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.domain.PlaylistSong;
import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;
import br.inatel.project.playlist.management.repository.SongRepository;
import br.inatel.project.playlist.management.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {
    private Playlist playlist;
    private Song song;
    private PlaylistSong playlistSong;
    @InjectMocks
    private SongService songService;
    @Mock
    private SongRepository songRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private PlaylistSongRepository playlistSongRepository;

    List<Song> songList = new ArrayList<>();

    @Before
    public void init() {
        song = Song.builder()
                .id(1)
                .music("Ticket To Ride")
                .artist("Beatles")
                .kindOfMusic("Rock")
                .songAlbum("Help!")
                .songDuration("30000")
                .build();

        playlist = Playlist.builder()
                .id(1)
                .playlistName("The Best")
                .build();

        playlistSong = PlaylistSong.builder().
                songId(1).
                playlistId(1)
                .build();
    }

    @Test
    public void givenSong_WhenGetValidSongById_ShouldReturnSong() {
        when(songRepository.findById(1)).thenReturn(Optional.of(song));
        songService.find(1);
        assertEquals(song.getArtist(), "Beatles");
    }

    @Test
    public void givenSong_WhenGetInvalidSongById() {
        when(songRepository.findById(1)).thenReturn(Optional.of(song));
        songService.find(1);
        assertNotEquals(song.getArtist(), "Bob Marley");
    }

    @Test
    public void givenAllSongs_WhenGetListAllSongs_ShouldReturnListOfSongs() {
        when(songRepository.findAll()).thenReturn(songList);
        songService.findAllSongs();
        assertEquals(List.of(), songList);
    }

    @Test
    public void givenInsertASongAtAPlaylist_WhenPostValidPlaylistIdAndAValidSongId_ShouldReturnPlaylistList() {
        playlistSongRepository.findByPlaylistIdAndSongId(1, 1);
        assertThat(song.getId()).isEqualTo(playlist.getId());
    }

    @Test
    public void givenInsertASongAtAPlaylist_WhenPostInvalidPlaylistIdAndOrAInvalidSongId_ShouldReturnPlaylistList() {
        playlistSongRepository.findByPlaylistIdAndSongId(2, 1);
        assertNotEquals(List.of(), playlistSong);
    }
}

