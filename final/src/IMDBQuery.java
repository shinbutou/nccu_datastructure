import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
		
		IMDBQuery.searchKeyword=searchKeyword.trim().replace(" ","+");

		this.url = "https://www.imdb.com/find?q="+searchKeyword+"&oe=utf8&num=20";

	}

	
	 public String fetchContent_url() throws IOException{
		  //String retVal = "";
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
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
		Elements doc2=doc.select("div.credit_summary_item");
		String doc3=doc2.text();
		String director=doc3.substring(doc3.indexOf(":")+1,doc3.indexOf("Writer"));
		String writers=doc3.substring(doc3.indexOf(":",doc3.indexOf(":")+1)+1,doc3.indexOf("Stars"));
		
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
			double priorityWeight = 1;
			double typeWeight;
			
			if (type.equals("directors")) {
				
				typeWeight = 2;
				
				if (inputArray.length == 3) {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					outputList.add(new Keyword(inputArray[1],typeWeight*0.7));
					outputList.add(new Keyword(inputArray[2],typeWeight*0.65));
					
				}
				
				else if (inputArray.length == 2) {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					outputList.add(new Keyword(inputArray[1],typeWeight*0.7));
				}
				
				else {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					
				}
				
			}
			
			else if (type.equals("writer")) {
				
				typeWeight = 1.75;
				
				if (inputArray.length == 3) {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					outputList.add(new Keyword(inputArray[1],typeWeight*0.85));
					outputList.add(new Keyword(inputArray[2],typeWeight*0.8));
					
				}
				
				else if (inputArray.length == 2) {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					outputList.add(new Keyword(inputArray[1],typeWeight*0.85));
				}
				
				else {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					
				}
				
				
			}
			
			else {
				
				typeWeight = 1.66;
				
				if (inputArray.length == 3) {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					outputList.add(new Keyword(inputArray[1],typeWeight*0.9));
					outputList.add(new Keyword(inputArray[2],typeWeight*0.85));
					
				}
				
				else if (inputArray.length == 2) {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					outputList.add(new Keyword(inputArray[1],typeWeight*0.9));
				}
				
				else {
					
					outputList.add(new Keyword(inputArray[0],typeWeight*1));
					
				}
				
				
			}
			
			
			/*for (String element:inputArray) {
				
				double weight = priorityWeight * typeWeight;
				Keyword keyword = new Keyword(element,weight);
				
				outputList.add(keyword);
				
				if (type.equals("directors")) {
					
					priorityWeight -= 0.3;
					
				}
				
				else if (type.equals("writer")) {
					
					priorityWeight -= 0.15;
					
				}
				
				else {
					
					priorityWeight -= 0.1;
					
				}
			}*/
			
			return;
		}
			
}
	