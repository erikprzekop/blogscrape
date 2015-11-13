package blogscrape;

public class ContactInfo {
	private String linkedInInfo;
	private String twitterHandle;
	private String emailAddress;
	private String facebookAddress;
	private String blogAboutLink;
	private String link;
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ContactInfo() {
		linkedInInfo = "";
		twitterHandle = "";
		emailAddress = "";
		facebookAddress = "";
		blogAboutLink = "";
		link = "";
		author = "";
	}

	public String getLinkedIn() {
		return linkedInInfo;
	}

	public void setLinkedIn(String linkedInInfo) {
		this.linkedInInfo = linkedInInfo;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;

	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;

	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setFacebookAddress(String facebookAddress) {
		this.facebookAddress = facebookAddress;

	}

	public String getFacebookAddress() {
		return facebookAddress;
	}

	public void setBlogAboutMeLink(String blogAboutLink) {
		this.blogAboutLink = blogAboutLink;

	}

	public String getBlogAboutMeLink() {
		return blogAboutLink;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
