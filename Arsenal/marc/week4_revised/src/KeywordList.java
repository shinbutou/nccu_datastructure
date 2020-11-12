
import java.util.*;
public class KeywordList {
	
	
	private LinkedList<Keyword> lst;
	
	public KeywordList(){
		this.lst = new LinkedList<Keyword>();
		
	}
	public void add(Keyword keyword){
		//7. add keyword to proper index base on its count
		if ((lst.size() == 0)) {
			lst.add(keyword);
			//System.out.println(lst + "\n");
		}
		else {
		
			for(int i =0; i<lst.size(); i++) {
				if(keyword.count >= lst.get(i).count) {		
					lst.add(i, keyword);
					//System.out.println(lst + "\n");
					break;
					}
				else if (i == (lst.size()-1)) {
					lst.add(keyword);
					//System.out.println(lst + "\n");
					break;
				}
			}
		}	
		//System.out.println(lst); test line
	}
	public void outputIndex(int i){
		if(i>lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
		
		LinkedList<Keyword> results = new LinkedList<>();
		Keyword k = lst.get(i);		    
		results.add(k);		    		
		
		printKeywordList(results);
		}
		
	public void outputCount(int c){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    if(k.count == c){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
		}
	
	public void outputHas(String pattern){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    if(k.name.contains(pattern)){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
		}
	
	public void outputName(String pattern){
		
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    if(k.name.equals(pattern)){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
		}
	
	public void outputFirstN(int n){
		if(n>lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<n;i++){
		    Keyword k = lst.get(i);
		    if(i>0){
		    	sb.append(" ");
		    }
		    sb.append(k.toString());   
		}
		System.out.println(sb.toString());
		
		}
	
	public void outputScore(){
		float results = 0;
		//System.out.println(results);
		for(int i=0;i<lst.size();i++){
		    Keyword k = lst.get(i);
		    results += k.weight * k.count;
		    //System.out.println(results);
		}
		
		System.out.println(results);
		}
	
	public void deleteIndex(int i){
		
		if(i>=lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
			
		lst.remove(i);
		
		System.out.println("Done");
		}

	public void deleteCount(int c){
		// 8. remove nodes that the count is equal to cnt
		for (int i = 0; i < lst.size(); i++) {
			//System.out.println(i); test line
			if (lst.get(i).count == c) {
				//System.out.println(i); test line
				lst.remove(i);
				i = 0;
				//System.out.println(lst); test line
				}
			}
		//System.out.println(lst); test line
		System.out.println("Done");
		}

	public void deleteHas(String pattern){
		// 9. remove nodes that the name contains input name
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).name.contains(pattern)) {
				lst.remove(i);
				i = 0;
			}
			
		}
		//System.out.println(lst); test line
		System.out.println("Done");
		
	}
	
	public void deleteName(String name){
		// 10. remove nodes that the name is equal to input name
		for (int i = 0; i < lst.size(); i++) {
			//System.out.println(i); test line
			if (lst.get(i).name.equals(name) ) //can not use operator (==), 
				                               //cause it will compare with their reference, not their value.
			{
				//System.out.println(lst.get(i).name); test line
				lst.remove(i);
				i = 0;
			}
			
		}
		//System.out.println(lst); test line
		System.out.println("Done");
		
		}
	
	public void deleteFirstN(int n){
		//11. remove first n nodes
		for(int i = 0; i < n; i++, n--) {
			lst.remove(i);
			i = 0;
		}
		//System.out.println(lst); test line
		System.out.println("Done");
		}
	
	public void deleteAll(){
		lst = new LinkedList<Keyword>();
		System.out.println("Done");
	}
		
		private void printKeywordList(LinkedList<Keyword> kLst){
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<kLst.size();i++){
				Keyword k= kLst.get(i);
				if(i>0)sb.append(" ");
				sb.append(k.toString());
			}
			System.out.println(sb.toString());
		}
	}



