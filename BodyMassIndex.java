package BMIException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BodyMassIndex {

	private float height;
	private float weight;
	private final double LOWER_WEIGHT = 18.5;  // Define CONSTANT lower normal value of BMI.
	private final double HIGHER_WIEGHT = 24;  // Define CONSTANT higher normal value of BMI.

	// BMI NORMAL Range from 18.5 to 24.

	public BodyMassIndex(String weight, String height) { 
	// Constructor is using for assigning values to Variable.
		try {
			this.weight = Float.valueOf(weight);
			this.height = Float.valueOf(height);
			
		}	
		catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println("This is Body Mass Index Calculate System...");

		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); // User input (Weight)
		BodyMassIndex index =null;
		
		try{
			System.out.println("Please Enter your Weight(pounds) : ");
			String userWeight = userInput.readLine();
			System.out.println("Please Enter your Height(inches) : "); 					  // User input (height)
			String userHeight = userInput.readLine();
			
			if(userHeight.length() >=4 || userWeight.length() >= 4){
				throw new LargeNumberOfInputFoundException("large Number of Input Found!! ");
			}
			
			// TODO: default constructor called to initialize the variables.
			index = new BodyMassIndex(userWeight, userHeight);

			
		}catch (StringIndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			double valueBMI = index.calculateBMI();
			String reviewBMI = index.reviewUser(valueBMI);
			
			if (reviewBMI == null) {
				// if BMI value would not match with any of cases then it fires this Exception
				throw new NullPointerException();			
			}
			index.showResults(reviewBMI);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void showResults(String reviewBMI) throws Exception {
		
		if(reviewBMI != null){
			System.out.println(reviewBMI);

		}
	}

	private String reviewUser(double valueBMI) throws CustomException {

		int bmiVal = (int) valueBMI;
		System.out.println("Your BMI value is : " + bmiVal);

		String gainW = "You are in Underweight! \n You can gain your weight 1 pound in a one week! ";
		String lossW = "You are in Overweight! \n You can loss your weight 1 pound in a one week! ";
		
		if(valueBMI <= 0){
			
			throw new CustomException("Invalid Input Found !!");
			
		}
		else if (valueBMI <= LOWER_WEIGHT) {
			int gainPound = (int) ((LOWER_WEIGHT * height * height) / (703)); 			// Under Weight
			int week = (int) (gainPound - weight);
			return (gainW + "\nYou can be in Normal after " + week + " week.");
		
		} else if (valueBMI > LOWER_WEIGHT && valueBMI <= 24.9) {

			return ("Congratulations, Your weight is Perfect!"); 						// Normal Weight
																	 
		} else if (valueBMI >= 25 && valueBMI < 29.9) {

			int lossPound = (int) ((HIGHER_WIEGHT * height * height) / (703)); 			// Over weight
																				 
			int week = (int) (weight - lossPound);
			return (lossW + "\nYou can be in Normal after " + week + " week.");
		}	
		
		else {
			return null;
		}
	}

	private double calculateBMI() {
		
		// Method Calculates BMI and returns the Value.
		
		double bmi = ((weight * 703) / (height * height));
		
		if (bmi == Double.POSITIVE_INFINITY || bmi == Double.NEGATIVE_INFINITY) {
			throw new ArithmeticException();								
		}
		if (bmi == 0.0) {
			throw new NullPointerException();
		}

		return bmi;
	}
}
