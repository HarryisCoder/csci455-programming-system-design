
// CS 455 PA4
// Spring 2017

import java.util.Comparator;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 * A Score Table to compute corresponding Scrabble score for each word
 */

public class ScoreTable{


	private Map<String, Integer> scoreWords;
	private int[] scoreTable;

	/**
	   Constructs a score table from input
	*/
	public ScoreTable(int[] scoreTable){

		this.scoreTable = scoreTable;
	}

	/**
		Computes the corresponding Scrabble score for each word, and displays them in decreasing order by score. 
	*/
	public void printScoreWords(ArrayList<String> words){

		//compute scrabble score
		int score = 0;
		scoreWords = new TreeMap<String, Integer>();
		for(int i = 0; i < words.size(); i++){
			String currentWord = words.get(i);
			for(int j = 0; j < currentWord.length(); j++){
				score += scoreTable[currentWord.charAt(j) - 'a'];
			}
			scoreWords.put(currentWord, score);
			score = 0;				
		}
		//display results
		ArrayList<Map.Entry<String, Integer>> sortedScoreWords = new ArrayList<Map.Entry<String, Integer>>(scoreWords.entrySet());
		Collections.sort(sortedScoreWords, new ScoreComparator());

		for(int i = 0; i < sortedScoreWords.size(); i++){
			Map.Entry<String, Integer> currentEntry = sortedScoreWords.get(i);
			System.out.println(currentEntry.getValue() + ": " + currentEntry.getKey());
		}
	};
}

/**
 * Comparator used to sort words by corresponding Scrabble score
 */
class ScoreComparator implements Comparator<Map.Entry<String, Integer>>{
	public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
		return b.getValue() - a.getValue();

	}
}
