package t.stefan.portfolio.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import t.stefan.portfolio.entity.Collection;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.web.dto.CollectionDTO;

@Component
public class CollectionMapper implements Mapper<Collection, CollectionDTO> {

    @Override
    public CollectionDTO toDTO(Collection collection) {
        CollectionDTO dto = new CollectionDTO();
        dto.setId(collection.getId());
        dto.setName(collection.getName());
        dto.setDescription(collection.getDescription());
        dto.setUser(collection.getUser().getUsername());
        return dto;
    }

    @Override
    public List<CollectionDTO> toDTO(List<Collection> entities) {
        return entities
                .stream()
                .map(collection -> toDTO(collection))
                .collect(Collectors.toList());
    }

    @Override
    public Collection toEntity(CollectionDTO collectionDTO) {
        Collection entity = new Collection();
        entity.setId(collectionDTO.getId());
        entity.setName(collectionDTO.getName());
        entity.setDescription(collectionDTO.getDescription());
        return entity;
    }

    @Override
    public List<Collection> toEntity(List<CollectionDTO> dtos) {
        return dtos
                .stream()
                .map(collectionDTO -> toEntity(collectionDTO))
                .collect(Collectors.toList());
    }

}