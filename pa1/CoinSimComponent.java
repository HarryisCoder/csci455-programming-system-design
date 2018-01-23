
// CS 455 PA1
// Spring 2017
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Color;

/**
* class CoinSimComponent
*
* this class draws the result of CoinTossSimulator: a bar chart including 3 bars
*/

public class CoinSimComponent extends JComponent{

	public static final int NUMBER_OF_BARS = 3;
	public static final int UNITS_PER_BAR = 100;
	//set up color for each bar
	public static final Color BAR_COLOR1 = Color.RED;    
	public static final Color BAR_COLOR2 = Color.GREEN;
    public static final Color BAR_COLOR3 = Color.BLUE;
    //set the fixed width of bar
    public static final int BAR_WIDTH = 73;
    //set the distance from top/bottom of labeled bar to top/bottom of frame
   	public static final int VERTICAL_BUFFER = 50;   

	private int numTrials;
	private int numTwoHeads;
	private int numTwoTails;
	private int numHeadTails;

   /**
      Construct component and run simulation
      @param numtials  the number of simulation trials
   */

    //public CoinSimComponent(){};

	public CoinSimComponent(int numTrials){
		this.numTrials = numTrials;

		//run coin toss simulation
		CoinTossSimulator coinTossSimulator = new CoinTossSimulator();
		coinTossSimulator.run(this.numTrials);

		//get number results of coin toss simulation
		this.numTwoHeads = coinTossSimulator.getTwoHeads();
		this.numTwoTails = coinTossSimulator.getTwoTails();
		this.numHeadTails = coinTossSimulator.getHeadTails();
	};
	
   /**
      Draw 3 labeled bars. 
      @param g  the graphics context
   */

	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		int barWidth = BAR_WIDTH;

		//get the size of current frame
		int frameWidth = getSize().width;
		int frameHeight = getSize().height;

		//calculate the interval distance between neighbor bars
		int horizontalBuffer = (frameWidth - BAR_WIDTH * NUMBER_OF_BARS) / (NUMBER_OF_BARS + 1); 
		//calculate the location of the bottom of the label
		int labelBottom = frameHeight - VERTICAL_BUFFER;
		//calculate how many pixels per application unit
		double barScale = (double)(frameHeight - 2 * VERTICAL_BUFFER) / (UNITS_PER_BAR + Bar.MAX_LABEL_HEIGHT_IN_UNITS);

		//calculate the height of bar in application units, each unit represents 1%
		int barHeight1 = (int)Math.round(100 * (double)numTwoHeads/numTrials);
		int barHeight2 = (int)Math.round(100 * (double)numHeadTails/numTrials);
		int barHeight3 = (int)Math.round(100 * (double)numTwoTails/numTrials);

		int barLeft1 = horizontalBuffer;
		Color barColor1 = BAR_COLOR1;
		String barLabel1 = "Two Heads: " + numTwoHeads + " (" + barHeight1 + "%)";
		Bar bar1 = new Bar(labelBottom, barLeft1, barWidth, barHeight1, barScale, barColor1, barLabel1);
		
		int barLeft2 = barLeft1 + barWidth +horizontalBuffer;
		Color barColor2 = BAR_COLOR2;
		String barLabel2 = "A Head & a Tail: " + numHeadTails + " (" + barHeight2 + "%)";
		Bar bar2 = new Bar(labelBottom, barLeft2, barWidth, barHeight2, barScale, barColor2, barLabel2);

		int barLeft3 = barLeft2 + barWidth + horizontalBuffer;
		Color barColor3 = BAR_COLOR3;
		String barLabel3 = "Two Tails: " + numTwoTails + " (" + barHeight3 + "%)";
		Bar bar3 = new Bar(labelBottom, barLeft3, barWidth, barHeight3, barScale, barColor3, barLabel3);

		bar1.draw(g2);
		bar2.draw(g2);
		bar3.draw(g2);
	}
}