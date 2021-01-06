import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;

import java.util.*;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQuery 

{

	public String searchKeyword;

	public String url_google;
	
	public String Goolge = "https://www.google.com/search?q=";

	public String content;
	
	
	
	

	public GoogleQuery(String searchKeyword)

	{
		
		
		this.searchKeyword = searchKeyword;
		
		this.url_google = "https://www.google.com/search?q=";
							
		

	}

	

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url_google);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public Elements query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		
		
		Document doc = Jsoup.parse(content);
		
		Elements doc2 = doc.select("div");
		//String doc3 = doc2.text();
		System.out.println(url_google);
		//System.out.println(doc);
		return doc2;

	}

		/*public String callGoogle() {
			
		}
		*/
	

}