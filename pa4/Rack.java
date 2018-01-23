
// CS 455 PA4
// Spring 2017

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {

   private ArrayList<String> rackSubsets;
   private String unique;
   private int[] mult;

   /**
      Constructs a Rack.
      @param rackString a Rack of Scrabble tiles entered by user
   */

   public Rack(String rackString){

      // preprocess the input string
      String sortedRack = getSortedRack(rackString);

      // compute multiset of input rack for allSubsets method
      Map<Character, Integer> charCounter= new TreeMap<Character, Integer>();
      for(int i = 0; i < sortedRack.length(); i++){
        char currentChar = sortedRack.charAt(i);
        if (charCounter.get(currentChar) == null){
          charCounter.put(currentChar, 1);
        }
        else{
          charCounter.put(currentChar, charCounter.get(currentChar) + 1);
        }
      }
      mult = new int[charCounter.size()];
      
      // compute unique string of input rack for allSubsets method
      char[] uniqueChar = new char[charCounter.size()];
      int i = 0;
      for(Map.Entry<Character, Integer> entry : charCounter.entrySet()){
        uniqueChar[i] = entry.getKey();
        mult[i] = entry.getValue();
        i++;
      }
      unique = new String (uniqueChar);

   }

   /**
      returns all subsets(in alphabetic order) of input Scrabble tiles
   */
   public ArrayList<String> getAllSubsets(){

      rackSubsets = allSubsets(unique, mult, 0);
      return rackSubsets;
   }

    /**
      returns alphabetic-order, lower-case string of input Scrabble tiles
   */
   public String getSortedRack(String string){

      string = string.toLowerCase();
      char[] charArray = string.toCharArray();
      Arrays.sort(charArray);
      String sortedString = new String(charArray);
      return sortedString;

   }

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
