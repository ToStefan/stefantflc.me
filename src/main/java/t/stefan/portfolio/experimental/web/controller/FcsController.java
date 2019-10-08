package t.stefan.portfolio.experimental.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import t.stefan.portfolio.experimental.bridge.CrawlerService;

@RestController
@RequestMapping(value = "/experimental/fcs")
public class FcsController {
	
	@Autowired
	@Qualifier("crawler")
	private CrawlerService crawler;
	
	@GetMapping(value = "/hello")
	public String index() {
		return crawler.hello();
	}

}
