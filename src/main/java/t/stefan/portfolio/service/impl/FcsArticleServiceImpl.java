package t.stefan.portfolio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import t.stefan.portfolio.repository.FcsArticleRepository;
import t.stefan.portfolio.service.FcsArticleService;
import t.stefan.portfolio.web.dto.FcsArticleDTO;
import t.stefan.portfolio.web.mapper.FcsArticleMapper;

@Service
public class FcsArticleServiceImpl implements FcsArticleService {

	@Value("${aws.basepath}")
	private String awsBasePath;

	private final FcsArticleRepository fcsArticleRep;
	private final FcsArticleMapper fcsArticleMapper;
	private final ObjectMapper objectMapper;

	public FcsArticleServiceImpl(FcsArticleRepository fcsArticleRep, FcsArticleMapper fcsArticleMapper,
			ObjectMapper objectMapper) {
		this.fcsArticleRep = fcsArticleRep;
		this.fcsArticleMapper = fcsArticleMapper;
		this.objectMapper = objectMapper;
	}

	@Transactional
	@Override
	public void startCrawler() {

		RestTemplate restTemplate = new RestTemplate();
		List<Object> response = restTemplate.getForObject(awsBasePath + "/fcs-crawler", List.class);
		List<FcsArticleDTO> articles = objectMapper.convertValue(response, new TypeReference<List<FcsArticleDTO>>() {});

		fcsArticleRep.deleteAll();
		fcsArticleRep.saveAll(fcsArticleMapper.toEntity(articles));
	}

	@Transactional(readOnly = true)
	@Override
	public List<FcsArticleDTO> findAll() {
		return fcsArticleMapper.toDTO(fcsArticleRep.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public FcsArticleDTO findById(Long id) {
		return fcsArticleMapper.toDTO(fcsArticleRep.getOne(id));
	}

}
