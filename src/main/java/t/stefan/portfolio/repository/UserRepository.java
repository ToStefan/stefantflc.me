package t.stefan.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import t.stefan.portfolio.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
