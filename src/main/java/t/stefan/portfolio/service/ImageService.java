package t.stefan.portfolio.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import t.stefan.portfolio.web.dto.ImageDTO;

public interface ImageService {
    ImageDTO add(MultipartFile file, String user, Long collectionId);
    List<ImageDTO> findAllByCollection(Long collection);
    void remove(Long id);
}

