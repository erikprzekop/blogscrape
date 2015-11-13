package blogscrape;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentProvider {
	
	public Document getDocumentFromURL(String URL) throws IOException {
		return connect(URL);		
	}
	
	public Document connect(String url) throws IOException {
		return Jsoup.connect(url).get();
	}
	
	

}
