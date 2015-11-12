package blogscrape;

import static org.junit.Assert.*;


import org.junit.Test;

public class ContactInfoMinerTest {
	@Test
	public void testThatMineReturnsContactInfoObject() throws Exception {
		ContactInfoMiner miner = new ContactInfoMiner();
		Object result = miner.mine("");
		assertEquals(ContactInfo.class,result.getClass());
	}
	
	@Test
	public void testMineReturnsContactInfoWithTwitterHandleWhenFound() throws Exception {
		ContactInfoMiner miner = new ContactInfoMiner();
		String expectedBodyText = "<div class='Content'>Some random information, here is my twitter handle: @MrCleanCoder you can follow my test driven tweets there.</div>";
		ContactInfo result = miner.mine(expectedBodyText);	
		assertEquals("@MrCleanCoder",result.getTwitterHandle());
	}

}
