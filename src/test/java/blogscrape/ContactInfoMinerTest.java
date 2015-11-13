package blogscrape;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.mockito.Mockito;

public class ContactInfoMinerTest {
	
	@Test
	public void testGetDocumentProvider() throws Exception {
		DocumentProvider provider = new DocumentProvider();
		ContactInfoMiner miner = new ContactInfoMiner(provider);
		DocumentProvider actualProvider = miner.getDocumentProvider();
		assertEquals(provider,actualProvider);
		
	}
	
	@Test
	public void testMineReturnsContactInfoWithTwitterHandleWhenFound() throws Exception {
		String expectedBodyText = "<div class='Content'>Some random information, here is my twitter handle: @MrCleanCoder you can follow my test driven tweets there.</div>";
		ContactInfo info = new ContactInfo();
		Document doc = new Document("");
		doc.appendElement("body");
		doc.body().text(expectedBodyText);
		DocumentProvider mockDocumentProvider = Mockito.mock(DocumentProvider.class);
		Mockito.when(mockDocumentProvider.getDocumentFromURL(Mockito.anyString())).thenReturn(doc);
		
		ContactInfoMiner miner = new ContactInfoMiner(mockDocumentProvider);
		ContactInfo result = miner.mine(info);	
		assertEquals("@MrCleanCoder",result.getTwitterHandle());
	}

}
