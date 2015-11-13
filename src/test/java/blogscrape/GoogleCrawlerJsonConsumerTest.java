package blogscrape;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class GoogleCrawlerJsonConsumerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private ContactInfoMiner contactInfoMock;

	@Before
	public void setup() {
		contactInfoMock = Mockito.mock(ContactInfoMiner.class);
	}

	@Test
	public void shouldTestIfJsonResponseIsEmpty() throws Exception {
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer(contactInfoMock);
		thrown.expect(RuntimeException.class);

		consumer.mapJsonResponseToContactInfo("");
	}

	@Test
	public void shouldTestIfJsonResponseIsNull() throws Exception {
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer(contactInfoMock);
		thrown.expect(RuntimeException.class);

		consumer.mapJsonResponseToContactInfo(null);
	}

	@Test
	public void shouldTestIfInvalidJson() throws Exception {
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer(contactInfoMock);
		thrown.expect(RuntimeException.class);

		consumer.mapJsonResponseToContactInfo("{\"invalidJson\" : }");
	}

	@Test
	public void shouldShouldReturnOneContactInfoWhenValidJsonStringPassed() throws Exception {
		String input = "{" + "\"items\" : [" + "{" + "\"title\": \"About Clean Code\","
				+ "\"link\": \"https://somelink\"," + "\"content\": \"some text\"" + "}] }";
		when(contactInfoMock.mine(Mockito.any(ContactInfo.class))).thenAnswer(new Answer<ContactInfo>() {
			@Override
			public ContactInfo answer(final InvocationOnMock invocation) throws Throwable {
				return (ContactInfo) invocation.getArguments()[0];
			}
		});
		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer(contactInfoMock);

		List<ContactInfo> actualContactInfos = consumer.mapJsonResponseToContactInfo(input);

		assertEquals(1, actualContactInfos.size());
		ContactInfo contactInfo = actualContactInfos.get(0);
		assertEquals("https://somelink", contactInfo.getLink());
	}

	@Test
	public void shouldShouldReturnTwoContactInfoWhenValidJsonStringPassed() throws Exception {
		String input = "{" + "\"items\" : [" + "{" + "\"title\": \"About Clean Code 1\","
				+ "\"link\": \"https://somelink1\"," + "\"guid\": \" \"," + "}," + "{"
				+ "\"title\": \"About Clean Code 2\"," + "\"link\": \"https://somelink2\"," + "}," + "]}";
		
		when(contactInfoMock.mine(Mockito.any(ContactInfo.class))).thenAnswer(new Answer<ContactInfo>() {
			@Override
			public ContactInfo answer(final InvocationOnMock invocation) throws Throwable {
				return (ContactInfo) invocation.getArguments()[0];
			}
		});

		GoogleCrawlerJsonConsumer consumer = new GoogleCrawlerJsonConsumer(contactInfoMock);

		List<ContactInfo> actualContactInfos = consumer.mapJsonResponseToContactInfo(input);

		assertEquals(2, actualContactInfos.size());
		ContactInfo contactInfo = actualContactInfos.get(0);
		assertEquals("https://somelink1", contactInfo.getLink());
		contactInfo = actualContactInfos.get(1);
		assertEquals("https://somelink2", contactInfo.getLink());
	}

}
