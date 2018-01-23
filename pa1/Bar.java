
// CS 455 PA1
// Spring 2017

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {

   //set the maximum height of bar label in application units
   public static final int MAX_LABEL_HEIGHT_IN_UNITS = 5; 
   public static final Color LABEL_COLOR = Color.BLACK; 
   
   private int ybottom;
   private int xleft;
   private int width;
   private int barHeight;
   private double scale;
   private Color barColor;
   private String label;

   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
      ybottom = bottom;
      xleft = left;
      this.width = width;
      this.barHeight = barHeight;
      this.scale = scale;
      barColor = color;
      this.label = label;

   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {

      int labelMaxHeight = (int)(scale * MAX_LABEL_HEIGHT_IN_UNITS);
      //caculate the height of bar in pixels
      int barHeightPixels = (int)(barHeight * scale);
      
      //draw a bar
      Rectangle bar = new Rectangle(xleft, ybottom - labelMaxHeight - barHeightPixels, width, barHeightPixels);
      g2.setColor(barColor);
      g2.fill(bar);

      //create a label using defalt font and size
      Font font = g2.getFont(); 
      FontRenderContext context = g2.getFontRenderContext();
      Rectangle2D labelBounds = font.getStringBounds(label, context);
      int heightOfLabel = (int)labelBounds.getHeight();
      
      //resize the label to fit the maximum label height
      int newFontSize;
      if (heightOfLabel != 0){
         newFontSize = Math.max((int)(font.getSize() * labelMaxHeight / heightOfLabel), font.getSize());
      }
      else{
         newFontSize = font.getSize();
      } 
      Font newFont = new Font(font.getName(), Font.PLAIN, newFontSize);
      g2.setFont(newFont);
      context = g2.getFontRenderContext();
      labelBounds = newFont.getStringBounds(label, context);
      int newWidthOfLabel = (int)labelBounds.getWidth();

      g2.setColor(LABEL_COLOR);
      //draw the label under the bar, ensure the label aligned with the center of bar
      g2.drawString(label, xleft + width/2 - newWidthOfLabel/2, ybottom);
   }
}