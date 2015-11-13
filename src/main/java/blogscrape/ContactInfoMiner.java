package blogscrape;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

public class ContactInfoMiner {

	private DocumentProvider documentProvider;
	private Pattern twitterRegexPattern;
	private Pattern emailRegexPattern;
	private Pattern facebookRegexPattern;
	private Pattern linkedInRegexPattern;

	public ContactInfoMiner(DocumentProvider provider) {
		this.documentProvider = provider;
		this.twitterRegexPattern = Pattern.compile("@([a-z0-9_]+)",Pattern.CASE_INSENSITIVE);
		this.emailRegexPattern = Pattern.compile("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9._-]+)",Pattern.CASE_INSENSITIVE);
		this.facebookRegexPattern = Pattern.compile("(?:https?:\\/\\/)?(?:www\\.)?facebook\\.com\\/(?:(?:\\w)*#!\\/)?(?:pages\\/)?(?:[\\w\\-]*\\/)*([\\w\\-\\.]*)");
		this.linkedInRegexPattern = Pattern.compile("(?:http?:\\/\\/)?(?:www\\.)?linkedin\\.com\\/(?:(?:\\w)*#!\\/)?(?:pages\\/)?(?:[\\w\\-]*\\/)*([\\w\\-\\.]*)");
	}

	public ContactInfo mine(ContactInfo contactInfo) throws IOException {
		Document documentFromURL = documentProvider.getDocumentFromURL(contactInfo.getBlogAboutMeLink());
		String bodyText = documentFromURL.body().text();
		
		attemptTwitterRegexScrape(contactInfo,twitterRegexPattern,bodyText);
		attemptEmailRegexScrape(contactInfo,emailRegexPattern,bodyText);
		attemptFacebookRegexScrape	(contactInfo,facebookRegexPattern,bodyText);
		attemptLinkedInRegexScrape	(contactInfo,linkedInRegexPattern,bodyText);
		
		return contactInfo;
	}

	private void attemptTwitterRegexScrape(ContactInfo contactInfo,Pattern regexPattern ,String bodyText) {
		Matcher matcher = regexPattern.matcher(bodyText);
		if(matcher.find()) {
			contactInfo.setTwitterHandle(matcher.group(0));
		}
	}

	private void attemptEmailRegexScrape(ContactInfo contactInfo,Pattern regexPattern ,String bodyText) {
		Matcher matcher = regexPattern.matcher(bodyText);
		if(matcher.find()) {
			contactInfo.setEmailAddress(matcher.group(0));
		}
	}
	
	private void attemptFacebookRegexScrape(ContactInfo contactInfo,Pattern regexPattern ,String bodyText) {
		Matcher matcher = regexPattern.matcher(bodyText);
		if(matcher.find()) {
			contactInfo.setFacebookAddress(matcher.group(0));
		}
	}
	
	private void attemptLinkedInRegexScrape(ContactInfo contactInfo,Pattern regexPattern ,String bodyText) {
		Matcher matcher = regexPattern.matcher(bodyText);
		if(matcher.find()) {
			contactInfo.setLinkedIn(matcher.group(0));
		}
	}

	public DocumentProvider getDocumentProvider() {
		return documentProvider;
	}

}
