package t.stefan.portfolio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import t.stefan.portfolio.dto.SongDTO;
import t.stefan.portfolio.entity.Song;
import t.stefan.portfolio.service.SongService;

@RestController
@RequestMapping(value = "api/songs")
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping()
	public ResponseEntity<List<SongDTO>> getSongs() {

		List<SongDTO> songsDTO = new ArrayList<SongDTO>();
		List<Song> songs = songService.findAll();
		songs.removeIf(s -> s.getPlayed() == true);

		for (Song song : songs) {
			songsDTO.add(new SongDTO(song));
		}

		return new ResponseEntity<List<SongDTO>>(songsDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@ModelAttribute SongDTO songDTO) throws IOException {

		Song song = new Song();
		song.setSong(songDTO.getSong().getBytes());
		song.setPlayed(false);
		song.setTitle(songDTO.getTitle());

		songService.save(song);

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{song-id}")
	public ResponseEntity<?> delete(@PathVariable("song-id") Long id) {

		songService.remove(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
