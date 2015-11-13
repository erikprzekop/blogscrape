package blogscrape;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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
	public void acceptsPageLimit() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler(3);
		assertEquals(3, crawler.getLimit());
	}

	/**
	 * INTEGRATION TEST - remove @Ignore to go to Google & get real results.
	 */
	@Test
	@Ignore
	public void getsJSONStringBackFromGoogle() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		String expected = crawler.crawl(1);
		assertThat(expected).containsOnlyOnce("customsearch#search");
	}

	@Test
	public void getsPage2() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		String expected = crawler.crawl(2);
		assertThat(expected).contains("\"startIndex\": 21");
	}
}
