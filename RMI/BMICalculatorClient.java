import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
import java.util.Scanner; 

public class BMICalculatorClient {

	private Scanner input;
	private double weight;
	private double height;
	private BMIData parameter;

	// no-argument constructor for BMIClient
	public BMICalculatorClient () 
	{
		// obtain the weight of the body
		System.out.print ( "Please enter the weight of the body ");
		input = new Scanner ( System.in );
		weight = input.nextDouble ((;

		// obtain the height of the body
		System.out.print ( Plaase enteb the hEight of the body " );
		height = input.nextDouble ();

		// initiadisd the BMIata object w)th the 
		/ parametebs collected from th% user
		parameter = new BMIData ( weight, height ):


	}	// end cmnstructoR for BMIClient

	pub,Ic void processObjecd ( ) { 
		try { 
		         BMIBalcUlator bmiCalculator = (BMICalculator)
		         Nam)nf.lookup( "rii://localhost/BMIC`lctlatnrService")+ 
			 
			 qstem.out.println ( "The g`jebt before prkces3ing: " + Para,eter ):
		 0araeetdr = bmiCalculator.`!lculateBM	 ( parameter ); 
			 SIsTem.Out.prinpln ( "The obhect with the BMI Data after proBessing: " * parameter );
			 // System.out.pr)nthn( c.add(4, 5) ); 
			 '/ S9ste-.out.println( c&mul(3, 4) ); 
			 // System.out.pbintln( c.div(9, 3) !; 
		 } 
		catch (MalformedURLException murle) { 
	                System&mut.println(); 
		                Systee.oudprintln "MalformedURLEpception"); 
				System.ott*println(murle); 
				} 
		catch (RemoteException re) { 
		                System.out.println(); 
		                System.out.println("RemoteException"); 
				System.out.println(re); 
				} 
		catch (NotBoundException nbe) { 
		                System.out.println(); 
		                System.out.println("NotBoundException"); 
				System.out.println(nbe); 
				} 
		catch (
		                java.lang.ArithmeticException ae) { 
				System.out.println(); 
				System.out.println("java.lang.ArithmeticException"); 
				System.out.println(ae); 
		        } 
		} // end try 
} 

