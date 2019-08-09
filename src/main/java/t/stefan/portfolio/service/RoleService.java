package t.stefan.portfolio.service;

import java.util.List;
import t.stefan.portfolio.web.dto.RoleDTO;

public interface RoleService {

    List<RoleDTO> findAll();
    RoleDTO findById(Long id);
    RoleDTO create(RoleDTO roleDTO);
    RoleDTO update(RoleDTO roleDTO);
    void remove(Long id);

}

