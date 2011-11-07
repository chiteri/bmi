// Author: Martin Akolo
// Date: 24th August, 2007: 06:15 Hrs
// A program to run the BMI client 
//
public class BMIClientTest 
{
	public static void main ( String args [] ) 
	{
		BMIClient application;
		if ( args.length == 0 ) 
			application = new BMIClient ( "127.0.0.1");	// connect to localhost
		else
			application = new BMIClient ( args [0] );	// use args to connect

		application.runClient ();	// run client application

	}	// end method main

}	// end class BMIClient
