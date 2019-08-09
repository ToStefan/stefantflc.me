package t.stefan.portfolio.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.entity.ConfirmationToken;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.repository.ConfirmationTokenRepository;
import t.stefan.portfolio.repository.UserRepository;
import t.stefan.portfolio.service.ConfirmationTokenService;
import t.stefan.portfolio.web.dto.UserDTO;
import t.stefan.portfolio.web.mapper.UserMapper;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmTokenRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ConfirmationToken generateToken(User user) {
        ConfirmationToken ct = new ConfirmationToken(user);
        return confirmTokenRepository.save(ct);
    }

    @Override
    public Boolean confirmUser(String confirmationToken) {

        ConfirmationToken ct = confirmTokenRepository.findByConfirmationToken(confirmationToken);

        UserDTO user = userMapper.toDTO(userRepository.findByUsername(ct.getUser().getUsername()));
        user.setIsEnabled(true);
        userRepository.save(userMapper.toEntity(user));

        confirmTokenRepository.deleteById(ct.getId());

        return true;
    }
}
