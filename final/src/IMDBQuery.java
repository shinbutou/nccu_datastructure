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
import java.util.*
;public class IMDBQuery {

	//public String searchKeyword;

	public String url;
	public String content;
	public String IMDb = "https://www.imdb.com";
	public List<Keyword> Directors = new ArrayList<Keyword>();
	public List<Keyword> Writers = new ArrayList<Keyword>();
	public List<Keyword> Stars = new ArrayList<Keyword>();
	public String searchKeyword;
	//public Scanner scan=new Scanner(System.in);
	
	public IMDBQuery(String searchKeyword)
	{
		//searchKeyword=scan.next();
		this.searchKeyword=searchKeyword;

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
			while ((line = br.readLine()) != null)
			{
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

		while((line=bufReader.readLine())!=null)
		{
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
		writers=doc3.substring(doc3.indexOf(":",doc3.indexOf(":")+1)+1,doc3.indexOf("|",doc3.indexOf(":",doc3.indexOf(":")+1)+1));
		}
		while (writers.contains("(")){
			int index1=writers.indexOf("(");
			int index2=writers.indexOf(")");
			String doc4=writers.substring(0,index1);
			String doc7=writers.substring(index2+1, writers.length());
			writers=doc4+doc7;
		}
		String stars=doc3.substring(doc3.indexOf(":",doc3.indexOf(":",doc3.indexOf(":")+1)+1)+1,doc3.indexOf("|",doc3.indexOf(":",doc3.indexOf(":",doc3.indexOf(":")+1)+1)+1));
		//System.out.println(doc3);
		//System.out.println(director);
		//System.out.println(writers);
		//String mix=director+"\n"+writers+"\n"+stars;
		String[] arrayDirector = director.split(",");
		String[] arrayWriter = writers.split(",");
		String[] arrayStar = stars.split(",");
		//System.out.println(star[1]);
		addList(arrayDirector,Directors,"directors");
		addList(arrayWriter,Writers,"writer");
		addList(arrayStar,Stars,"stars");
		//System.out.println(Stars.get(0));
		return;
	}
	
		private List<Keyword> addList(String [] inputArray, List<Keyword> outputList,
												String type){
			double priorityWeight = 1;
			double typeWeight;
			if (type.equals("directors")) {
				typeWeight = 2;
			}
			else if (type.equals("writer")) {
				typeWeight = 1.5;
			}
			else {
				typeWeight= 1;
			}
			
			for (String element:inputArray) {
				double weight = priorityWeight * typeWeight;
				Keyword keyword = new Keyword(element,weight);
				outputList.add(keyword);
				
				if (priorityWeight>0) {
					priorityWeight -= 0.2;
				}
				else {
					priorityWeight = 0;
				}
			}
			return outputList;
		}
}
	