
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordFinder class
 * 
 * Program to read in a rack of Scrabble tiles and display all possible words that can be made from input
 * in the dictionary you specify.
 * 
 * How to call it from the command line:
 * 
 *      java WordFinder dictionaryFile
 * 
 * where dictionaryFile is a text file of the Scrabble dictionary you want to use. The dictionary file contains
 * one word in each line in alphabetic order as following:
 * 
 * aa
 * aah
 * aahed
 * aahing
 * ...
 * 
 * when no dictionary specified by user, a defalt dictionary will be used instead.
 */

public class WordFinder{
	public static void main(String[] args){

		try{

			String dicFileName;
			if (args.length < 1){
				dicFileName = "sowpods.txt";
			}
			else{
				dicFileName = args[0];
			}

			AnagramDictionary dicMap = new AnagramDictionary(dicFileName);
			// hard-coded score table
			int[] inScoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

			// initial program output
			System.out.println("Type . to quit.");

			Scanner in = new Scanner(System.in);
			boolean flag = true;
			while(flag){
				System.out.print("Rack? ");			
				String inString = in.nextLine();

				if(!inString.equals(".")){
					Rack rack = new Rack(inString);
					ArrayList<String> words = dicMap.findWords(rack);
					System.out.println("We can make " + words.size() + " words from \"" + rack.getSortedRack(inString) + "\"");
					if(words.size() != 0){
						System.out.println("All of the words with their scores (sorted by score):");
						// compute corresponding Scrabble score for words
						ScoreTable scoreTable = new ScoreTable(inScoreTable);
						// print words in decreasing order of Scrabble score
						scoreTable.printScoreWords(words);
					}
				}
				else{
					flag = false;} //when "." detected, terminates the program			
			}
		}

		catch(FileNotFoundException exception){
			System.out.println("File not found. " + exception.getMessage());
		}
	}
}