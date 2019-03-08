package t.stefan.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import t.stefan.portfolio.entity.Song;
import t.stefan.portfolio.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	private SongRepository songRep;

	public List<Song> findAll() {
		return songRep.findAll();
	}
	
	public Song getById(Long id) {
		return songRep.getOne(id);
	}

	public Song save(Song song) {
		return songRep.save(song);
	}

	public void remove(Long id) {
		songRep.deleteById(id);
	}
}
