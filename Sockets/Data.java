// Author: akolomc@gmail.com
// Date: 23rd August, 2007
// A simple class to abstact a 
// body ( its weight and its height)
import java.io.Serializable;

public class Data implements Serializable 
{
	// member data for the class
	private double weight;
	private double height;
	private double bodyMassIndex;

	// constructor for the class
	public Data ( double theWeight, double theHeight )
	{
		setWeight ( theWeight );
		setHeight ( theHeight );
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

}	// end class Data 
