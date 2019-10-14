package t.stefan.portfolio.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import t.stefan.portfolio.service.impl.FcsArticleServiceImpl;
import t.stefan.portfolio.web.dto.FcsArticleDTO;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/fcs-news")
public class FcsNewsController {

	private final FcsArticleServiceImpl fcsArticleService;

	@GetMapping
	public ResponseEntity<List<FcsArticleDTO>> findAll() {
		List<FcsArticleDTO> articles = fcsArticleService.findAll();
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<FcsArticleDTO> findById(@PathVariable("id") Long id) {
		FcsArticleDTO article = fcsArticleService.findById(id);
		return new ResponseEntity<>(article, HttpStatus.OK);
	}

}
