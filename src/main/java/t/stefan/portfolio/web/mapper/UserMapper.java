package t.stefan.portfolio.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.web.dto.UserDTO;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setIsEnabled(user.getIsEnabled());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRoles(user.getRoles());
        dto.setInformation(user.getInformation());
        return dto;
    }

    @Override
    public List<UserDTO> toDTO(List<User> entities) {
        return entities
                    .stream()
                    .map(user -> toDTO(user))
                    .collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User entity = new User();
        entity.setId(userDTO.getId());
        entity.setIsEnabled(userDTO.getIsEnabled());
        entity.setEmail(userDTO.getEmail());
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        entity.setRoles(userDTO.getRoles());
        entity.setInformation(userDTO.getInformation());
        return entity;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtos) {
        return dtos
                    .stream()
                    .map(userDTO -> toEntity(userDTO))
                    .collect(Collectors.toList());
    }

}