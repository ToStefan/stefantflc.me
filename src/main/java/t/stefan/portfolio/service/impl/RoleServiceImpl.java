package t.stefan.portfolio.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.repository.RoleRepository;
import t.stefan.portfolio.service.RoleService;
import t.stefan.portfolio.web.dto.RoleDTO;
import t.stefan.portfolio.web.mapper.RoleMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDTO> findAll() {
        return roleMapper.toDTO(roleRepository.findAll());
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMapper.toDTO(roleRepository.getOne(id));
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        return roleMapper.toDTO(roleRepository.save(roleMapper.toEntity(roleDTO)));
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        return roleMapper.toDTO(roleRepository.save(roleMapper.toEntity(roleDTO)));
    }

    @Override
    public void remove(Long roleId) {
        roleRepository.deleteById(roleId);
    }

}