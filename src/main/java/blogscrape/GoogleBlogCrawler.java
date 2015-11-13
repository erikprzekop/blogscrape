package blogscrape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

public class GoogleBlogCrawler {
	private GoogleCustomSearchQuery query;

	public GoogleBlogCrawler() {
		query = GoogleCustomSearchQuery.create().apiKey(GoogleCustomSearchQuery.API_KEY)
				.customEngineId(GoogleCustomSearchQuery.SEARCH_ENGINE_ID).query(GoogleCustomSearchQuery.QUERY)
				.pageSize(10);
	}

	public GoogleBlogCrawler(List<String> keywords) throws Exception {
		String queryString = "";
		for (String keyword : keywords) {
			if (queryString.length() > 0) {
				queryString += " OR ";
			}
			queryString += "\"" + keyword + "\"";
		}
		queryString = URLEncoder.encode(queryString, "UTF-8");
		query = GoogleCustomSearchQuery.create().apiKey(GoogleCustomSearchQuery.API_KEY)
				.customEngineId(GoogleCustomSearchQuery.SEARCH_ENGINE_ID).query(queryString).pageSize(10);
	}

	public String getUrl() {
		return query.getQuery();
	}

	public String crawl(int pageNumber) throws MalformedURLException, IOException {
		query.setPageNumber(pageNumber);
		String url = query.getQuery();
		InputStream inputStream = new URL(url).openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		String currentLine;
		StringBuilder sb = new StringBuilder();
		while ((currentLine = reader.readLine()) != null) {
			sb.append(currentLine);
		}
		return sb.toString();
	}

	public List<ContactInfo> crawlForContactInfo(int pageNumber) throws Exception {
		String rawResults = crawl(pageNumber);
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		return consumer.mapJsonResponseToContactInfo(rawResults);
	}

}
