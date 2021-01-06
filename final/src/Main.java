import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {	
			String test = "top+gun"; 
			System.out.println(new IMDBQuery(test).fetchContent_url());
			//System.out.println(new IMDBQuery("top+gun").query());
			IMDBQuery X = new IMDBQuery("top+gun");
			X.query();
			System.out.println(X.Stars);
			//System.out.println(X.Stars);
			//GoogleQuery Y = new GoogleQuery(test,X.Directors);
			//Y.query();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
