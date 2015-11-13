package blogscrape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class GoogleBlogCrawler {
	private int maxPages = 1;
	private GoogleCustomSearchQuery query;

	public GoogleBlogCrawler(int maxPages) {
		this.maxPages = maxPages;
		query = GoogleCustomSearchQuery.create().apiKey(GoogleCustomSearchQuery.API_KEY)
				.customEngineId(GoogleCustomSearchQuery.SEARCH_ENGINE_ID).query(GoogleCustomSearchQuery.QUERY)
				.pageSize(10);
	}

	public GoogleBlogCrawler() {
		this(1);
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

	public List<ContactInfo> crawlForContactInfo(int pageNumber) throws MalformedURLException, IOException {
		String rawResults = crawl(pageNumber);
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		return consumer.mapJsonResponseToContactInfo(rawResults);
	}

	public int getLimit() {
		return this.maxPages;
	}

}
