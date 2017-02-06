/*
139. Word Break

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".


Idea: dynamic programming
- dp[i] = true if first i characters make up of a string in dictionary (ending index is = i - 1)
- dp[i] = true if there exists dp[j] = true AND substring(j, i) is also in the dictionary
- initially, dp[0] = true; answer = dp[n].
*/

public class WordBreak_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
    	// create a set for constant time lookup
        Set<String> set = new HashSet<>();
        for (String s : wordDict) {
        	set.add(s);
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        

        for (int i = 1; i <= n; ++i) {
        	// for each ending, check all substrings starting at index 0
        	for (int j = 0; j < len; ++j) {
        		if (dp[j] && set.contains(s.substring(j, i))) {
        			dp[i] = true;	// found a match, break here
        			break;
        		}
        	}
        }
        return dp[n];
    }


    // follow up: print one of the solutions   
    public boolean wordBreak(String s, List<String> wordDict) {
        // create a set for constant time lookup
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int[] pos = new int[n + 1];     // store the previous word's ending index
        for (int i = 1; i <= n; ++i) {
            pos[i] = -1;
        }

        for (int i = 1; i <= n; ++i) {
            // for each ending, check all substrings starting at index 0
            for (int j = 0; j < i; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;   // found a match, break here
                    pos[i] = j;
                    break;
                }
            }
        }
        if (dp[n] == true) {
            // print one solution
            int k = n;
            while (k > 0) {
                System.out.print(s.substring(pos[k], k) + " ");
                k = pos[k];
            }
        }
        return dp[n];
    }
}