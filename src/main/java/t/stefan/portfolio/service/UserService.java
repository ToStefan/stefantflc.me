package t.stefan.portfolio.service;

import java.util.List;
import t.stefan.portfolio.web.dto.UserDTO;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void remove(Long id);

}

