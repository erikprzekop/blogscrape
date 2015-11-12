package blogscrape;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

public class GoogleBlogCrawlerTest {
	String jsonString = "";

	@Test
	public void getsUrlConnectionUsingGoogleCustomSearchQuery() throws Exception {
		GoogleCustomSearchQuery googleQuery = GoogleCustomSearchQuery.create().query("query").apiKey("apiKey")
				.customEngineId("customEngineId");
		HttpClient httpClient = mock(HttpClient.class);
		String uri = "http://my.thing.com";
		HttpResponse response = mock(HttpResponse.class);
		HttpEntity entity = mock(HttpEntity.class);
		HttpGet get = new HttpGet(uri);
		when(response.getEntity()).thenReturn(entity);
		when(httpClient.execute(get)).thenReturn(response);
		InputStream inputStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
		when(entity.getContent()).thenReturn(inputStream);
	}
}
