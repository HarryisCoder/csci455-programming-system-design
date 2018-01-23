import javax.swing.JFrame;

/**
A class to test the Bar class
*/

public class BarTester{
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 500;
	//public static final int TITLE_BAR_HEIGHT = 20;
	public static void main(String[] args){
		JFrame frame = new JFrame();
		int frameWidth = FRAME_WIDTH;
		int frameHeight = FRAME_HEIGHT;
		frame.setSize(frameWidth, frameHeight);
		frame.setTitle("BarTester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		BarComponent bar = new BarComponent(frame.getWidth(), frame.getHeight());
		frame.add(bar);

		frame.setVisible(true);
	}
}