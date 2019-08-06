package t.stefan.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t.stefan.portfolio.entity.ClientDetails;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {

}