public interface BMICalculator 
	extends java.rmi.Remote {

	public BMIData calculateBMI ( BMIData message ) 
		throws java.rmi.RemoteException;

}	// end interface Calculator
