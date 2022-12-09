package br.inatel.project.playlist.management.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.project.playlist.management.domain.Song;
import br.inatel.project.playlist.management.dto.SongDTO;
import br.inatel.project.playlist.management.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongResource {

	@Autowired
	private SongService songService;

	// find one Song by id (GET)
	@GetMapping("/{id}")
	public ResponseEntity<Song> find(@PathVariable Integer id) {
		Song obj = songService.find(id);
		return ResponseEntity.ok().body(obj);
	}
//Tentando lançar BadRequestIdException caso o id fornecido for zero	
//	@GetMapping("/{id}")
//	public ResponseEntity<Song> find(@PathVariable Integer id) {
//		try {
//		Song obj = songService.find(id);
//		return ResponseEntity.ok().body(obj);
//		}catch (BadRequest e){
//			throw new BadRequestIdException("This Id is invalid, please use only numbers"+ "Type: " + Song.class.getName());
//		}
//	}
	

	// find all Songs in my bank (GET)
	@GetMapping
	public ResponseEntity<List<SongDTO>> findAll() {
		List<Song> list = songService.findAllSongs();
		List<SongDTO> listDTO = list.stream().map(obj -> new SongDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
