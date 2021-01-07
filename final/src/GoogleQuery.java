import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;

import java.util.*;



/*import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements*/

public class GoogleQuery {

	public String url_google;
	
	public String Goolge = "https://www.google.com/search?q=";

	public String content;
	
	/*public GoogleQuery(){
		
		this.url_google = "https://www.google.com/search?q=top+gun+Tom+Scott+'film'or'series'or'television'or'movie'+-site:www.imdb.com+-site:www.rottentomatoes.com+-site:wikipedia.org+-site:www.pinterest.com+-site:www.amazon.com";	
	}
    */
	
	
	public GoogleQuery(String searchKeyword){
		
		this.url_google ="https://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num=20";	
	}

	

		/*private String fetchContent() throws IOException

		{
			String retVal = "";
	
			URL u = new URL(url_google);
	
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("User-agent","Chrome/7.0.517.44 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2)");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
			InputStream in = conn.getInputStream();
	
			InputStreamReader inReader = new InputStreamReader(in,"utf-8");
	
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;
	
			while((line=bufReader.readLine())!=null)
			{
				retVal += line;
	
			}
			return retVal;
		}*/
	
	 public List<UrlTempResult> fetchContent_url() throws IOException{
		  
			URL u = new URL(url_google);
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("User-agent","Chrome/7.0.517.44 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2)");
			
			try {
				Thread.sleep(2000);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String retVal = "";
			String line = null;
			
			while ((line = br.readLine()) != null){
				
			    retVal = retVal + line + "\n";
			    
			}
			
			//System.out.println(retVal);											//test line
			
			List<UrlTempResult> tempList = new ArrayList<UrlTempResult>();
			
			
			
			int headOfUrl=0;
			int tailOfUrl=0;
			String searchUrl = "";
			UrlTempResult temp;
			int numberOfUrl = 10;
			double urlWeight;
			
			for(double i = 0; i < numberOfUrl; i++) {
				
				
				
				urlWeight = (10-i)/10 ;
				
				
				if (i==0) {
					headOfUrl = retVal.indexOf("<a href=\"/url?q=");
				}
				else {
					headOfUrl = retVal.indexOf("<a href=\"/url?q=", tailOfUrl);
				}
																//test line
				tailOfUrl = retVal.indexOf("&amp", headOfUrl);
				searchUrl = retVal.substring(headOfUrl+16, tailOfUrl);
				temp = new UrlTempResult(searchUrl, urlWeight);
				tempList.add(temp);
				
			
			}
			//System.out.println(tempList);											//test line
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
		
		
		System.out.println();  						//test line
		System.out.println(doc2);
		System.out.println(url_google); 			//test line
		System.out.println();  	
		return doc2;

	}*/

		
	

}