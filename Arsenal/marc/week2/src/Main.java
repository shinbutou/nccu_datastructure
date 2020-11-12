
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Please type Datatype First Base N");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
		    int type = sc.nextInt();
		    
		    switch (type){
			    case 0:
			    GeomProgression<Integer> intGeomProg = new GeomProgression<Integer>(sc.nextInt(), 
			    		sc.nextInt());
				intGeomProg.printProgression(sc.nextInt());
				break;
				
			    case 1:
				GeomProgression<Long> longGeomProg = new GeomProgression<Long>(sc.nextLong(), 
				    		sc.nextLong());
				longGeomProg.printProgression(sc.nextInt());
				break;
				
			    case 2:
			    GeomProgression<Float> floatGeomProg = new GeomProgression<Float>(sc.nextFloat(),
			    		sc.nextFloat());
				floatGeomProg.printProgression(sc.nextInt());
				break;
				
			    case 3:
				GeomProgression<Double> doubleGeomProg = new GeomProgression<Double>(sc.nextDouble(),
				    		sc.nextDouble());
				doubleGeomProg.printProgression(sc.nextInt());
				break;
	
			    default:
				System.out.println("InvalidType");
				//consume the following 3 useless tokens
				sc.next();
				sc.next();
				sc.next();
				break;
		    }
		    
		}
		sc.close();

	}

}
	



