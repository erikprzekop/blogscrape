package blogscrape;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class GoogleBlogCrawlerTest {
	String jsonString = "";

	@Test
	public void getsCompleteUrlWithEntryOneThroughTen() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		String url = crawler.getUrl();
		assertThat(url).contains("start=1");
		assertThat(url).contains("num=10");
	}

	@Test
	@Ignore
	public void getsJSONStringBackFromGoogle() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		String expected = crawler.crawl();
		assertThat(expected).contains("customsearch#search");
	}
}
