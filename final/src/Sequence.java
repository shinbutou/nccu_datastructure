
import java.util.*;

 public class Sequence {
	 
	 private List<UrlResult> lst;
  
	 public Sequence(){
		 this.lst = RequestGoogle.Results;
     }
  

	   //quick sort
	 public void sort(){
		 quickSort(0, lst.size()-1);
		 }
	  
	 
	 private void quickSort(int leftbound,  int rightbound){
	   //1. implement quickSort algorithm
	   if (lst.isEmpty()) {
	    System.out.println("InvalidOperation");
	   }
	   else {
	    if(rightbound<=leftbound) {
	     return;
	    }
	       UrlResult i=lst.get(leftbound);  
	    int l=leftbound+1;
	    int r=rightbound;
	    
	    while(true) {
	     while(r>leftbound&&lst.get(r).weight>=i.weight) {
	      --r;
	     }
	     while(l<=r&&lst.get(l).weight<=i.weight) {
	      ++l;
	     }
	     if(l<r) {
	      swap(l,r);
	     }
	     else {
	      if(r>leftbound) {
	       swap(r,leftbound);
	      }
	      break;
	     }
	     System.out.println("Done");
	    }
	    
	        if(r>leftbound) {
	         quickSort(leftbound, r-1);
	        }
	        if(r<rightbound) {
	         quickSort(r+1, rightbound);
	        }
	   }
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
	    UrlResult k = lst.get(i);
	    if(i>0)sb.append(" ");
	    sb.append(k.toString());
	   }
	   
	   System.out.println(sb.toString()); 
	  }
	  
	 }


