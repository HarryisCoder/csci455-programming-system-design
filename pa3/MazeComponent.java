
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
   // coordinates of top left corner of maze background
   private static final int START_X = 10; 
   private static final int START_Y = 10;
   // width and height of one maze box
   private static final int BOX_WIDTH = 20;  
   private static final int BOX_HEIGHT = 20;
   // how much smaller on each side to make entry/exit inner box
   private static final int INSET = 1;  

   private static final int MAZE_BORDER = 2;  // width of maze border
   private static final Color BOX_COLOR = Color.WHITE;
   private static final Color START_BOX_COLOR = Color.YELLOW;
   private static final Color EXIT_BOX_COLOR = Color.GREEN;
   private static final Color BACKGROUND_COLOR = Color.BLACK;
   private static final Color PATH_COLOR = Color.BLUE;

   private Maze maze;
   // coordinates of top left corner of maze
   private int mazeX = START_X + MAZE_BORDER;
   private int mazeY = START_Y + MAZE_BORDER;
   

   
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
        
        drawBackground(g2);

        drawMaze(g2);

        drawStartLoc(g2);

        drawExitLoc(g2);

        drawPath(g2);

   }
   /**
     Draws the black backgorund of maze.
     @param g2 the graphics context
   */
    private void drawBackground(Graphics2D g2){

        int mazeWidth = maze.numCols() * BOX_WIDTH;
        int mazeHeight = maze.numRows() * BOX_HEIGHT;
        Rectangle backGround = new Rectangle(START_X, START_Y, mazeWidth + MAZE_BORDER * 2, mazeHeight + MAZE_BORDER * 2);
        g2.setColor(BACKGROUND_COLOR);
        g2.fill(backGround);

    }
   /**
     Draws the maze.
     @param g2 the graphics context
   */
    private void drawMaze(Graphics2D g2){

        g2.setColor(BOX_COLOR);
        for(int i = 0; i < maze.numRows(); i++){
            for(int j = 0; j < maze.numCols(); j++){
                if(!maze.hasWallAt(new MazeCoord (i, j))){
                    Rectangle mazeSquare = new Rectangle(mazeX + j * BOX_WIDTH, mazeY + i * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);
                    g2.fill(mazeSquare);
                }

            }
        }

    }
   /**
     Draws the start location of maze.
     @param g2 the graphics context
   */
    private void drawStartLoc(Graphics2D g2){

        int startLocX = mazeX + maze.getEntryLoc().getCol() * BOX_WIDTH + INSET;
        int startLocY = mazeY + maze.getEntryLoc().getRow() * BOX_HEIGHT + INSET;
        Rectangle startSquare = new Rectangle(startLocX, startLocY, BOX_WIDTH - INSET * 2, BOX_HEIGHT - INSET * 2);
        g2.setColor(START_BOX_COLOR);
        g2.fill(startSquare);
    }
   /**
     Draws the exit location of maze.
     @param g2 the graphics context
   */
    private void drawExitLoc(Graphics2D g2){

        int exitLocX = mazeX + maze.getExitLoc().getCol() * BOX_WIDTH + INSET;
        int exitLocY = mazeY + maze.getExitLoc().getRow() * BOX_HEIGHT + INSET;;
        Rectangle exitSquare = new Rectangle(exitLocX, exitLocY, BOX_WIDTH - INSET * 2, BOX_HEIGHT - INSET * 2);
        g2.setColor(EXIT_BOX_COLOR);
        g2.fill(exitSquare);
    }
   /**
     Draws the path through maze if one has been found.
     @param g2 the graphics context
   */
    private void drawPath(Graphics2D g2){

        LinkedList<MazeCoord> path = maze.getPath();

        if(path.size() > 0){

            ListIterator<MazeCoord> iter = path.listIterator();
            g2.setColor(PATH_COLOR);
            MazeCoord currentLoc;
            MazeCoord nextLoc = iter.next();

            while(iter.hasNext()){

                currentLoc = nextLoc;
                nextLoc = iter.next();
                System.out.println("currentLoc: " + currentLoc);
                System.out.println("nextLoc: " + nextLoc);
                
                double x1 = mazeX + currentLoc.getCol() * BOX_WIDTH + BOX_WIDTH/2;
                double y1 = mazeY + currentLoc.getRow() * BOX_HEIGHT + BOX_HEIGHT/2;
                double x2 = mazeX + nextLoc.getCol() * BOX_WIDTH + BOX_WIDTH/2;
                double y2 = mazeY + nextLoc.getRow() * BOX_HEIGHT + BOX_HEIGHT/2;
                
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }

        }
    }
   
}



