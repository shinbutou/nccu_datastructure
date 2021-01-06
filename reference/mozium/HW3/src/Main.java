import java.io.*;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Please input in the following format [URL] [Keyword]: ");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
		{
		    String urlStr = sc.next();
		    String keyword = sc.next();
		   
		    WordCounter counter = new WordCounter(urlStr);
		    System.out.println(counter.countKeyword(keyword));
		}
	}
}