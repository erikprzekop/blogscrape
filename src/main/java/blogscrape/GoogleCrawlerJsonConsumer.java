package blogscrape;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleCrawlerJsonConsumer {
	private List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
	private ContactInfoMiner miner;
	
	public GoogleCrawlerJsonConsumer(ContactInfoMiner miner) {
		this.miner = miner;
	}

	public GoogleCrawlerJsonConsumer() {
		this.miner = new ContactInfoMiner(new DocumentProvider());
	}

	public List<ContactInfo> mapJsonResponseToContactInfo(String googleSearchJsonResponse) throws Exception {
		JSONObject searchJsonObject = new JSONObject(googleSearchJsonResponse);
		
		JSONArray contactJsonArray = (JSONArray) searchJsonObject.get("items");
		for (int i = 0; i < contactJsonArray.length(); i++) {
			JSONObject contactJsonObject = (JSONObject) contactJsonArray.get(i);
			String blogLink = (String) contactJsonObject.get("link");
			ContactInfo contactInfo = buildContactInfo(blogLink);
			miner.mine(contactInfo);
			contactInfoList.add(contactInfo);
		}
		return contactInfoList;
	}

	private ContactInfo buildContactInfo(String blogLink) {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setLink(blogLink);
		return contactInfo;
	}

}
