package KT;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class TextReader {

	public static void main(String[] args) throws IOException {
		TextReader tr = new TextReader();
		int[] numbers = Arrays.stream(tr.readFile("numbers.txt").split("\\s+|,\\s*|\\.\\s*")).mapToInt(Integer::parseInt).toArray();
		String[] words = tr.readFile("words.txt").split(" ");
		tr.printMaxMinAvg(numbers);
		System.out.println(tr.countLetters(words, 'a'));
		System.out.println(tr.getWordCount(words));
	}

	public String readFile (String name) throws IOException {
		return new String(Files.readAllBytes(Paths.get(name)), StandardCharsets.UTF_8);
	}
	
	public String getWordCount (String[] words) {
		String result = "";
		HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
		for (String word : words) {
			Integer value = wordsMap.get(word);
			if (value != null) {				
				wordsMap.put(word, wordsMap.get(word) + 1);
			} else {
				wordsMap.put(word, 1);
			}
		}
		
		for(Entry<String, Integer> m : wordsMap.entrySet()) {
			result += m.getKey() + ": " + m.getValue() + "; ";
		}
		
		return result;
	}
	
	public int countLetters (String[] words, char c) {
		int count = 0;
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) != c) {
					count ++;
				}
			}
		}
		return count;
	}
	
	public void printMaxMinAvg (int[] numbers) {
		int max = numbers[0];
		int min = numbers[0];
		double sum = 0;
		double avg;
		
		for (int number : numbers) {
			if (number < min) {
				min = number;
			} else if (number > max) {
				max = number;
			}
			sum += number;
		}
		avg = sum / numbers.length;
		System.out.println("MAX: " + max + " MIN: " + min + " AVG: " + avg);
	}
}
