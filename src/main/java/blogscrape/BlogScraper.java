package blogscrape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlogScraper {

	private static final int KEYWORD_PARM_INDEX = 0;
	private final GoogleBlogCrawler crawler;

	public BlogScraper(final GoogleBlogCrawler crawler) {
		this.crawler = crawler;
	}

	public static void main(String[] args) throws Exception {
		List<String> keywordsList = buildKeywordsFromArgs(args);
		BlogScraper blogScraper = new BlogScraper(new GoogleBlogCrawler());

		blogScraper.scrape(keywordsList);
	}

	public List<ContactInfo> scrape(List<String> keywordsList) throws Exception {
		List<ContactInfo> contacts = new ArrayList<ContactInfo>();
		for (int i = 1; i <= 2; i++) {
			contacts = crawler.crawlForContactInfo(i);

			printContactInfo(contacts);
		}
		return contacts;
	}

	private void printContactInfo(List<ContactInfo> contacts) {
		for (ContactInfo contactInfo : contacts) {
			System.out.println(contactInfo.toString());
		}
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
}
