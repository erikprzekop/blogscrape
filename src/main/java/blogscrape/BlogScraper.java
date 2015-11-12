package blogscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BlogScraper {
	
	public static void main(String[] args) throws Exception {
		String urlParm = args[0];
		Document doc = Jsoup.connect(urlParm).get();
		String body = doc.body().text();
		System.out.println(new BlogScraper().scrape(body, "Clean Code"));
	}

	public Boolean scrape(String bodyText, String keyword) throws Exception {
		if (bodyText == null || bodyText.isEmpty()) { return false; }

		return bodyText.toLowerCase().contains(keyword.toLowerCase());
	}
}
