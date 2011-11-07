// Author: Martin AKolo Chiteri
// Date: 23rd August, 2007 21:02 Hrs
// A program to capture the weight and  height values 
// from the client over the network and process them 
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BMIServer 
{
	// The Sata members of the class 
	private Data message;
	private ServerSocket server;
	private Socket connection;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private int counter;	// counter of the number of connections

	public BMIServer ( )
	{
		// initialise all the variables
		counter = 1;

	}	// end constructor 

	public void runServer ( )
	{
		try {	// set up the server to receive connections; process connections
			server = new ServerSocket ( 12345, 20 );	// allow a maximum of 20 connections on port 1245

			while ( true ) 
			{	// let the program run for an indefinite amount of time
				try {
					waitForConnection ();
					getStreams ();
					processConnection ();
				}	// end try
				catch ( EOFException eofExeption )
				{
					displayMessage ( "\n Sever terminated connection" );
				}	// end catch
				finally 
				{
					closeConnection ();
					counter++;
				}	// end finally clause
			}
		}
		catch ( IOException ioException ) 
		{
			ioException.printStackTrace ();

		}
		
	}	// end method run

	// wait for a connection to arrive, then display connection info
	private void waitForConnection () throws IOException 
	{
		displayMessage ( "Waiting for connection \n" );
		connection = server.accept ();	// allow server to accept connection
		displayMessage ( "Connection " + counter + " received from " + 
				connection.getInetAddress().getHostName () );

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
			displayMessage ("\nConnection Successful.");

			message = ( Data ) input.readObject ();	// read new message
			displayMessage ( "\nThe following message was received from the client." );
			displayMessage ( message.toString () );
		}
		catch ( ClassNotFoundException classNotFoundException )
		{
			displayMessage ( "\n Unknown Object type received. " );
		}
	
		calculateBMI ( message );	// calculate the body mass index for the object
		displayMessage ( "\n Client's message after processing is: " );
		displayMessage ( message.toString () );
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
			ioException.printStackTrace ();	
		}	

	}	// end method closeConnection

	// compute the B.M.I for the client before 
    // sending it back 
    public static void calculateBMI (Data message ) 
    {
		double dividend = message.getWeight();
		double divisor = message.getHeight () * message.getHeight ();
		message.setBMI ( dividend/ divisor );
    }

	// send the message to the client
	private void sendData ( Data message ) 
	{
		try // send object to client
		{	
			output.writeObject ( message );
			output.flush ();
			displayMessage ( "\nSERVER >>> Sent back the message to client. " );
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
}
