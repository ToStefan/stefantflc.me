package t.stefan.portfolio.experimental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import t.stefan.portfolio.experimental.bridge.CrawlerService;
import t.stefan.portfolio.experimental.factory.CrawlerFactory;

@Configuration
public class CrawlerConfig {
	
	@Bean(name = "crawlerFactory")
	public CrawlerFactory fcsCrawlerFactory() {
		return new CrawlerFactory();
	}
	
	@Bean(name = "crawler")
	public CrawlerService fcsCrawler() throws Exception {
		return fcsCrawlerFactory().getObject();
	}

}
