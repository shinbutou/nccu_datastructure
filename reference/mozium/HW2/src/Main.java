import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Input parameters for the geometric progression: [DataType] [First] [Base] [N]");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
		{
		    try
		    {
		    	int type = sc.nextInt();		    
			    switch (type)
			    {
				    case 0: //int
				    GeomProgression<Integer> intGeomProg = new GeomProgression<Integer>(sc.nextInt(), sc.nextInt());
					intGeomProg.printProgression(sc.nextInt());
					break;
					
				    case 1: //long
				    GeomProgression<Long> longGeomProg = new GeomProgression<Long>(sc.nextLong(), sc.nextLong());
					longGeomProg.printProgression(sc.nextInt());
					break;
					
				    case 2: //float
				    GeomProgression<Float> floatGeomProg = new GeomProgression<Float>(sc.nextFloat(), sc.nextFloat());
					floatGeomProg.printProgression(sc.nextInt());
					break;
					
				    case 3: //double
				    GeomProgression<Double> doubleGeomProg = new GeomProgression<Double>(sc.nextDouble(), sc.nextDouble());
					doubleGeomProg.printProgression(sc.nextInt());
					break;
		
				    default:
					System.out.println("Invalid Type!");
					sc.next();
					sc.next();
					sc.next(); //consumption of the latter 3 inputs, interesting.
					break;
			    }
			}
		    catch (Exception e)
		    {
				System.out.println("Please check input form!");
				sc.nextLine();
			}	    
		}
		sc.close();
	}
}