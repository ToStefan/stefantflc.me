package t.stefan.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import t.stefan.portfolio.entity.FcsArticle;

@Repository
public interface FcsArticleRepository extends JpaRepository<FcsArticle, Long> {

}
