package blogscrape;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class DocumentProviderTest {

	@Test
	public void testReturnsDocumentFromURL() throws Exception {
		String expectedURL = "http://www.testurl.com";
		Document document = new Document(expectedURL);
		DocumentProvider documentProvider = spy(DocumentProvider.class);
		when(documentProvider.connect(expectedURL)).thenReturn(document);
		documentProvider.getDocumentFromURL(expectedURL);
		assertEquals(expectedURL,document.location());
		verify(documentProvider).connect(expectedURL);
	}
}
