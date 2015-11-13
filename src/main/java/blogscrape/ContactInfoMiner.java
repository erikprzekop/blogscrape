package blogscrape;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

public class ContactInfoMiner {

	private DocumentProvider documentProvider;

	public ContactInfoMiner(DocumentProvider provider) {
		this.documentProvider = provider;
	}

	public ContactInfo mine(ContactInfo contactInfo) throws IOException {
		Document documentFromURL = documentProvider.getDocumentFromURL(contactInfo.getBlogAboutMeLink());
		String bodyText = documentFromURL.body().text();
		
		scrapeTwitterHandle(contactInfo, bodyText);
		return contactInfo;
	}

	private void scrapeTwitterHandle(ContactInfo contactInfo, String bodyText) {
		Matcher matcher = Pattern.compile("@([a-z0-9_]+)",Pattern.CASE_INSENSITIVE).matcher(bodyText);
		if(matcher.find()) {
			contactInfo.setTwitterHandle(matcher.group(0));
		}
	}

	public DocumentProvider getDocumentProvider() {
		return documentProvider;
	}

}
