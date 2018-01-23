
// CS 455 PA1
// Spring 2017
import javax.swing.JFrame;
import java.util.Scanner;

/**
* class CoinSimViewer
*
* Display the result of CoinTossSmulator
*
* Usage: run simulation by inputting the number of simulations and press "enter", exit program by closing the display window
*/

public class CoinSimViewer{
    
    //initialized size of frame
	public static final int FRAME_WIDTH = 800;   
	public static final int FRAME_HEIGHT = 500;

	public static void main(String[] args){

		//check input, input number should be > 0
		System.out.println("Please input the number of trials: ");
		Scanner in = new Scanner(System.in);
		int numTrials = in.nextInt();
		while(numTrials <= 0){
			System.out.println("ERROR! Number entered must be greater than 0. ");
			System.out.println("Please input the number of trials: ");
			in = new Scanner(System.in);
			numTrials = in.nextInt();
		}

		CoinSimComponent coinSimBar = new CoinSimComponent(numTrials);

		JFrame frame = new JFrame();
		int frameWidth = FRAME_WIDTH;
		int frameHeight = FRAME_HEIGHT;
		frame.setSize(frameWidth, frameHeight);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		frame.add(coinSimBar);

		frame.setVisible(true);
	}
}