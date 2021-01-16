import java.io.IOException;
import java.util.*;


public class RequestGoogle {
	
	private String movie;
	
	private String infoOfMovie ="";
	
	private String keywordToGoogle;
	
	public static ArrayList<UrlResult> Results= new ArrayList<UrlResult>();
	
	
	public RequestGoogle() {
		
		this.movie = IMDBQuery.searchKeyword;
		
	}
	
	
	public  ArrayList<UrlResult> Request() {
		
		RequestDir();
		RequestWri();
		RequestSta();
		removeSimilar();
		quickSort(0, Results.size()-1);
		return Results;
		
	}
	
	private void quickSort(int leftbound, int rightbound){
		//1. implement quickSort algorithm
		
			if  (leftbound > rightbound) 
			    return;
				
		
			double pivot = Results.get(rightbound).weight;
			int right = rightbound -1;
			int left = leftbound;
			int swapindex = leftbound;
			for(int i = left; i<=right;i++) {
				if (Results.get(i).weight > pivot) {    // key comparator
					swap(swapindex,i);
					swapindex++;
				}
				
			}
			swap(swapindex,rightbound);
			quickSort(leftbound,swapindex-1);
			quickSort(swapindex+1,rightbound);			
	}
	
	
	private void swap(int aIndex, int bIndex){
		UrlResult temp = Results.get(aIndex);
		Results.set(aIndex, Results.get(bIndex));
		Results.set(bIndex, temp);
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<Results.size();i++){
			UrlResult temp = Results.get(i);
			if(i>0)sb.append(" ");
			sb.append(temp.toString());
		}
		
		System.out.println(sb.toString());	
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
					List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).query();
					
					for(int j =0; j<tempList.size();j++) {
						
						String topNameOfUrl = tempList.get(j).topName;
						String titleOfUrl = tempList.get(j).title;
						String url = tempList.get(j).url;
						String introsOfUrl = tempList.get(j).intros;
						double weightOfUrl = tempList.get(j).urlWeight;
						double finalWeight = weightOfUrl*weightOfInfo;
						
						//System.out.println(tempList);															//test line
						UrlResult tmpVar = new UrlResult(topNameOfUrl, titleOfUrl, url, introsOfUrl, finalWeight);
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
				List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).query();
				
				for(int j =0; j<tempList.size();j++) {
					
					String topNameOfUrl = tempList.get(j).topName;
					String titleOfUrl = tempList.get(j).title;
					String url = tempList.get(j).url;
					String introsOfUrl = tempList.get(j).intros;
					double weightOfUrl = tempList.get(j).urlWeight;
					double finalWeight = weightOfUrl*weightOfInfo;
																			
					UrlResult tmpVar = new UrlResult(topNameOfUrl, titleOfUrl, url, introsOfUrl, finalWeight);
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
				List<UrlTempResult> tempList = new GoogleQuery(keywordToGoogle).query();
				
				for(int j =0; j<tempList.size();j++) {
					
					String topNameOfUrl = tempList.get(j).topName;
					String titleOfUrl = tempList.get(j).title;
					String url = tempList.get(j).url;
					String introsOfUrl = tempList.get(j).intros;
					double weightOfUrl = tempList.get(j).urlWeight;
					double finalWeight = weightOfUrl*weightOfInfo;
																	
					UrlResult tmpVar = new UrlResult(topNameOfUrl, titleOfUrl, url, introsOfUrl, finalWeight);
					Results.add(tmpVar);	
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void removeSimilar() {
		
    	//***call this before rank elements
    	int index = 0;
    
    	while(index < Results.size()) {
    		int check = index+1;
    		
    		while (check < Results.size()) {
    			if(Results.get(index).topName.equals(Results.get(check).topName)) {
    				if(Results.get(index).weight >= Results.get(check).weight){
    					Results.remove(check);
    				}
    				else {
    					Results.remove(index);
    				}
    			}
    			else {
    				check++;
    				
    			}	
    		}
    		index++;
    	}
    }
}
