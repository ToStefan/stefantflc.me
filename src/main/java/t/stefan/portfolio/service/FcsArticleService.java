package t.stefan.portfolio.service;

import java.util.List;

import t.stefan.portfolio.web.dto.FcsArticleDTO;


public interface FcsArticleService {
	
	void startCrawler();
    List<FcsArticleDTO> findAll();
    FcsArticleDTO findById(Long id);

}
