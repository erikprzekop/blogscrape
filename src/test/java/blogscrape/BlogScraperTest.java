package blogscrape;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BlogScraperTest {

	private BlogScraper blogScraper;

	private GoogleBlogCrawler mockCrawler;

	@Before
	public void setup() {
		mockCrawler = mock(GoogleBlogCrawler.class);
		blogScraper = new BlogScraper(mockCrawler);
	}

	@Test
	public void shouldReturnListOfContactInfo() throws Exception {
		List<ContactInfo> expected = new ArrayList<ContactInfo>();
		ContactInfo contactInfo = new ContactInfo();
		expected.add(contactInfo);
		when(mockCrawler.crawlForContactInfo(1)).thenReturn(expected);

		List<ContactInfo> actual = blogScraper.scrape(Arrays.asList("Clean Code"));

		assertTrue(actual.size() == 1);
	}
}
