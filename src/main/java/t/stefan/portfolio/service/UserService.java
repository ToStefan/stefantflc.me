package t.stefan.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User getById(Long id) {
		return userRepository.getOne(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.deleteById(id);
	}
}
