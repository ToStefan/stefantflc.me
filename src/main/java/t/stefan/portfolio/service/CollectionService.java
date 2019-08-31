package t.stefan.portfolio.service;

import t.stefan.portfolio.web.dto.CollectionDTO;

import java.util.List;

public interface CollectionService {

    List<CollectionDTO> findAll();
    CollectionDTO findById(Long id);
    CollectionDTO create(CollectionDTO collectionDTO);
    void remove(Long id);
}

