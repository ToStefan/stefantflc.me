package t.stefan.portfolio.web.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import t.stefan.portfolio.entity.FcsArticle;
import t.stefan.portfolio.web.dto.FcsArticleDTO;

@Component
public class FcsArticleMapper implements Mapper<FcsArticle, FcsArticleDTO> {

	@Override
	public FcsArticleDTO toDTO(FcsArticle article) {
		FcsArticleDTO dto = new FcsArticleDTO();
		dto.setId(article.getId());
		dto.setContent(article.getContent());
		dto.setDate(article.getDate());
		dto.setDescription(article.getDescription());
		dto.setLink(article.getLink());
		dto.setPicture(article.getPicture());
		dto.setTitle(article.getTitle());
		dto.setType(article.getType());
		return dto;
	}
	
	@Override
	public List<FcsArticleDTO> toDTO(List<FcsArticle> entities) {
        return entities
                .stream()
                .map(article -> toDTO(article))
                .collect(Collectors.toList());
	}

	@Override
	public FcsArticle toEntity(FcsArticleDTO articleDTO) {
		FcsArticle entity = new FcsArticle();
		//entity.setId(articleDTO.getId());
		entity.setContent(articleDTO.getContent());
		entity.setDate(articleDTO.getDate());
		entity.setDescription(articleDTO.getDescription());
		entity.setLink(articleDTO.getLink());
		entity.setPicture(articleDTO.getPicture());
		entity.setTitle(articleDTO.getTitle());
		entity.setType(articleDTO.getType());
		return entity;
	}

	@Override
	public List<FcsArticle> toEntity(List<FcsArticleDTO> dtos) {
		return dtos
                .stream()
                .map(articleDTO -> toEntity(articleDTO))
                .collect(Collectors.toList());
	}

}
