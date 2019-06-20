package t.stefan.portfolio.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import t.stefan.portfolio.dto.SongDTO;
import t.stefan.portfolio.entity.Song;
import t.stefan.portfolio.service.SongService;

@RestController
@RequestMapping(value = "api/radio")
public class RadioController {

	Player radioPlayer;

	private static Song currentSong;

	@Autowired
	private SongService songService;

	@GetMapping(value = "/start-time")
	public ResponseEntity<?> startTime() {

		System.out.println("/api/radio/start-time");

		Integer startTime;

		try {
			startTime = radioPlayer.getPosition() / 1000;

		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Integer>(startTime, HttpStatus.OK);
	}

	@GetMapping(value = "/current-song")
	public ResponseEntity<SongDTO> currentSong() {

		System.out.println("/api/radio/current-song");

		Song song = songService.getById(currentSong.getId());
		SongDTO songDTO = new SongDTO(song);

		return new ResponseEntity<SongDTO>(songDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/play")
	public ResponseEntity<?> play() {

		System.out.println("/api/radio/play");

		List<Song> songs = songService.findAll();
		for (Song song : songs) {
			if (song.getPlayed() != true) {
				byte[] currentPlaying = Base64.getEncoder().encode(song.getSong());
				return new ResponseEntity<byte[]>(currentPlaying, HttpStatus.OK);
			}
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/start")
	public ResponseEntity<?> start() throws JavaLayerException {

		System.out.println("/api/radio/start");

		List<Song> songs = songService.findAll();
		songs.removeIf(s -> s.getPlayed() == true);

		InputStream file;

		for (Song song : songs) {
			currentSong = song;
			file = new ByteArrayInputStream(song.getSong());
			radioPlayer = new Player(file);
			radioPlayer.play();

			song.setPlayed(true);
			songService.save(song);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@GetMapping(value = "/stop")
	public ResponseEntity<?> stop() {

		System.out.println("/api/radio/stop");

		radioPlayer.close();

		List<Song> songs = songService.findAll();
		for (Song song : songs) {
			song.setPlayed(true);
			songService.save(song);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@GetMapping(value = "/refrash")
	public ResponseEntity<?> refrash() {

		System.out.println("/api/radio/refrash");

		List<Song> songs = songService.findAll();
		for (Song song : songs) {
			song.setPlayed(false);
			songService.save(song);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
