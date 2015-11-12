package blogscrape;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BlogScraperTest {

	private BlogScraper blogScraper;

	@Before
	public void setup() {
		blogScraper = new BlogScraper();
	}
	
	@Test
	public void shouldReturnFalseWhenNoUrlPassed() throws Exception {
		Boolean actual = blogScraper.scrape("", "Clean Code");
		
		assertFalse(actual);
	}
	
	@Test
	public void shouldReturnFalseWhenUrlIsNull() throws Exception {
		Boolean actual = blogScraper.scrape(null, "Clean Code");

		assertFalse(actual);
	}
	
	@Test
	public void shouldReturnFalseWhenUrlDoesNotContainsCleanCodeInBodyText() throws Exception {
		Boolean actual = blogScraper.scrape("this is a body text without the term", "Clean Code");
		
		assertFalse(actual);
	}
	
	@Test
	public void shouldReturnTrueWhenUrlContainsCleanCodeInBodyText() throws Exception {
		Boolean actual = blogScraper.scrape("this is a body text with Clean code term", "Clean Code");
		
		assertTrue(actual);
	}
	
	@Test
	public void shouldReturnTrueWhenUrlContainsCleanCodeIgnoringCaseInBodyText() throws Exception {
		Boolean actual = blogScraper.scrape("this is a body text with ClEaN CoDe term", "ClEaN CoDe");
		
		assertTrue(actual);
	}
	

}
