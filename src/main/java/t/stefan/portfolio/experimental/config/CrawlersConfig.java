package t.stefan.portfolio.experimental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import t.stefan.portfolio.bridge.FcsCrawler;
import t.stefan.portfolio.experimental.factory.FcsCrawlerFactory;

@Configuration
public class CrawlersConfig {
	
	@Bean(name = "fcsCrawlerFactory")
	public FcsCrawlerFactory fcsCrawlerFactory() {
		return new FcsCrawlerFactory();
	}
	
	@Bean(name = "fcsCrawler")
	public FcsCrawler fcsCrawler() throws Exception {
		return fcsCrawlerFactory().getObject();
	}

}
