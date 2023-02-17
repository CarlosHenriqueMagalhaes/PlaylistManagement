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

//unitary tests
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {
    private Playlist playlist;
    @InjectMocks
    private PlaylistService service;
    @Mock
    private PlaylistRepository repo;
    @Mock
    private PlaylistSongRepository plSgRepo;

    List<Playlist> plList = new ArrayList<>();

    @Before
    public void init() {
        playlist = Playlist.builder()
                .id(1)
                .playlistName("The Best")
                .build();
    }

    //Find All Playlists
    @Test
    public void givenAllPlaylists_WhenGetListAllPlaylist_ShouldReturnListOfPlaylist() {
        when(repo.findAll()).thenReturn(plList);
        service.findAllPlaylist();
        assertEquals(List.of(), plList);
    }

    //Success to find a playlist
    @Test
    public void givenAPlaylist_WhenGetAPlaylistById_ShouldReturnAPlaylist() {
        when(repo.findById(1)).thenReturn(Optional.of(playlist));
        service.find(1);
        assertEquals(playlist.getId(), 1);
    }

    //Failed to find a playlist
    @Test
    public void givenAPlaylist_WhenGetAPlaylistByAInvalidId() {
        when(repo.findById(3)).thenReturn(Optional.of(playlist));
        service.find(3);
        assertNotEquals(playlist.getId(), 3);
    }

    // Success to create a playlist
    @Test
    public void givenInsertANewPlaylists_WhenPostANewPlaylist_ShouldReturnANewPlaylist() {
        when(repo.save(playlist)).thenReturn(playlist);
        service.insert(playlist);
        playlist.setPlaylistName("Zoom Total");
        assertEquals(playlist.getPlaylistName(), "Zoom Total");
    }

    //Failed to create a playlist
    @Test
    public void givenInsertANewPlaylists_WhenPostAInvalidPlaylistName() {
        when(repo.save(playlist)).thenReturn(playlist);
        service.insert(playlist);
        playlist.setPlaylistName(null);
        assertNotEquals(playlist.getPlaylistName(), "Sleep Songs");
    }

    //Success in changing the name of a playlist
    @Test
    public void givenUpdateAPlaylistName_WhenPutAValidPlaylistId_ShouldReturnAPlaylist() {
        repo.findById(1);
        playlist.setPlaylistName("Loving Songs");
        assertEquals(playlist.getPlaylistName(), "Loving Songs");
    }

    // Failed to change the name of a playlist leaving playlistName null
    @Test
    public void givenUpdateAPlaylistName_WhenPutAInvalidPlaylistName_ShouldReturnAPlaylist() {
        repo.findById(1);
        playlist.setPlaylistName(null);
        assertNotEquals(playlist.getPlaylistName(), "The Best");
    }

    // Failed to rename a playlist when reporting a playlist that does not exist
    @Test
    public void givenUpdateAPlaylistName_WhenPutAInvalidPlaylistId_ShouldReturnAPlaylist() {
        repo.findById(1);
        playlist.setId(0);
        assertNotEquals(playlist.getId(), 1);
    }

    //Success to delete a playlist
    @Test
    public void givenDeleteAPlaylist_WhenDeleteAValidPlaylistById() {
        when(repo.findById(1)).thenReturn(Optional.of(playlist));
        plSgRepo.deleteAllInBatch();
        service.delete(1);
        assertEquals(playlist.getId(), 1);
    }

    //Failed to delete a playlist
    @Test
    public void givenDeleteAPlaylist_WhenDeleteAInvalidPlaylistById() {
        when(repo.findById(1)).thenReturn(Optional.of(playlist));
        plSgRepo.deleteAllInBatch();
        service.delete(1);
        assertNotEquals(playlist.getId(), 3);
    }
}


