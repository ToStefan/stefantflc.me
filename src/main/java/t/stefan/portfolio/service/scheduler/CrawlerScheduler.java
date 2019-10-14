package t.stefan.portfolio.service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import t.stefan.portfolio.service.impl.FcsArticleServiceImpl;

@Slf4j
@AllArgsConstructor
@Component
public class CrawlerScheduler {
	
	private final FcsArticleServiceImpl fcsNewsService;
	
	@Scheduled(cron = "0 0 0/2 ? *")
	public void fcsCrawlerScheduler() {
		log.info("Fcs crawler started...");
		fcsNewsService.startCrawler();
	}

}
