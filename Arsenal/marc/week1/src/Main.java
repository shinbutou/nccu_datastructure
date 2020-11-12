import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("Please enter your height and weight: "); 
		String text = buf.readLine(); 
		String[] data = text.split(" ");
		double res = getBMI(data);
		String dia = getDiagnosis(res);
		System.out.println(dia + " BMI: " + res); 
               
	}
	
	public static double getBMI(String[] data) { 
		// calculate the bmi
		
		double height = Double.valueOf(data[0]);
		double weight = Double.valueOf(data[1]);
		double BMI = weight / Math.pow((height/100), 2);
		BigDecimal BMI_revised = new BigDecimal(BMI).setScale(2,RoundingMode.UP);
		
		return BMI_revised.doubleValue();
	}
	
	public static String getDiagnosis(double bmi) {
		// give comments depending on bmi
		
		if  (bmi < 20)
			return "You are under shape.";
		else if (bmi < 26 && bmi >= 20)
			return "You are in shape.";
		else if (bmi < 30 && bmi >= 26)
			return "To be honest, you are not in shape.";
		else 
			return "You are not in shape. Actually, you are not even close.";
        
	}
}
