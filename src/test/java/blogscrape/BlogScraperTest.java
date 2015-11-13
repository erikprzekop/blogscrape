package blogscrape;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BlogScraperTest {

	private BlogScraper blogScraper;
	
	private GoogleBlogCrawler mockCrawler;

	@Before
	public void setup() {
		blogScraper = new BlogScraper();
		mockCrawler = Mockito.mock(GoogleBlogCrawler.class);
		blogScraper.setGoogleBlogCrawler(mockCrawler);
	}
	
	@Test
	public void shouldReturnListOfContactInfo() throws Exception {
		List<ContactInfo> expected = new ArrayList<ContactInfo>();
		ContactInfo contactInfo = new ContactInfo();
		expected.add(contactInfo);
		Mockito.when(mockCrawler.crawl()).thenReturn(expected);
		
		List<ContactInfo> actual = blogScraper.scrape(Arrays.asList("Clean Code"));

		assertTrue(actual.size() == 1);
	}
}
