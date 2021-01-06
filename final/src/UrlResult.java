

public class UrlResult {

	public String url;
	public String attribute;
    public double weight;
    
    public UrlResult(String url, String attribute, double weight){
		this.url = url;
		this.attribute = attribute;
		this.weight = weight;
    }
    
    @Override
    public String toString(){
    	return "["+url+","+ attribute+","+weight+"]";
    }
    
    public String getName()
    {
    	return url;
    }
    
    public String getAttribute()
    {
    	return attribute;
    }
    
    public double getWeight()
    {
    	return weight;
    }
}