package blogscrape;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

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
	public void buildsQueryFromKeywords() throws Exception {
		List<String> keywords = Arrays.asList("TDD", "\"Pair+Programming\"");
		GoogleBlogCrawler crawler = new GoogleBlogCrawler(keywords);
		String url = crawler.getUrl();
		assertThat(url).contains("TDD+OR+\"Pair+Programming\"");
	}

	/**
	 * INTEGRATION TESTS - remove @Ignore to go to Google & get real results.
	 */
	@Test
	@Ignore
	public void getsJSONStringBackFromGoogle() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		String expected = crawler.crawl(1);
		assertThat(expected).containsOnlyOnce("customsearch#search");
	}

	@Test
	@Ignore
	public void getsPage2() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		String expected = crawler.crawl(2);
		assertThat(expected).contains("\"startIndex\": 21");
	}
}
