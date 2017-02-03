/*
Find the most frequent letter in a string
*/
import java.util.*;

public class MostFrequentLetter {
	// method 1: hashmap count
	// time: O(n), space: constant (<= 256)
	public char findMostFrequent1(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char ch : s.toCharArray()) {
			if (!Character.isLetterOrDigit(ch)) continue;
			ch = Character.toLowerCase(ch);		// no distinction for upper/lower cases
			if (!map.containsKey(ch)) {
				map.put(ch, 1);
			} else {
				map.put(ch, map.get(ch) + 1);
			}
		}
		int maxCount = 0;
		char best = '\u0000';	// '\u0000' stands for null character
		for (char ch : map.keySet()) {
			if (map.get(ch) > maxCount) {
				maxCount = map.get(ch);
				best = ch;
			}
		}
		return best;
	}

	// method 2: optimize running time
	// Keep track of the most and second most frequent
	// if the count1 - count2 > # of remaining characters, then we're done!

	public static void main(String[] args) {
		MostFrequentLetter obj = new MostFrequentLetter();
		String s = "mISs..;*%%%%%%%%         iSsipp";
		System.out.println(obj.findMostFrequent1(s));
	}
}