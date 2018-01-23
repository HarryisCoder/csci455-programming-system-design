
// CS 455 PA3
// Spring 2017

import java.util.LinkedList;
import java.util.Arrays;



/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;

   private MazeCoord startLoc;
   private MazeCoord exitLoc;
   boolean[][] mazeArray;  
   boolean[][] visitedPoints;  
   int[][] memo;  
   LinkedList<MazeCoord> path;

   

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {

      mazeArray = new boolean[mazeData.length + 2][mazeData[0].length + 2];
      visitedPoints = new boolean[mazeData.length][mazeData[0].length];
      memo = new int[mazeData.length + 2][mazeData[0].length + 2];
      path = new LinkedList<MazeCoord>();
      for (boolean[] row: mazeArray){
          Arrays.fill(row, WALL);
        }
      for (boolean[] row: visitedPoints){
          Arrays.fill(row, false);
        }
      for (int[] row: memo){
          Arrays.fill(row, -1);
        }
      for(int i = 0; i < mazeData.length; i++){
          for(int j = 0; j < mazeData[0].length; j++){
              mazeArray[i+1][j+1] = mazeData[i][j];
          }     
      }
      
      //System.out.println("mazeArray: ");
      //for(int i = 0; i < mazeArray.length; i++){
      //    System.out.println(Arrays.toString(mazeArray[i]));
      //}
      

      this.startLoc = startLoc;
      this.exitLoc = exitLoc;
 
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
   public int numRows() {
      return mazeArray.length - 2;   // DUMMY CODE TO GET IT TO COMPILE
   }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {
      return mazeArray[0].length - 2;   // DUMMY CODE TO GET IT TO COMPILE
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      return mazeArray[loc.getRow() + 1][loc.getCol() + 1] == WALL;   // DUMMY CODE TO GET IT TO COMPILE
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;   // DUMMY CODE TO GET IT TO COMPILE
   }
   
   
   /**
     Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return exitLoc;   // DUMMY CODE TO GET IT TO COMPILE
   }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

      return path; 

   }


   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
   public boolean search()  {  
      
      //boolean ans = recursiveSearch(this.startLoc.getCol(), this.startLoc.getRow()); 

      //System.out.println("has path?: " + ans);
      //System.out.println("memo: ");
      //for(int i = 0; i < memo.length; i++){
      //    System.out.println(Arrays.toString(memo[i]));
      //}
       
      return recursiveSearch(this.startLoc.getCol(), this.startLoc.getRow());

   }

   public boolean recursiveSearch(int x, int y)  {  
      
       //System.out.println("startLoc: " + this.startLoc.toString());
       //int y = currentLoc.getRow();
       //int x = currentLoc.getCol();
       //System.out.print("[" + x + ", " + y + "] ");

       /*
       if(memo[y + 1][x + 1] > -1){
           //System.out.println("explored");
           return ((memo[y + 1][x + 1] == 1)?true:false);
       }
       */

       if(mazeArray[y + 1][x + 1] == WALL || visitedPoints[y][x]){
           //System.out.println("at wall || visited");
           //memo[y + 1][x + 1] = 0;
           return false;
       }
       else if(x == this.exitLoc.getCol() && y == this.exitLoc.getRow()){
           //System.out.println("at exitLoc");
           path.add(new MazeCoord(y, x));
           //memo[y + 1][x + 1] = 1;
           return true;
       }
       /*
       else if(visitedPoints[y][x]){
           //System.out.println("visited");
           memo[y + 1][x + 1] = 0;
           return false;
       }
       */

       else{
           //System.out.println("being explored");
           visitedPoints[y][x] = true;
           boolean ans = recursiveSearch(x, y - 1) ||
                         recursiveSearch(x - 1, y) ||
                         recursiveSearch(x, y + 1) ||
                         recursiveSearch(x + 1, y);

           if(ans == true){
               path.add(new MazeCoord(y, x));
               //memo[y + 1][x + 1] = 1;
           }
           //else if(ans == false){memo[y + 1][x + 1] = 0;}

           return ans;
       }

   }

   //public String toString() {
   //   return "MazeCoord[row=" + row + ",col=" + col + "]";
   //}
}
