

import java.util.*;

public class Sequence {
	private List<UrlResult> lst;
	
	public Sequence(){
		this.lst = RequestGoogle.Results;
    }
	
	/*public void add(UrlResult UrlResult){
		lst.add(UrlResult);
		System.out.println("Done");	
    }*/
	
	//quick sort
	public void sort(){
		quickSort(0, lst.size()-1);
		//System.out.println("Done");
	}
	
	
	private void quickSort(int leftbound, int rightbound){
		//1. implement quickSort algorithm
		
			if  (leftbound > rightbound) 
			    return;
				
		
			double pivot = lst.get(rightbound).weight;
			int right = rightbound -1;
			int left = leftbound;
			int swapindex = leftbound;
			for(int i = left; i<=right;i++) {
				if (lst.get(i).weight > pivot) {    // key comparator
					swap(swapindex,i);
					swapindex++;
					
				}
				
			}
			swap(swapindex,rightbound);
			quickSort(leftbound,swapindex-1);
			quickSort(swapindex+1,rightbound);
		
			
		
	}
	
	
	private void swap(int aIndex, int bIndex){
		UrlResult temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<lst.size();i++){
			UrlResult temp = lst.get(i);
			if(i>0)sb.append(" ");
			sb.append(temp.toString());
		}
		
		System.out.println(sb.toString());	
	}


	public void output20(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
	
		for(int i=0; i<lst.size();i++){
			UrlResult temp = lst.get(i);
			
			if(i>0)
				sb.append(" ");
			
			sb.append(temp.toString());
			
			if (i>=20) 
				break;
		}
		
		System.out.println(sb.toString());	
	}
}
