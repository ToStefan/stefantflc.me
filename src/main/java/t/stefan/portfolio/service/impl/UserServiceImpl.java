package t.stefan.portfolio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.repository.UserRepository;
import t.stefan.portfolio.service.UserService;
import t.stefan.portfolio.web.dto.UserDTO;
import t.stefan.portfolio.web.mapper.UserMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDTO(userRepository.findAll());
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDTO(userRepository.getOne(id));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public void remove(Long userId) {
        userRepository.deleteById(userId);
    }

}