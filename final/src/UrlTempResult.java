
public class UrlTempResult {


		public String url;
	    
		public double urlWeight;
		
		
	    
	    public UrlTempResult(String url, double weight){
			
	    	this.url = url;
			
	    	this.urlWeight = weight;
	    	
	    }
	    
	    @Override
	    public String toString(){
	    	
	    	return "["+url+","+urlWeight+"]";
	    	
	    }
	    
	    public String getUrl(){
	    	
	    	return url;
	    	
	    }
	    
	    
	    public double getWeight(){
	    	
	    	return urlWeight;
	    	
	    }
	}

