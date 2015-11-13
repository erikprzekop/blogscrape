package blogscrape;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GoogleCustomSearchQueryTest {

	@Test
	public void addsKeyToQuery() {
		String queryString = "https://www.googleapis.com/customsearch/v1?"
				+ "key=AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.apiKey(GoogleCustomSearchQuery.API_KEY);
		assertThat(googleCustomSearchQuery.getQuery()).contains(queryString);
	}

	@Test
	public void addsQueryStringToQuery() throws Exception {
		String queryString = "https://www.googleapis.com/customsearch/v1?"
				+ "q=%22clean+code%22+OR+TDD+OR+%22Pair+Programming%22";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.query(GoogleCustomSearchQuery.QUERY);
		assertThat(googleCustomSearchQuery.getQuery()).contains(queryString);
	}

	@Test
	public void addsCustomSearchEngineStringToQuery() throws Exception {
		String queryString = "https://www.googleapis.com/customsearch/v1?" + "cx=000219294737032066243%3A0ubxwxlkb7o";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.customEngineId(GoogleCustomSearchQuery.SEARCH_ENGINE_ID);
		assertThat(googleCustomSearchQuery.getQuery()).contains(queryString);

	}

	@Test
	public void addsCustomSearchEngineAndKeyStringToQuery() throws Exception {
		String queryString = "https://www.googleapis.com/customsearch/v1?"
				+ "key=AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM&cx=000219294737032066243%3A0ubxwxlkb7o";
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create()
				.apiKey(GoogleCustomSearchQuery.API_KEY).customEngineId(GoogleCustomSearchQuery.SEARCH_ENGINE_ID);
		assertThat(googleCustomSearchQuery.getQuery()).contains(queryString);

	}

	@Test
	public void includesPageSize() throws Exception {
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create().pageSize(9);
		assertThat(googleCustomSearchQuery.getQuery()).contains("num=9");
	}

	@Test
	public void includesStartEntry() throws Exception {
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create();
		assertThat(googleCustomSearchQuery.getQuery()).contains("start=1");
	}

	@Test
	public void incrementsStartByPageSize() throws Exception {
		GoogleCustomSearchQuery googleCustomSearchQuery = GoogleCustomSearchQuery.create().pageSize(10);
		assertThat(googleCustomSearchQuery.getQuery()).contains("start=1");
		googleCustomSearchQuery.nextPage();
		assertThat(googleCustomSearchQuery.getQuery()).contains("start=11");

	}
}
