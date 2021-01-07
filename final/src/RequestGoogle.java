import java.io.IOException;
import java.util.*;


public class RequestGoogle {
	
	private String movie;
	
	private String infoOfMovie ="";
	
	private String keywordToGoogle;
	
	public static List<UrlResult> Results= new ArrayList<UrlResult>();
	
	public RequestGoogle() {
		
		this.movie = IMDBQuery.searchKeyword;
		
	}
	
	public void Request() {
		
		RequestDir();
		RequestWri();
		RequestSta();	
		
	}
	
	private void SetKeywordToGoogle() {
		
		String erasesite1 = "+-site:www.imdb.com";
		
		String erasesite2 = "+-site:www.rottentomatoes.com";
		
		String erasesite3 = "+-site:wikipedia.org";
		
		String erasesite4 = "+-site:www.pinterest.com";
		
		String erasesite5 = "+-site:www.amazon.com";
		
		String decorations = "+\'film\'or\'series\'or\'television\'or\'movie\'";
		
		this.keywordToGoogle = movie + "+" + infoOfMovie.replace(" ", "+") + decorations 
				+ erasesite1 + erasesite2 + erasesite3 + erasesite4 + erasesite5;
		
	}
	
	private void RequestDir() {
		
		int countOfElements = IMDBQuery.Directors.size();
		
		for(int i = 0; i < countOfElements; i++) {
			this.infoOfMovie = IMDBQuery.Directors.get(i).name;
			double weightOfInfo = IMDBQuery.Directors.get(i).weight;
			SetKeywordToGoogle();
			
				try {
					List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).fetchContent_url();
					
					for(int j =0; j<tempList.size();j++) {
						String url = tempList.get(j).url;
						double weightOfUrl = tempList.get(j).urlWeight;
						double finalWeight = weightOfUrl*weightOfInfo;
						//System.out.println(tempList);															//test line
						UrlResult tmpVar = new UrlResult(url, infoOfMovie, finalWeight);
						Results.add(tmpVar);
					}
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	private void RequestWri() {
		
		int countOfElements = IMDBQuery.Writers.size();
		
		for(int i = 0; i < countOfElements; i++) {
			this.infoOfMovie = IMDBQuery.Writers.get(i).name;
			double weightOfInfo = IMDBQuery.Writers.get(i).weight;
			SetKeywordToGoogle();
			
			try {
				List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).fetchContent_url();
				
				for(int j =0; j<tempList.size();j++) {
					String url = tempList.get(j).url;
					double weightOfUrl = tempList.get(j).urlWeight;
					double finalWeight = weightOfUrl*weightOfInfo;
					//System.out.println(tempList);															//test line
					UrlResult tmpVar = new UrlResult(url, infoOfMovie, finalWeight);
					Results.add(tmpVar);
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void RequestSta() {
		
		int countOfElements = IMDBQuery.Stars.size();
		
		for(int i = 0; i < countOfElements; i++) {
			this.infoOfMovie = IMDBQuery.Stars.get(i).name;
			double weightOfInfo = IMDBQuery.Stars.get(i).weight;
			SetKeywordToGoogle();
			
			try {
				List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).fetchContent_url();
				
				for(int j =0; j<tempList.size();j++) {
					String url = tempList.get(j).url;
					double weightOfUrl = tempList.get(j).urlWeight;
					double finalWeight = weightOfUrl*weightOfInfo;
					//System.out.println(tempList);															//test line
					UrlResult tmpVar = new UrlResult(url, infoOfMovie, finalWeight);
					Results.add(tmpVar);	
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
