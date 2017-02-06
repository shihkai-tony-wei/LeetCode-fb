/*
76. Minimum Window Substring
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
*/

/*
Two pointers with hash table:
keeping a count of the characters used.
*/

public class MinumumWindowSubstring_76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        // initialize the hashmap for counting
        for (char ch : t.toCharArray()) {
        	map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int minLen = Integer.MAX_VALUE, start = -1, end = -1;
        int j = 0, cnt = t.length();
        for (int i = 0; i < s.length(); ++i) {
        	// fast forward j
        	while (j < s.length() && cnt > 0) {
        		char ch = s.charAt(j);
        		// don't care about the characters not in map
        		if (map.containsKey(ch)) {
        			if (map.get(ch) > 0) --cnt;
        			map.put(ch, map.get(ch) - 1);
        		}
        		++j;
        	}
        	// update minLen, start and end ONLY IF count is 0 -- which means valid substring
        	// it could be j out-of-bounds but count is still positive
        	if (cnt == 0 && j - i < minLen) {
        		minLen = j - i;
        		start = i;
        		end = j;
        	}

        	// update i
        	char chi = s.charAt(i);
        	if (map.containsKey(chi)) {
        		map.put(chi, map.get(chi) + 1);
        		if (map.get(chi) > 0) ++cnt;
        	}
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}