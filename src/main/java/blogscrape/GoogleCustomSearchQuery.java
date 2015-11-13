package blogscrape;

public class GoogleCustomSearchQuery {

	public static final String QUERY = "%22clean+code%22+OR+TDD+OR+%22Pair+Programming%22";
	public static final String SEARCH_ENGINE_ID = "000219294737032066243%3A0ubxwxlkb7o";
	public static final String API_KEY = "AIzaSyBtSmw4yAkt-iYUqbxWSKxhO9B_0hAwuUM";
	private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";
	private StringBuilder queryString = new StringBuilder(BASE_URL);
	private int pageSize = 10;
	private int currentEntry = 1;

	public static GoogleCustomSearchQuery create() {
		return new GoogleCustomSearchQuery();
	}

	public GoogleCustomSearchQuery query(String query) {
		addParameter("q", query);
		return this;
	}

	public GoogleCustomSearchQuery apiKey(String key) {
		addParameter("key", key);
		return this;
	}

	public GoogleCustomSearchQuery customEngineId(String engineId) {
		addParameter("cx", engineId);
		return this;
	}

	public String getQuery() {
		String query = queryString.toString();
		query += "&start=" + currentEntry;
		return query;
	}

	private void addParameter(String parameterName, String parameterValue) {
		String separator = "&";
		if (queryString.toString().equals(BASE_URL)) {
			separator = "?";
		}
		queryString.append(separator).append(parameterName).append("=").append(parameterValue);

	}

	public GoogleCustomSearchQuery pageSize(int pageSize) {
		this.pageSize = pageSize;
		addParameter("num", Integer.toString(pageSize));
		return this;
	}

	public void nextPage() {
		this.currentEntry += this.pageSize;
	}

	public void setPageNumber(int pageNumber) {
		currentEntry = (pageNumber - 1) * pageSize + 1;
	}

}
