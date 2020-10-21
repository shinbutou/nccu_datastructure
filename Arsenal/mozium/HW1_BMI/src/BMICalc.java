import java.util.Scanner;
import java.text.DecimalFormat;

public class BMICalc
{ 
    public static void main(String[] args)
    {
    	Scanner ginput = new Scanner(System.in);
        System.out.print("Enter your weight in kilogram: ");
        float weight = ginput.nextFloat();
        System.out.print("Enter your height in centimeter: ");
        float height = ginput.nextFloat();
        
        float bmi = get_value(weight, height);
        String dia = get_diagnosis(bmi);
        
        DecimalFormat d3 = new DecimalFormat("###.###");
        System.out.println("Your BMI value is: " + d3.format(bmi) + ", " + dia);
    }
    
    public static float get_value(float w, float h)
    {
    	float mh = h / 100;
        return w / (mh * mh);
    }
    
    public static String get_diagnosis(float v)
    {
        if (v < 20)
        {
            return "You are under shape.";
        }
        else if (20 < v & v < 26)
        {
            return "You are in shape.";
        }
        else if (26 < v & v < 30)
        {
            return "To be honest, you are not in shape.";
        }
        else
        {
        	return "You're not in shape. Actually, you are not even close!";
        }
    }
}