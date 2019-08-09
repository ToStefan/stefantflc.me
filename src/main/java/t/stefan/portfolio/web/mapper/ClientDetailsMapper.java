package t.stefan.portfolio.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import t.stefan.portfolio.entity.ClientDetails;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.web.dto.ClientDetailsDTO;

@Component
public class ClientDetailsMapper implements Mapper<ClientDetails, ClientDetailsDTO> {

    @Override
    public ClientDetailsDTO toDTO(ClientDetails clientDetails) {
        ClientDetailsDTO dto = new ClientDetailsDTO();
        dto.setId(clientDetails.getId());
        dto.setDateTime(clientDetails.getDateTime());
        dto.setUserAgent(clientDetails.getUserAgent());
        dto.setLocation(clientDetails.getLocation());
        dto.setRegion(clientDetails.getRegion());
        dto.setCountry(clientDetails.getCountry());
        dto.setCity(clientDetails.getCity());
        dto.setIp(clientDetails.getIp());
        dto.setCount(clientDetails.getCount());
        dto.setUser(clientDetails.getUser());
        return dto;
    }

    @Override
    public List<ClientDetailsDTO> toDTO(List<ClientDetails> entities) {
        return entities
                    .stream()
                    .map(clientDetails -> toDTO(clientDetails))
                    .collect(Collectors.toList());
    }

    @Override
    public ClientDetails toEntity(ClientDetailsDTO clientDetailsDTO) {
        ClientDetails entity = new ClientDetails();
        entity.setId(clientDetailsDTO.getId());
        entity.setDateTime(clientDetailsDTO.getDateTime());
        entity.setUserAgent(clientDetailsDTO.getUserAgent());
        entity.setLocation(clientDetailsDTO.getLocation());
        entity.setRegion(clientDetailsDTO.getRegion());
        entity.setCountry(clientDetailsDTO.getCountry());
        entity.setCity(clientDetailsDTO.getCity());
        entity.setIp(clientDetailsDTO.getIp());
        entity.setCount(clientDetailsDTO.getCount());
        return entity;
    }

    @Override
    public List<ClientDetails> toEntity(List<ClientDetailsDTO> dtos) {
        return dtos
                    .stream()
                    .map(clientDetailsDTO -> toEntity(clientDetailsDTO))
                    .collect(Collectors.toList());
    }

}