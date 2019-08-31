package t.stefan.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t.stefan.portfolio.entity.ClientDetails;
import t.stefan.portfolio.web.dto.ClientDetailsDTO;

import java.util.List;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {

    List<ClientDetails> findAllByIp(String ip);

    @Query(value = "SELECT MAX(id) as id, COUNT(id) as count, date_time, user_agent, location, region, country, city, ip, user, path FROM client_details GROUP BY ip ORDER BY id DESC", nativeQuery = true)
    List<ClientDetails> findAllGroupByIp();
}