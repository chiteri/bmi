public class BMICalculatorImpl 
    extends java.rmi.server.UnicastRemoteObject 
    implements BMICalculator { 
	       
  // Implementations must have an explicit constructor 
  // in order to declare the RemoteException exception 
  public BMICalculatorImpl() 
	  throws java.rmi.RemoteException { 
	  super();          
  } 
                                     
  public BMIData calculateBMI ( BMIData message ) 
          throws java.rmi.RemoteException { 

     double dividend = message.getWeight ();
     double divisor = message.getHeight () * message.getHeight ();

     message.setBMI ( dividend / divisor );

     return message;

  }	// end method calculateBMI

} // end class CalculatorImpl
