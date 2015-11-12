package blogscrape;

import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BlogScraper {

	public static void main(String[] args) throws Exception {
		String urlParm = args[0];
		Document doc = Jsoup.connect(urlParm).get();
		String body = doc.body().text();
		System.out.println(new BlogScraper().scrape(body, Arrays.asList("Clean Code")));
	}

	public Boolean scrape(String bodyText, List<String> keywords) throws Exception {
		if (bodyText == null || bodyText.isEmpty()) {
			return false;
		}

		for (String word : keywords) {
			if (bodyText.toLowerCase().contains(word.toLowerCase()) == true) {
				return true;
			}
		}
		return false;
	}
}
