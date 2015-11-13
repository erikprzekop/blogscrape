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

	@Test
	public void testMineReturnsContactInfoWithEmailWhenFound() throws Exception {
		String expectedBodyText = "<div class='Content'>Some random information, here is my email: clean.coder@gmail.com, you can send me any comment there.</div>";
		ContactInfo info = new ContactInfo();
		Document doc = new Document("");
		doc.appendElement("body");
		doc.body().text(expectedBodyText);
		DocumentProvider mockDocumentProvider = Mockito.mock(DocumentProvider.class);
		Mockito.when(mockDocumentProvider.getDocumentFromURL(Mockito.anyString())).thenReturn(doc);
		
		ContactInfoMiner miner = new ContactInfoMiner(mockDocumentProvider);
		ContactInfo result = miner.mine(info);	
		assertEquals("clean.coder@gmail.com",result.getEmailAddress());
	}
	
	@Test
	public void testMineReturnsContactInfoWithFacebookLinkWhenFound() throws Exception {
		String expectedBodyText = "<div class='Content'>Some random information, find me on facebook! <a href='https://www.facebook.com/cleanest.coder'>, you can send me any comment there.</div>";
		ContactInfo info = new ContactInfo();
		Document doc = new Document("");
		doc.appendElement("body");
		doc.body().text(expectedBodyText);
		DocumentProvider mockDocumentProvider = Mockito.mock(DocumentProvider.class);
		Mockito.when(mockDocumentProvider.getDocumentFromURL(Mockito.anyString())).thenReturn(doc);
		
		ContactInfoMiner miner = new ContactInfoMiner(mockDocumentProvider);
		ContactInfo result = miner.mine(info);	
		assertEquals("https://www.facebook.com/cleanest.coder",result.getFacebookAddress());
	}
	
	@Test
	public void testMineReturnsContactInfoWithLinkedInLinkWhenFound() throws Exception {
		String expectedBodyText = "<div class='Content'>Some random information, find me on linkedIn! <a href='http://www.linkedin.com/in/cleanestcoder'>, you can send me any comment there.</div>";
		ContactInfo info = new ContactInfo();
		Document doc = new Document("");
		doc.appendElement("body");
		doc.body().text(expectedBodyText);
		DocumentProvider mockDocumentProvider = Mockito.mock(DocumentProvider.class);
		Mockito.when(mockDocumentProvider.getDocumentFromURL(Mockito.anyString())).thenReturn(doc);
		
		ContactInfoMiner miner = new ContactInfoMiner(mockDocumentProvider);
		ContactInfo result = miner.mine(info);	
		assertEquals("http://www.linkedin.com/in/cleanestcoder",result.getLinkedIn());
	}

}
