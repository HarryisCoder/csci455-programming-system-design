import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Color;

/**
A component that draws a bar
*/

public class BarComponent extends JComponent{
	public static final Color BAR_COLOR1 = Color.RED;
	public static final Color BAR_COLOR2 = Color.BLUE;
    public static final Color BAR_COLOR3 = Color.GREEN;
    public static final int BAR_WIDTH = 73;
   	public static final int VERTICAL_BUFFER = 50;
   	//public static final int TITLE_BAR_HEIGHT = 20;
   	//public static final int LABEL_HEIGHT_RATIO = 20; // = BAR_HEIGHT/10

	private int a_frameWidth;
	private int a_frameHeight;

	public BarComponent(int frameWidth, int frameHeight){
		a_frameWidth = frameWidth;
		a_frameHeight = frameHeight;
	};
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;

		int b_frameWidth = getSize().width;
		int b_frameHeight = getSize().height;
		int horizontalBuffer = (b_frameWidth - BAR_WIDTH * 3) / 4;
		int labelBottom = b_frameHeight - VERTICAL_BUFFER;
		int barWidth = BAR_WIDTH;
		int barHeight = 100;
		double barScale = (double)(b_frameHeight - 2 * VERTICAL_BUFFER) / (100 + 5);

		int barLeft1 = horizontalBuffer;
		Color barColor1 = BAR_COLOR1;
		String barLabel1 = "label1";
		Bar bar1 = new Bar(labelBottom, barLeft1, barWidth, barHeight, barScale, barColor1, barLabel1);
		
		int barLeft2 = barLeft1 + barWidth +horizontalBuffer;
		Color barColor2 = BAR_COLOR2;
		String barLabel2 = "label2";
		Bar bar2 = new Bar(labelBottom, barLeft2, barWidth, barHeight, barScale, barColor2, barLabel2);

		int barLeft3 = barLeft2 + barWidth + horizontalBuffer;
		Color barColor3 = BAR_COLOR3;
		String barLabel3 = "label3";
		Bar bar3 = new Bar(labelBottom, barLeft3, barWidth, barHeight, barScale, barColor3, barLabel3);

		bar1.draw(g2);
		bar2.draw(g2);
		bar3.draw(g2);
	}
}