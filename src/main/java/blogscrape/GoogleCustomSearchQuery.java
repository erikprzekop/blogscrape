package blogscrape;

public class GoogleCustomSearchQuery {

	private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";
	private StringBuilder queryString = new StringBuilder(BASE_URL);

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
		return queryString.toString();
	}

	private void addParameter(String parameterName, String parameterValue) {
		String separator = "&";
		if (queryString.toString().equals(BASE_URL)) {
			separator = "?";
		}
		queryString.append(separator).append(parameterName).append("=").append(parameterValue);

	}

}
