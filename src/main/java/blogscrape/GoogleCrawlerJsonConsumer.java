package blogscrape;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleCrawlerJsonConsumer {
	private List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();

	public List<ContactInfo> mapJsonResponseToContactInfo(String googleSearchJsonResponse) {
		JSONArray contactJsonArray = new JSONArray(googleSearchJsonResponse);

		for (int i = 0; i < contactJsonArray.length(); i++) {
			JSONObject contactJsonObject = (JSONObject) contactJsonArray.get(i);
			String blogAuthor = (String) contactJsonObject.get("author");
			String blogLink = (String) contactJsonObject.get("link");

			if (blogLink.trim().isEmpty()) {
				blogLink = (String) contactJsonObject.get("guid");
			}
			contactInfoList.add(buildContactInfo(blogAuthor, blogLink));
		}
		return contactInfoList;
	}

	private ContactInfo buildContactInfo(String blogAuthor, String blogLink) {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setLink(blogLink);
		contactInfo.setAuthor(blogAuthor);
		return contactInfo;
	}

}
