package t.stefan.portfolio.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import t.stefan.portfolio.entity.Image;
import t.stefan.portfolio.web.dto.ImageDTO;

@Component
public class ImageMapper implements Mapper<Image, ImageDTO> {

    @Override
    public ImageDTO toDTO(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setName(image.getName());
        dto.setUrl(image.getUrl());
        return dto;
    }

    @Override
    public List<ImageDTO> toDTO(List<Image> entities) {
        return entities
                .stream()
                .map(image -> toDTO(image))
                .collect(Collectors.toList());
    }

    @Override
    public Image toEntity(ImageDTO imageDTO) {
        Image entity = new Image();
        entity.setId(imageDTO.getId());
        entity.setName(imageDTO.getName());
        entity.setUrl(imageDTO.getUrl());
        return entity;
    }

    @Override
    public List<Image> toEntity(List<ImageDTO> dtos) {
        return dtos
                .stream()
                .map(imageDTO -> toEntity(imageDTO))
                .collect(Collectors.toList());
    }

}