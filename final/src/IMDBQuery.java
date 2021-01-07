import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;


public class IMDBQuery {

	public static String searchKeyword;
	
	public String url;
	
	public String content;
	
	public String IMDb = "https://www.imdb.com";
	
	public static List<Keyword> Directors = new ArrayList<Keyword>();
	
	public static List<Keyword> Writers = new ArrayList<Keyword>();
	
	public static List<Keyword> Stars = new ArrayList<Keyword>();
	

	
	public IMDBQuery(String searchKeyword){
		
		IMDBQuery.searchKeyword=searchKeyword;

		this.url = "https://www.imdb.com/find?q="+searchKeyword+"&oe=utf8&num=20";

		
	}

	
	 public String fetchContent_url() throws IOException{
		  //String retVal = "";
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
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
	
			int indexOfPhoto = retVal.indexOf("primary_photo");
			int indexOfhref = retVal.indexOf("href",indexOfPhoto);
			int indexOfclose = retVal.indexOf(">",indexOfhref);
			 
			String searchResult = IMDb + retVal.substring(indexOfhref+5, indexOfclose).replace("\"","");
			
			return searchResult; 
			
	    }
	    
	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(fetchContent_url());
		URLConnection conn = u.openConnection();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null){
			
			retVal += line;

		}
		
		return retVal;
	}
	
	public void query() throws IOException{
		
		if(content==null) {
			
			content=fetchContent();
			
		}
	
		Document doc=Jsoup.parse(content);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements doc2=doc.select("div.credit_summary_item");
		String doc3=doc2.text();
		String director=doc3.substring(doc3.indexOf(":")+1,doc3.indexOf("Writer"));
		String writers=doc3.substring(doc3.indexOf(":",doc3.indexOf(":")+1)+1,doc3.indexOf("Stars"));
		
		//System.out.println(doc2);												//test line
		//System.out.println(doc3);												//test line
		
		if(writers.contains("|")) {
			
		writers=doc3.substring(doc3.indexOf(":",doc3.indexOf(":")+1)+1,
				doc3.indexOf("|",doc3.indexOf(":",doc3.indexOf(":")+1)+1));
		
		}
		
		while (writers.contains("(")){
			
			int index1=writers.indexOf("(");
			int index2=writers.indexOf(")");
			String doc4=writers.substring(0,index1);
			String doc7=writers.substring(index2+1, writers.length());
			writers=doc4+doc7;
			
		}
		
		String stars=doc3.substring(doc3.indexOf(":",doc3.indexOf(":",doc3.indexOf(":")+1)+1)+1,
				doc3.indexOf("|",doc3.indexOf(":",doc3.indexOf(":",doc3.indexOf(":")+1)+1)+1));
		//System.out.println(doc3);						//test line
		//System.out.println(director);				    //test line
		//System.out.println(writers);					//test line
		//String mix=director+"\n"+writers+"\n"+stars;  //test line
		String[] arrayDirector = director.split(",");
		String[] arrayWriter = writers.split(",");
		String[] arrayStar = stars.split(",");
		//System.out.println(star[1]);					//test line
		addList(arrayDirector,Directors,"directors");
		addList(arrayWriter,Writers,"writer");
		addList(arrayStar,Stars,"stars");
		//System.out.println(Stars.get(0));   			//test line
		return;
	}
	
		private void addList(String [] inputArray, List<Keyword> outputList,
												String type){
			
			double typeWeight;
			double[] weightDir = {1, 0.7, 0.65};
			double[] weightWri = {1, 0.85, 0.8};
			double[] weightSta = {1, 0.9, 0.85};
			
			if (type.equals("directors")) {
				
				typeWeight = 2;
				
				for (int i = 0; i < inputArray.length;i++) {
					
					double weight = weightDir[i] * typeWeight;
					Keyword keyword = new Keyword(inputArray[i],weight);
						
					outputList.add(keyword);
				}
			}
			
			else if (type.equals("writer")) {
				
				typeWeight = 1.75;	
				
				for (int i = 0; i < inputArray.length;i++) {
					
					double weight = weightWri[i] * typeWeight;
					Keyword keyword = new Keyword(inputArray[i],weight);
						
					outputList.add(keyword);
				}
			}
			
			else {
				
				typeWeight = 1.66;
				
				for (int i = 0; i < inputArray.length;i++) {
					
					double weight = weightSta[i] * typeWeight;
					Keyword keyword = new Keyword(inputArray[i],weight);
						
					outputList.add(keyword);
				}
				
			}
			return;
		}
			
}
	