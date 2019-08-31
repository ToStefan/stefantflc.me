package t.stefan.portfolio.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t.stefan.portfolio.entity.Collection;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.exception.EntityNotFoundException;
import t.stefan.portfolio.repository.CollectionRepository;
import t.stefan.portfolio.repository.UserRepository;
import t.stefan.portfolio.service.CollectionService;
import t.stefan.portfolio.web.dto.CollectionDTO;
import t.stefan.portfolio.web.mapper.CollectionMapper;
import t.stefan.portfolio.web.mapper.ImageMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {

    private final ImageMapper imageMapper;
    private final CollectionMapper collectionMapper;
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CollectionDTO> findAll() {
        List<Collection> collections = collectionRepository.findAll();
        List<CollectionDTO> dtos = collectionMapper.toDTO(collections);
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public CollectionDTO findById(Long id) {
        Collection collection = collectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        CollectionDTO dto = collectionMapper.toDTO(collection);
        dto.setImages(imageMapper.toDTO(collection.getImages()));
        return dto;
    }

    @Transactional
    @Override
    public CollectionDTO create(CollectionDTO collectionDTO) {
        User u = userRepository.findByUsername(collectionDTO.getUser());
        Collection collection = collectionMapper.toEntity(collectionDTO);
        collection.setUser(u);
        return collectionMapper.toDTO(collectionRepository.save(collection));
    }

    @Override
    public void remove(Long id) {
        collectionRepository.deleteById(id);
    }
}
