package blogscrape;

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
		List<ContactInfo> contacts = crawler.crawlForContactInfo(1);

		for (ContactInfo contactInfo : contacts) {
			System.out.println(contactInfo.toString());
		}
		return contacts;
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
