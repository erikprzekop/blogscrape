package blogscrape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class GoogleBlogCrawler {

	public String getUrl() {
		GoogleCustomSearchQuery query = GoogleCustomSearchQuery.create().apiKey(GoogleCustomSearchQuery.API_KEY)
				.customEngineId(GoogleCustomSearchQuery.SEARCH_ENGINE_ID).query(GoogleCustomSearchQuery.QUERY)
				.pageSize(10);

		return query.getQuery();
	}

	public String crawl() throws MalformedURLException, IOException {
		InputStream inputStream = new URL(getUrl()).openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		String currentLine;
		StringBuilder sb = new StringBuilder();
		while ((currentLine = reader.readLine()) != null) {
			sb.append(currentLine);
		}
		return sb.toString();
	}

}
