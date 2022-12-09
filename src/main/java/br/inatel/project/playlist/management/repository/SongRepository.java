package br.inatel.project.playlist.management.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.project.playlist.management.domain.Song;

@Repository
public interface SongRepository extends JpaRepository <Song,Integer> {
	
//	@Transactional (readOnly=true)
//	Page<Song>findDistincyByNomeContainingAdsPlaylistIn (String nome, List<Playlist> playlists,Pageable pageRequest);

}
