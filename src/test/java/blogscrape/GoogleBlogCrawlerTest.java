package blogscrape;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

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

	/**
	 * INTEGRATION TEST - remove @Ignore to go to Google & get real results.
	 */
	@Test
	@Ignore
	public void getsJSONStringBackFromGoogle() throws Exception {
		GoogleBlogCrawler crawler = new GoogleBlogCrawler();
		List<ContactInfo> expected = crawler.crawl();

		assertTrue(expected.size() > 5);
	}
}
