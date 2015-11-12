package blogscrape;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BlogScraperTest {

	private BlogScraper blogScraper;

	@Before
	public void setup() {
		blogScraper = new BlogScraper();
	}

	@Test
	public void shouldReturnFalseWhenNoBodyPassed() throws Exception {
		Boolean actual = blogScraper.scrape("", Arrays.asList("Clean Code"));

		assertFalse(actual);
	}

	@Test
	public void shouldReturnFalseWhenBodyIsNull() throws Exception {
		Boolean actual = blogScraper.scrape(null, Arrays.asList("Clean Code"));

		assertFalse(actual);
	}

	@Test
	public void shouldReturnFalseWhenBodyDoesNotContainCleanCodeInBodyText() throws Exception {
		Boolean actual = blogScraper.scrape("this is a body text without the term", Arrays.asList("Clean Code"));

		assertFalse(actual);
	}

	@Test
	public void shouldReturnTrueWhenBodyContainsCleanCodeInBodyText() throws Exception {
		Boolean actual = blogScraper.scrape("this is a body text with Clean code term", Arrays.asList("Clean Code"));

		assertTrue(actual);
	}

	@Test
	public void shouldReturnTrueWhenUrlContainsCleanCodeIgnoringCaseInBodyText() throws Exception {
		Boolean actual = blogScraper.scrape("this is a body text with ClEaN CoDe term", Arrays.asList("ClEaN CoDe"));

		assertTrue(actual);
	}

	@Test
	public void shouldReturnTrueWhenBodyContainsCleanCodeOrTDDInBodyText() throws Exception {
		List<String> keywords = new ArrayList<String>();
		keywords.add("Clean Code");
		keywords.add("TDD");

		Boolean actual = blogScraper.scrape("this is a body text with TDD term", keywords);

		assertTrue(actual);
	}

	@Test
	public void shouldReturnFalseWhenUrlDoesNotContainsCleanCodeOrTDDInBodyText() throws Exception {
		List<String> keywords = new ArrayList<String>();
		keywords.add("Clean Code");
		keywords.add("TDD");

		Boolean actual = blogScraper.scrape("this is a body text without terms", keywords);

		assertFalse(actual);
	}
	
	@Test
	public void shouldReturnTrueWhenBodyContainsCleanCodeOrTDDIgnoringCaseInBodyText() throws Exception {
		List<String> keywords = new ArrayList<String>();
		keywords.add("Clean Code");
		keywords.add("TDD");

		Boolean actual = blogScraper.scrape("this is a body text with TdD term", keywords);

		assertTrue(actual);
	}

}
