package blogscrape;

import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BlogScraper {

	private static final int URL_PARM_INDEX = 0;
	private static final int KEYWORD_PARM_INDEX = 1;

	public static void main(String[] args) throws Exception {
		String urlParm = args[URL_PARM_INDEX];
		List<String> keywordsList = buildKeywordsFromArgs(args);
		Document doc = Jsoup.connect(urlParm).get();
		String body = doc.body().text();
		System.out.println(new BlogScraper().scrape(body, keywordsList));
	}

	private static List<String> buildKeywordsFromArgs(String[] args) {
		if (args.length == 2) {
			String keywords = args[KEYWORD_PARM_INDEX];
			if (keywords != null && !keywords.isEmpty()) {
				return Arrays.asList(keywords.split(","));
			}
		}
		return Arrays.asList("Clean Code");
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
