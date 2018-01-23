
// CS 455 PA1
// Spring 2017
/**
* class CoinTossSimulator
*
* A class to test the CoinTossSimulator class
*/
public class CoinTossSimulatorTester{


	public static void main(String[] args){

		CoinTossSimulator test = new CoinTossSimulator();
		printer(0, 0, test);
		
		test.run(1);
		printer(1, 1, test);

		test.run(10);
		printer(10, 11, test);

		test.run(100);
		printer(100, 111, test);

		test.reset();
		printer(0, 0, test);

		test.run(1000);
		printer(1000, 1000, test);
	}

   /**
      Print result of each test
      
      @param numTrials  number of trials of one test
      @param expectNumTrials  expected number of total trials
      @param test  class to do simuation
    */

	private static void printer(int testNumTrials, int expectNumTrials, CoinTossSimulator test){
		System.out.println("\nAfter constructor: ");
		System.out.println("Number of trials [exp: " + expectNumTrials + "]: " + test.getNumTrials());
		System.out.println("Two-head tosses: " + test.getTwoHeads());
		System.out.println("Two-tail tosses: " + test.getTwoTails());
		System.out.println("One-head one-tail tosses: " + test.getHeadTails());
		if (test.getTwoHeads() + test.getTwoTails() + test.getHeadTails() == test.getNumTrials()){
			System.out.println("Tosses add up correctly? true");
		}
		else{
			System.out.println("Tosses add up correctly? false");
		}
	}
}
