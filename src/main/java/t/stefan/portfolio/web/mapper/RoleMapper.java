package t.stefan.portfolio.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import t.stefan.portfolio.entity.Role;
import t.stefan.portfolio.web.dto.RoleDTO;

@Component
public class RoleMapper implements Mapper<Role, RoleDTO> {

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    @Override
    public List<RoleDTO> toDTO(List<Role> entities) {
        return entities
                    .stream()
                    .map(role -> toDTO(role))
                    .collect(Collectors.toList());
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role entity = new Role();
        entity.setId(roleDTO.getId());
        entity.setName(roleDTO.getName());
        return entity;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtos) {
        return dtos
                    .stream()
                    .map(roleDTO -> toEntity(roleDTO))
                    .collect(Collectors.toList());
    }

}