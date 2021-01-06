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

public class GoogleQuery {

	public String url_google;
	
	public String Goolge = "https://www.google.com/search?q=";

	public String content;
	
	/*public GoogleQuery(){
		
		this.url_google = "https://www.google.com/search?q=top+gun+Tom+Scott";	
	}
    */
	
	
	public GoogleQuery(String searchKeyword){
		
		this.url_google = "https://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num=20";	
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
	
	 public List<UrlTempResult> fetchContent_url() throws IOException{
		  
			URL u = new URL(url_google);
			URLConnection conn = u.openConnection();
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String retVal = "";
			String line = null;
			
			while ((line = br.readLine()) != null){
				
			    retVal = retVal + line + "\n";
			    
			}
			
			List<UrlTempResult> tempList = new ArrayList<UrlTempResult>();
			
			int indexOfFir = retVal.indexOf("<a href=");
			int indexOfSec = retVal.indexOf("<a href=",indexOfFir);
			int indexOfThr = retVal.indexOf("<a href=",indexOfSec);
			int indexOfFour = retVal.indexOf("<a href=",indexOfThr);
			
			int firstUrl = retVal.indexOf("<a href=",indexOfFour);
			int closeFir = retVal.indexOf("\"",firstUrl);
			String searchUrl1 = retVal.substring(firstUrl+9, closeFir);
			UrlTempResult temp1 = new UrlTempResult(searchUrl1,1);
			tempList.add(temp1);
			
			int secondUrl = retVal.indexOf("<a href=",closeFir);
			int closeSec = retVal.indexOf("\"",secondUrl);
			String searchUrl2 = retVal.substring(secondUrl+9,closeSec);
			UrlTempResult temp2 = new UrlTempResult(searchUrl2,0.9);
			tempList.add(temp2);
			
			
			int thirdUrl = retVal.indexOf("<a href=",closeSec);
			int closethir = retVal.indexOf("\"",thirdUrl);
			String searchUrl3 = retVal.substring(thirdUrl+9,closethir);
			UrlTempResult temp3 = new UrlTempResult(searchUrl3,0.8);
			tempList.add(temp3);
			
			int fourthUrl = retVal.indexOf("<a href=",closethir);
			int closefour = retVal.indexOf("\"",fourthUrl);
			String searchUrl4 = retVal.substring(fourthUrl+9,closefour);
			UrlTempResult temp4 = new UrlTempResult(searchUrl4,0.7);
			tempList.add(temp4);
			
			int fifthUrl = retVal.indexOf("<a href=",closefour);
			int closefif = retVal.indexOf("\"",fifthUrl);
			String searchUrl5 = retVal.substring(fifthUrl+9,closefif);
			UrlTempResult temp5 = new UrlTempResult(searchUrl5,0.6);
			tempList.add(temp5);
			
			return tempList; 
			
	    }
	 
	/*public Elements query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		
		
		Document doc = Jsoup.parse(content);
		//System.out.println(content);				//test line
		Elements doc2 = doc.select(".kCrYT");
		//String doc3 = doc2.text();				//test line
		
		//System.out.println(url_google);  			//test line 
		System.out.println(doc2);
		return doc2;

	}*/

		
	

}