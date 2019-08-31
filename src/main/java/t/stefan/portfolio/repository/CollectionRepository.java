package t.stefan.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t.stefan.portfolio.entity.Collection;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}