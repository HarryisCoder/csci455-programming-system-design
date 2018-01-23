
// CS 455 PA1
// Spring 2017

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
  private int totalNumTrials;
  private int numTwoHeads;
  private int numTwoTails;
  private int numHeadTails;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
    totalNumTrials = 0;
    numTwoHeads = 0;
    numTwoTails = 0;
    numHeadTails = 0;

   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {

    Random generator = new Random();
    totalNumTrials += numTrials;

    int trial_1; // the result of the first trial
    int trial_2; // the result of the second trial

    for(int i = 1; i <= numTrials; i++){
      
      trial_1 = generator.nextInt(2);
      trial_2 = generator.nextInt(2);

      if ((trial_1 == 0) && (trial_2 == 0)){
        numTwoHeads ++;
      }
      else if ((trial_1 == 1) && (trial_2 == 1)){
        numTwoTails ++;
      }
      else{
        numHeadTails ++;
      }
    }

 
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return totalNumTrials; 
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numTwoHeads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numTwoTails; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
    totalNumTrials = 0;
    numTwoHeads = 0;
    numTwoTails = 0;
    numHeadTails = 0;
   }

}
