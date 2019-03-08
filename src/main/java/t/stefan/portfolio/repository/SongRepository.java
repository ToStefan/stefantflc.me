package t.stefan.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import t.stefan.portfolio.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}
