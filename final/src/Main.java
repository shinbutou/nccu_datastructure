import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//String keyword = "top+gun";   									//test line
		
		Scanner sc = new Scanner(System.in);
		String keyword ="";
		while (sc.hasNextLine()) {
			keyword = sc.nextLine();
			keyword = keyword.trim().replace(" ","+");
			System.out.println(keyword);								   //test line
			
			
			
			try {	
				
				//System.out.println(new IMDBQuery(test).fetchContent_url());  //test line
				//System.out.println(new IMDBQuery("top+gun").query());        //test line
				IMDBQuery X = new IMDBQuery(keyword);
				X.query();
				//System.out.println(IMDBQuery.Stars);     					   //test line
				//System.out.println(IMDBQuery.Stars);     					   //test line
				//GoogleQuery Y = new GoogleQuery("");     					   //test line
				//System.out.println(Y.fetchContent_url());					   //test line
				RequestGoogle Y = new RequestGoogle();
				Y.Request();
				System.out.println(RequestGoogle.Results); 					   //test line
				Sequence Z = new Sequence();
				Z.sort();
				Z.output();
				
			} 
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		sc.close();
	}
		
}
