package t.stefan.portfolio.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import t.stefan.portfolio.entity.Collection;
import t.stefan.portfolio.entity.Image;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.repository.CollectionRepository;
import t.stefan.portfolio.repository.ImageRepository;
import t.stefan.portfolio.repository.UserRepository;
import t.stefan.portfolio.service.ImageService;
import t.stefan.portfolio.web.dto.ImageDTO;
import t.stefan.portfolio.web.mapper.ImageMapper;

import java.util.List;


@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final DropboxServiceImpl dropboxService;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;

    @Transactional
    @Override
    public ImageDTO add(MultipartFile file, String user, Long collectionId) {
        Collection c = collectionRepository.getOne(collectionId);
        User u = userRepository.findByUsername(user);

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setFile(file);
        imageDTO.setUser(user);
        imageDTO.setCollection(collectionId);
        imageDTO.createImageName(file.getOriginalFilename());
        imageDTO = dropboxService.uploadFile(imageDTO);

        Image image = imageMapper.toEntity(imageDTO);
        image.setCollection(c);
        image.setUser(u);

        imageRepository.save(image);
        return imageDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ImageDTO> findAllByCollection(Long collection) {
        return imageMapper.toDTO(imageRepository.findAllByCollectionId(collection));
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }
}
