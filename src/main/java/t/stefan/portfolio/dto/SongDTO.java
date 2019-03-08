package t.stefan.portfolio.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import t.stefan.portfolio.entity.Song;

public class SongDTO implements Serializable {

	private Long id;
	private MultipartFile song;
	private Boolean played;
	private String title;

	public SongDTO() {

	}

	public SongDTO(Long id, MultipartFile song, Boolean played, String title) {
		this.id = id;
		this.song = song;
		this.played = played;
		this.title = title;
	}

	public SongDTO(Song song) {
		this(song.getId(), null, song.getPlayed(), song.getTitle());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MultipartFile getSong() {
		return song;
	}

	public void setSong(MultipartFile song) {
		this.song = song;
	}

	public Boolean getPlayed() {
		return played;
	}

	public void setPlayed(Boolean played) {
		this.played = played;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
