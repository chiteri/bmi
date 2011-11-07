import java.rmi.Naming;

public class BMICalculatorServer {

	   public BMICalculatorServer() {
		        try {
				       BMICalculator bmiCalculator = new BMICalculatorImpl();
				              Naming.rebind("rmi://localhost:1099/BMICalculatorService", bmiCalculator );
					           } catch (Exception exception ) {
							          System.out.println("Trouble: " + exception );
								       }
			   }

	      public static void main(String args[]) {
		           new BMICalculatorServer();
			      }
}

