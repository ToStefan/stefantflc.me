package t.stefan.portfolio.experimental.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import t.stefan.jython.bridge.CrawlerService;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/fcs")
public class FcsController {
	
	@Qualifier("crawler")
	private final CrawlerService crawler;
	
	@GetMapping(value = "/hello")
	public String index() {
		return crawler.hello();
	}

}
