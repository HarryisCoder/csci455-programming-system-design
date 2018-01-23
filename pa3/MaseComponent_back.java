
// CS 455 PA3
// Spring 2017

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.util.LinkedList;
import java.util.ListIterator;


/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

   private static final int START_X = 10; // top left of corner of maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 1;  // how much smaller on each side to make entry/exit inner box

   private static final int MAZE_BORDER = 2;  // width of maze border
   //public static final int LABEL_Y = 20;
   private static final Color BOX_COLOR = Color.WHITE;
   private static final Color START_BOX_COLOR = Color.YELLOW;
   private static final Color EXIT_BOX_COLOR = Color.GREEN;
   private static final Color BACKGROUND_COLOR = Color.BLACK;
   private static final Color PATH_COLOR = Color.BLUE;
   //private static final Color LABEL_COLOR = Color.BLACK;


   //public static final int VERTICAL_BUFFER = 50;
   //public static final int VERTICAL_BUFFER = 50;

   private Maze maze;
   private int backGroundX = START_X + MAZE_BORDER;
   private int backGroundY = START_Y + MAZE_BORDER;
   

   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
        this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
        Graphics2D g2 = (Graphics2D)g;  

        //String label = "Type any key to start maze search ...";
        //g2.setColor(LABEL_COLOR);
        //draw the label under the bar, ensure the label aligned with the center of bar
        //g2.drawString(label, START_X, LABEL_Y);
        
        //draw background
        drawBackground(g2);

        //draw maze
        drawMaze(g2);

        //draw start location
        drawStartLoc(g2);

        //draw exit location
        drawExitLoc(g2);

        //draw path
        //if(maze.search()){
        drawPath(g2);
        //}

   }

    private void drawBackground(Graphics2D g2){

        int mazeWidth = maze.numCols() * BOX_WIDTH;
        int mazeHeight = maze.numRows() * BOX_HEIGHT;
        Rectangle backGround = new Rectangle(START_X, START_Y, mazeWidth + MAZE_BORDER * 2, mazeHeight + MAZE_BORDER * 2);
        g2.setColor(BACKGROUND_COLOR);
        g2.fill(backGround);

    }

    private void drawMaze(Graphics2D g2){

        //int backGroundX = START_X + MAZE_BORDER;
        //int backGroundY = START_Y + MAZE_BORDER;
        for(int i = 0; i < maze.numRows(); i++){
            for(int j = 0; j < maze.numCols(); j++){
                if(!maze.hasWallAt(new MazeCoord (i, j))){
                    Rectangle mazeSquare = new Rectangle(backGroundX + j * BOX_WIDTH, backGroundY + i * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                    g2.setColor(BOX_COLOR);
                    g2.fill(mazeSquare);
                }

            }
        }

    }

    private void drawStartLoc(Graphics2D g2){

        int startLocX = backGroundX + maze.getEntryLoc().getCol() * BOX_WIDTH + INSET;
        int startLocY = backGroundY + maze.getEntryLoc().getRow() * BOX_HEIGHT + INSET;
        Rectangle startSquare = new Rectangle(startLocX, startLocY, BOX_WIDTH - INSET * 2, BOX_HEIGHT - INSET * 2);
        g2.setColor(START_BOX_COLOR);
        g2.fill(startSquare);
    }

    private void drawExitLoc(Graphics2D g2){

        int exitLocX = backGroundX + maze.getExitLoc().getCol() * BOX_WIDTH + INSET;
        int exitLocY = backGroundY + maze.getExitLoc().getRow() * BOX_HEIGHT + INSET;;
        Rectangle exitSquare = new Rectangle(exitLocX, exitLocY, BOX_WIDTH - INSET * 2, BOX_HEIGHT - INSET * 2);
        g2.setColor(EXIT_BOX_COLOR);
        g2.fill(exitSquare);
    }

    private void drawPath(Graphics2D g2){

        LinkedList<MazeCoord> path = maze.getPath();
        if(path.size() > 0){
            //System.out.println("path: " + path.toString());
            ListIterator<MazeCoord> iter = path.listIterator(path.size());
            g2.setColor(PATH_COLOR);
            MazeCoord currentLoc;
            //System.out.println("currentLoc: " + currentLoc);
            MazeCoord nextLoc = iter.previous();
            //Line2D.Double segment;
            //System.out.println("nextLoc: " + nextLoc);
            /*
            double x = backGroundX + maze.getEntryLoc().getCol() * BOX_WIDTH + BOX_WIDTH/2;
            double y = backGroundY + maze.getEntryLoc().getRow() * BOX_HEIGHT + BOX_HEIGHT/2;
            double xx = backGroundX + maze.getExitLoc().getCol() * BOX_WIDTH + BOX_WIDTH/2;
            double yy = backGroundY + maze.getExitLoc().getRow() * BOX_HEIGHT + BOX_HEIGHT/2;
            Line2D.Double segment = new Line2D.Double(x, y, xx, yy);;
            g2.fill(segment);
            */

            while(iter.hasPrevious()){

                currentLoc = nextLoc;
                nextLoc = iter.previous();
                
                //System.out.println("currentLoc: " + currentLoc);
                //System.out.println("nextLoc: " + nextLoc);
                double x1 = backGroundX + currentLoc.getCol() * BOX_WIDTH + BOX_WIDTH/2;
                double y1 = backGroundY + currentLoc.getRow() * BOX_HEIGHT + BOX_HEIGHT/2;
                double x2 = backGroundX + nextLoc.getCol() * BOX_WIDTH + BOX_WIDTH/2;
                double y2 = backGroundY + nextLoc.getRow() * BOX_HEIGHT + BOX_HEIGHT/2;
                
                //segment = new Line2D.Double(x1, y1, x2, y2);
                //System.out.println("segment: " + segment.toString());
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
                //g2.fill(segment);
            }

      }
    }
   
}



