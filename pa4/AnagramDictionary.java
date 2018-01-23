
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
   private Map<String, ArrayList<String>> dicMap;

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {

      File inFile = new File(fileName);
      Scanner in = new Scanner(inFile);
      //constructs a map where the key is sorted anagram word, the value is an arraylist of anagram words corresponding to that sorted word
      dicMap = new HashMap<String, ArrayList<String>>();
      while(in.hasNext()){
        String currentWord = in.next();
        char[] charArray = currentWord.toCharArray();
        Arrays.sort(charArray);
        String sortedWord = new String(charArray);
        if (dicMap.get(sortedWord) == null){
          ArrayList<String> wordArray = new ArrayList<String>();
          wordArray.add(currentWord);
          dicMap.put(sortedWord, wordArray);
        }
        else{
          ArrayList<String> wordArray = (ArrayList<String>)dicMap.get(sortedWord);
          wordArray.add(currentWord);
        }
      }
   }
   /**
      returns an arraylist of anagram words of input rack in dictionary
   */
   public ArrayList<String> findWords(Rack rack){

      ArrayList<String> rackSubsets = rack.getAllSubsets();
      ArrayList<String> words = new ArrayList<String>();

      for(int i = rackSubsets.size()-1; i >= 0; i--){

        ArrayList<String> anagramSet = getAnagramsOf(rackSubsets.get(i));
        if(anagramSet != null){
          for(int j = 0; j < anagramSet.size(); j++){
            words.add(anagramSet.get(j));
          }
        }
      }
      return words;
   };
   
   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {

       return dicMap.get(s); 
   }
   
   
}
