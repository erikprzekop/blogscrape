package blogscrape;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GoogleCrawlerJsonConsumerTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldTestIfJsonResponseIsEmpty() throws Exception {
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		thrown.expect(RuntimeException.class);
		
		consumer.mapJsonResponseToContactInfo("");
	}
	
	@Test
	public void shouldTestIfJsonResponseIsNull() throws Exception {
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		thrown.expect(RuntimeException.class);
		
		consumer.mapJsonResponseToContactInfo(null);
	}
	
	@Test
	public void shouldTestIfInvalidJson() throws Exception {
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		thrown.expect(RuntimeException.class);
		
		consumer.mapJsonResponseToContactInfo("{\"invalidJson\" : }");
	}
	
	@Test
	public void shouldShouldReturnOneContactInfoWhenValidJsonStringPassed() throws Exception {
		String input = "["
				       + "{"
				       +  "\"title\": \"About Clean Code\","
				       +  "\"link\": \"https://somelink\","
				       +  "\"epoch_time\": \"1441191181\","
				       + "\"author\": \"admin\","
				       + "\"guid\": \" \","
				       + "\"content\": \"some text\""
				       + "}]";
		
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		
		List<ContactInfo> actualContactInfos = consumer.mapJsonResponseToContactInfo(input);
		
		assertEquals(1, actualContactInfos.size());
		ContactInfo contactInfo = actualContactInfos.get(0);
		assertEquals("https://somelink", contactInfo.getLink());
		assertEquals("admin", contactInfo.getAuthor());
	}
	
	@Test
	public void shouldShouldReturnTwoContactInfoWhenValidJsonStringPassed() throws Exception {
		String input = "["
				+ "{"
				+  "\"title\": \"About Clean Code 1\","
				+  "\"link\": \"https://somelink1\","
				+ "\"author\": \"Dan\","
				+ "\"guid\": \" \","
				+ "},"
				+ "{"
				+  "\"title\": \"About Clean Code 2\","
				+  "\"link\": \"https://somelink2\","
				+ "\"author\": \"Bob\","
				+ "\"guid\": \" \","
				+ "},"				
				+ "]";
		
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		
		List<ContactInfo> actualContactInfos = consumer.mapJsonResponseToContactInfo(input);
		
		assertEquals(2, actualContactInfos.size());
		ContactInfo contactInfo = actualContactInfos.get(0);
		assertEquals("https://somelink1", contactInfo.getLink());
		assertEquals("Dan", contactInfo.getAuthor());
		contactInfo = actualContactInfos.get(1);
		assertEquals("https://somelink2", contactInfo.getLink());
		assertEquals("Bob", contactInfo.getAuthor());
	}
	
	@Test
	public void shouldShouldReturnOneContactInfoWhenLinkIsEmptyAndGuidAttrHasLink() throws Exception {
		String input = "["
				       + "{"
				       +  "\"title\": \"About Clean Code\","
				       +  "\"link\": \" \","
				       +  "\"epoch_time\": \"1441191181\","
				       + "\"author\": \"admin\","
				       + "\"guid\": \"https://somelink\","
				       + "\"content\": \"some text\""
				       + "}]";
		
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer();
		
		List<ContactInfo> actualContactInfos = consumer.mapJsonResponseToContactInfo(input);
		
		assertEquals(1, actualContactInfos.size());
		ContactInfo contactInfo = actualContactInfos.get(0);
		assertEquals("https://somelink", contactInfo.getLink());
		assertEquals("admin", contactInfo.getAuthor());
	}
}
