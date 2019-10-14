package t.stefan.portfolio.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import t.stefan.portfolio.service.impl.FcsArticleServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/admin-tools")
public class AdminToolsController {
	
	private final FcsArticleServiceImpl fcsArticleService;

	//@PreAuthorize("hasRole('MASTER')")
	@GetMapping(value = "/start-fcs-crawler")
	public ResponseEntity<HttpStatus> findAll() {
		fcsArticleService.startCrawler();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
