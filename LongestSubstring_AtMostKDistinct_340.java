/*
LC 340. Longest Substring with At Most K Distinct Characters

Idea: two pointers one slow, another fast
KEY point: fast forward pointer j, ***[until one past the last allowed element]***

Use a hashmap or array to store the number of occurrences
time: O(n) -- each pointer iterates through the String once
space: O(1) -- constant because there are only finite number of chars
*/

public class LongestSubstring_AtMostKDistinct_340 {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s.length() == 0 || k == 0) return 0;
		int maxLen = 0, start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < s.length(); ++i) {
        	while (j < s.length() && map.size() <= k) {
        		// fast forward pointer j, until one past the last allowed element
        		// ***when size is equal to k, we may add elements already in the map***
        		if (map.containsKey(s.charAt(j))) {
        			map.put(s.charAt(j), map.get(s.charAt(j)) + 1);
        		} else {
        			if (map.size() == k) break;
        			map.put(s.charAt(j), 1);
        		}
        		++j;
        	}

        	// update length
        	if (maxLen < j - i) {
        		maxLen = j - i;
        		start = i;
        		end = j;	// end is exclusive
        	}

        	// update i now
        	if (map.get(s.charAt(i)) == 1)
        		map.remove(s.charAt(i));
        	else
        		map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
        }
        System.out.println(s.substring(start, end + 1));
        return maxLen;
    }
}

/*
follow up: return the substring that satisfies the condition
Just keep track of the best start and end pair.
*/