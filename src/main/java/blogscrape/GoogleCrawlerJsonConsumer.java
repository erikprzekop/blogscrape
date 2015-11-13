package blogscrape;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleCrawlerJsonConsumer {
	private List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();

	public List<ContactInfo> mapJsonResponseToContactInfo(String googleSearchJsonResponse) {
		JSONObject searchJsonObject = new JSONObject(googleSearchJsonResponse);
		JSONArray contactJsonArray = (JSONArray) searchJsonObject.get("items");
		for (int i = 0; i < contactJsonArray.length(); i++) {
			JSONObject contactJsonObject = (JSONObject) contactJsonArray.get(i);
			String blogLink = (String) contactJsonObject.get("link");
			contactInfoList.add(buildContactInfo(blogLink));
		}
		return contactInfoList;
	}

	private ContactInfo buildContactInfo(String blogLink) {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setLink(blogLink);
		return contactInfo;
	}

}
