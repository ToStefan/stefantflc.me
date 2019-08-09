package t.stefan.portfolio.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        return UserPrincipal.create(user);
    }
}
