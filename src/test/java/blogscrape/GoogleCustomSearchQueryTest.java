package blogscrape;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GoogleCustomSearchQueryTest {
	@Test
	public void addsKeyToQuery() {
		String queryString = "https://www.googleapis.com/customsearch/v1?"
				+ "key=AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.apiKey("AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM");
		assertEquals(queryString, googleCustomSearchQuery.getQuery());
	}

	@Test
	public void addsQueryStringToQuery() throws Exception {
		String queryString = "https://www.googleapis.com/customsearch/v1?"
				+ "q=%22clean+code%22+OR+TDD+OR+%22Pair+Programming%22";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.query("%22clean+code%22+OR+TDD+OR+%22Pair+Programming%22");
		assertEquals(queryString, googleCustomSearchQuery.getQuery());
	}

	@Test
	public void addsCustomSearchEngineStringToQuery() throws Exception {
		String queryString = "https://www.googleapis.com/customsearch/v1?" + "cx=000219294737032066243%3A0ubxwxlkb7o";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.customEngineId("000219294737032066243%3A0ubxwxlkb7o");
		assertEquals(queryString, googleCustomSearchQuery.getQuery());

	}

	@Test
	public void addsCustomSearchEngineAndKeyStringToQuery() throws Exception {
		String queryString = "https://www.googleapis.com/customsearch/v1?"
				+ "key=AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM&cx=000219294737032066243%3A0ubxwxlkb7o";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.apiKey("AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM")
				.customEngineId("000219294737032066243%3A0ubxwxlkb7o");
		assertEquals(queryString, googleCustomSearchQuery.getQuery());

	}

}
