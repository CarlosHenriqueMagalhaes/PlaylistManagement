package br.inatel.project.playlist.service;

import br.inatel.project.playlist.management.domain.Playlist;
import br.inatel.project.playlist.management.repository.PlaylistRepository;
import br.inatel.project.playlist.management.repository.PlaylistSongRepository;
import br.inatel.project.playlist.management.service.PlaylistService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {
    private Playlist playlist;
    @InjectMocks
    private PlaylistService playlistService;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private PlaylistSongRepository playlistSongRepository;

    List<Playlist> playlistList = new ArrayList<>();

    @Before
    public void init() {
        playlist = Playlist.builder()
                .id(1)
                .playlistName("The Best")
                .build();
    }

    @Test
    public void givenAllPlaylists_WhenGetListAllPlaylist_ShouldReturnListOfPlaylist() {
        when(playlistRepository.findAll()).thenReturn(playlistList);
        playlistService.findAllPlaylist();
        assertEquals(List.of(), playlistList);
    }

    @Test
    public void givenAPlaylist_WhenGetAPlaylistById_ShouldReturnAPlaylist() {
        when(playlistRepository.findById(1)).thenReturn(Optional.of(playlist));
        playlistService.find(1);
        assertEquals(playlist.getId(), 1);
    }

    @Test
    public void givenAPlaylist_WhenGetAPlaylistByAInvalidId() {
        when(playlistRepository.findById(3)).thenReturn(Optional.of(playlist));
        playlistService.find(3);
        assertNotEquals(playlist.getId(), 3);
    }

    @Test
    public void givenInsertANewPlaylists_WhenPostANewPlaylist_ShouldReturnANewPlaylist() {
        when(playlistRepository.save(playlist)).thenReturn(playlist);
        playlistService.insert(playlist);
        playlist.setPlaylistName("Zoom Total");
        assertEquals(playlist.getPlaylistName(), "Zoom Total");
    }

    @Test
    public void givenInsertANewPlaylists_WhenPostAInvalidPlaylistName() {
        when(playlistRepository.save(playlist)).thenReturn(playlist);
        playlistService.insert(playlist);
        playlist.setPlaylistName(null);
        assertNotEquals(playlist.getPlaylistName(), "Sleep Songs");
    }

    @Test
    public void givenUpdateAPlaylistName_WhenPutAValidPlaylistId_ShouldReturnAPlaylist() {
        playlistRepository.findById(1);
        playlist.setPlaylistName("Loving Songs");
        assertEquals(playlist.getPlaylistName(), "Loving Songs");
    }

    @Test
    public void givenUpdateAPlaylistName_WhenPutAInvalidPlaylistName_ShouldReturnAPlaylist() {
        playlistRepository.findById(1);
        playlist.setPlaylistName(null);
        assertNotEquals(playlist.getPlaylistName(), "The Best");
    }

    @Test
    public void givenUpdateAPlaylistName_WhenPutAInvalidPlaylistId_ShouldReturnAPlaylist() {
        playlistRepository.findById(1);
        playlist.setId(0);
        assertNotEquals(playlist.getId(), 1);
    }

    @Test
    public void givenDeleteAPlaylist_WhenDeleteAValidPlaylistById() {
        when(playlistRepository.findById(1)).thenReturn(Optional.of(playlist));
        playlistSongRepository.deleteAllInBatch();
        playlistService.delete(1);
        assertEquals(playlist.getId(), 1);
    }

    @Test
    public void givenDeleteAPlaylist_WhenDeleteAInvalidPlaylistById() {
        when(playlistRepository.findById(1)).thenReturn(Optional.of(playlist));
        playlistSongRepository.deleteAllInBatch();
        playlistService.delete(1);
        assertNotEquals(playlist.getId(), 3);
    }
}


