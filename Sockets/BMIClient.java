// Author: Martin AKolo Chiteri
// Date: 24th August, 2007 05:37 Hrs
// A program to capture the weight and  height values 
// from the user and send them to the sever for processing 
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BMIClient 
{
	// Declare tha data members of the class 
	private Data message;
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Scanner keyboard; 
	private double weight;
	private double height;
	private Socket client;
	private String bmiServer;	// host server for this application

	// begin constructor for BMIClient 
	public BMIClient ( String host ) 
	{
		bmiServer = host;	// set server to which clients connect

		// collect the values from the user 
		System.out.print ( "\n Enter the weight of the body " );
		keyboard = new Scanner ( System.in );
		weight = keyboard.nextDouble ();

		System.out.print ( "Enter the height of the body " );
		height = keyboard.nextDouble ();

		// instaniate an object of Data type to hold the values
		message = new Data ( weight, height );

	}	// end constructor for BMIClient 

	// connect to the server and process messages from server
	public void runClient ()
	{
		try {
			connectToServer ();
			getStreams ();
			processConnection ();
		}	// end try
		catch ( EOFException eofExeption )
		{
			displayMessage ( "\n Client terminated connection" );
		}	// end catch
		catch ( IOException ioException )
		{
			ioException.printStackTrace ();
		}
		finally 
		{
			closeConnection ();
		}	// end finally clause

	}	// end method runClient

	// wait for a connection to arrive, then display connection info
	private void connectToServer () throws IOException 
	{
		displayMessage ( "Attempting connection \n" );
		connection = new Socket ( InetAddress.getByName ( bmiServer ), 12345 );

		displayMessage ( "Connected to " + connection.getInetAddress().getHostName () );

	}	// end method waitForConnection

	// get streams to send and receive data 
	private void getStreams () throws IOException
	{
		// set up stream for objects
		output = new ObjectOutputStream ( connection.getOutputStream () );
		output.flush ();	// flush output buffer to send header information

		// set up input stream for objects
		input = new ObjectInputStream ( connection.getInputStream () );

		displayMessage ( "\n Got I/O Streams \n" );

	}	// end method getStreams 

	// process connections with clients
	public void processConnection () throws IOException 
	{
		try {
			displayMessage ("\n Connection Successful");
			displayMessage ("\n The message before processing at the server is " );
			displayMessage ( message.toString () );
			sendData ( message ); 	// send the data to the server for processing 
			displayMessage ( "\n .... Waiting for server to finish processing ... " );
			message = ( Data ) input.readObject ();	// read new message sent back
			displayMessage ( "\n Object received from server. Processing complete. \n" );
			displayMessage ( message.toString () );
		}
		catch ( ClassNotFoundException classNotFoundException )
		{
			displayMessage ( "\n Unknown Object type received. " );
		}
	
		sendData ( message );	// relay the object back to the client after processing

	}	// end method processConnections

	// close streams and socket
	public void closeConnection ( )
	{
		displayMessage ( "\nTerminating connection\n" );

		try {
			output.close ();	// close output streams 
			input.close ();	// close input streams 
			connection.close ();	// close socket
		}	// end try
		catch ( IOException ioException )
		{
			System.out.println ("\n Client Disconnected \n");	
		}	

	}	// end method closeConnection

	// send the message to the client
	private void sendData ( Data message ) 
	{
		try // send object to client
		{	
			output.writeObject ( message );
			output.flush ();
			displayMessage ( "\nCLIENT >>> Sent the message to server. " );
		} 
		catch ( IOException ioException ) 
		{
			displayMessage ( "\n Error writing object"  );
		}
	}	// end method sendData 

	// a method to send the messages to the screen
	public void displayMessage ( String message )
	{
		System.out.printf ( "%s \n", message );
	}	// end method display message 


}	// end class BMIClient
