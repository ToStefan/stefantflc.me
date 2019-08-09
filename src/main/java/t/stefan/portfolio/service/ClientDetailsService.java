package t.stefan.portfolio.service;

import java.util.List;
import t.stefan.portfolio.web.dto.ClientDetailsDTO;

public interface ClientDetailsService {

    List<ClientDetailsDTO> findAllByIp(String ip);
    List<ClientDetailsDTO> findAllGroupByIp();
    ClientDetailsDTO findById(Long id);
    void create(ClientDetailsDTO clientdetailsDTO);
    void remove(Long id);
}

