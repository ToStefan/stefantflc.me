package t.stefan.portfolio.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.entity.ClientDetails;
import t.stefan.portfolio.repository.ClientDetailsRepository;
import t.stefan.portfolio.service.ClientDetailsService;
import t.stefan.portfolio.web.dto.ClientDetailsDTO;
import t.stefan.portfolio.web.mapper.ClientDetailsMapper;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final ClientDetailsRepository clientdetailsRepository;
    private final ClientDetailsMapper clientdetailsMapper;

    @Override
    public List<ClientDetailsDTO> findAllByIp(String ip) {
        return clientdetailsMapper.toDTO(clientdetailsRepository.findAllByIp(ip));
    }

    @Override
    public List<ClientDetailsDTO> findAllGroupByIp() {
        return clientdetailsMapper.toDTO(clientdetailsRepository.findAllGroupByIp());
    }

    @Override
    public ClientDetailsDTO findById(Long id) {
        return clientdetailsMapper.toDTO(clientdetailsRepository.getOne(id));
    }

    @Override
    public void create(ClientDetailsDTO clientDetailsDTO) {
        clientDetailsDTO.setDateTime(LocalDateTime.now());
        ClientDetails cd = clientdetailsMapper.toEntity(clientDetailsDTO);
        if(clientDetailsDTO.getUser() != null){
            cd.setUser(clientDetailsDTO.getUser());
        }
        clientdetailsRepository.save(cd);
    }

    @Override
    public void remove(Long clientDetailsId) {
        clientdetailsRepository.deleteById(clientDetailsId);
    }

}