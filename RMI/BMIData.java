// Author: akolomc@gmail.com
// Date: 23rd August, 2007
// A simple class to abstact a 
// body ( its weight and its height)
import java.io.Serializable;

public class BMIData implements Serializable 
{
	// member data for the class
	private double weight;
	private double height;
	private double bodyMassIndex;

	// constructor for the class
	public BMIData ( double theWeight, double theHeight )
	{
		setWeight ( theWeight );
		setHeight ( theHeight );
		setBMI (0.00);
	}	

	// a method to set the weight of the body
	public void setWeight ( double theWeight ) 
	{
		weight = theWeight;
	}

	// a method to set the height of the body
	public void setHeight ( double theHeight ) 
	{
		height = theHeight;
	}
	
	// a method to set the body mass index
	public void setBMI ( double bmi )
	{
		bodyMassIndex = bmi;
	}	
	
	// a method to get the body mass index of the body
	public double getBMI ( ) 
	{
		return bodyMassIndex;
	}

	// a method to return the weight of a body object
	public double getWeight ( )
	{
		return weight;
	}	// end method getWeight

	// a method to return the height of the body object
	public double getHeight () 
	{
		return height;
	}

	// a method to return the string 
	// equivalent of the message object
	public String toString ( )
	{
		return String.format ( "\nThe weight is %.2f \n %s%.2f\n%s%.2f\n", getWeight (), "The height is ", getHeight (), 
		"And the body mass index [B.M.I] is ", getBMI () );
	}

}	// end class Data 
