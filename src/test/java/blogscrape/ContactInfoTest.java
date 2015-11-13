package blogscrape;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContactInfoTest {
	@Test
	public void testLinkedInInitializedToEmptyString() throws Exception {
		ContactInfo info = new ContactInfo();
		String expected = "";
		String actual = info.getLinkedIn();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testLinkedInCanBeModified() throws Exception {
		ContactInfo info = new ContactInfo();
		String text = "Matthew";
		info.setLinkedIn(text);
		String actual = info.getLinkedIn();
		assertEquals(text, actual);
	}
	
	@Test
	public void testTwitterHandleGetAndSet() throws Exception {
		ContactInfo info = new ContactInfo();
		String expectedTwitterHandle = "@MrCleanCoder";
		info.setTwitterHandle(expectedTwitterHandle);
		String actual = info.getTwitterHandle();
		assertEquals(expectedTwitterHandle,actual);
	}
	
	@Test
	public void testTwitterHandleInitializeddToEmptyString() throws Exception {
		ContactInfo info = new ContactInfo();
		String expected = "";
		String actual = info.getTwitterHandle();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testEmailAddressGetAndSet() throws Exception {
		ContactInfo info = new ContactInfo();
		String expectedEmailAddress = "TheCleanestOfCodersWithTDD@gmail.com";
		info.setEmailAddress(expectedEmailAddress);
		String actual = info.getEmailAddress();
		assertEquals(expectedEmailAddress,actual);
		}
	
	@Test
	public void testEmailAddressInitializeddToEmptyString() throws Exception {
		ContactInfo info = new ContactInfo();
		String expected = "";
		String actual = info.getEmailAddress();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testFacebookAddressGetAndSet() throws Exception {
		ContactInfo info = new ContactInfo();
		String expectedFacebookAddress = "http://www.facebook.com/useruseruser";
		info.setFacebookAddress(expectedFacebookAddress);
		String actual = info.getFacebookAddress();
		assertEquals(expectedFacebookAddress,actual);
	}
	
	@Test
	public void testFacebookAddressInitializeddToEmptyString() throws Exception {
		ContactInfo info = new ContactInfo();
		String expected = "";
		String actual = info.getFacebookAddress();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testBlogAboutLinkGetAndSet() throws Exception {
		ContactInfo info = new ContactInfo();
		String expectedBlogAboutLink = "http://www.thebesttddblogoutthere.com/aboutme";
		info.setBlogAboutMeLink(expectedBlogAboutLink);
		String actual = info.getBlogAboutMeLink();
		assertEquals(expectedBlogAboutLink,actual);
	}
	
	@Test
	public void testBlogAboutInitializeddToEmptyString() throws Exception {
		ContactInfo info = new ContactInfo();
		String expected = "";
		String actual = info.getBlogAboutMeLink();
		assertEquals(expected,actual);
	}
}
