import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		// Although the original program hasn't report any error, this has ensured it won't
		InputStreamReader rd = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(rd);
	
		String retVal = "";
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}

		return retVal;
    }
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		// word count
		// However, there's no avoiding the inclusion of HTML tags, IMHO.
		int word_count = 0;
		for (int p=0; p<= content.length()-keyword.length(); p++)
		{
			String cand = content.substring(p, p+keyword.length());
			if (cand.equals(keyword))
			{
				word_count += 1;
			}
		}
		
		// This is a valid method, in a sense.
		/*
		String soup[] = content.split(" ");
		
		for (int i=0; i< soup.length; i++)
		{
			if (keyword.equals(soup[i]))
			{
				word_count += 1;
			}
		}
		*/
	
		return word_count;
    }
}