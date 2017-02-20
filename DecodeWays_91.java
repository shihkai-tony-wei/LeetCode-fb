/*
LC 91. Decode Ways
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
*/


/* Idea: Dynamic programming
1. keep two variables:
	single = # of ways if using current number individually
	combo  = # of ways if using current number together with the previous one
2. Transition functions:
	single = oldSingle + oldCombo
	combo  = oldSingle	
3. Special cases:
	a. When using a combo, it's previous must be '1' or '2';
	b. '0' can't be used for single
	c. s starts with '0' -- 0 decoding ways
	e.g. "10", "0"
*/

// Method 1
// time: O(n) -- only one pass
// space: O(1) -- just two variables
public class DecodeWays_91 {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0 || s.startsWith("0")) return 0;
		int single = 1, combo = 0;
		for (int i = 1; i < s.length(); ++i) {
			int oldSingle = single;
			single = s.charAt(i) == '0' ? 0 : single + combo;
			if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
				combo = oldSingle;
			else
				combo = 0;
		}
		return single + combo;
	}
}



// Method 2: dp with array
// dp[i] = number of ways of decoding the first i numbers (note: we are dealing with the number on the left of i)
// time: O(n)
// space: O(n)
public class DecodeWays_91 {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0) return 0;
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;		// This must be 1 in order to have for loop work!
		dp[1] = s.charAt(0) == '0' ? 0 : 1;
		for (int i = 2; i <= n; ++i) {
			int single = Integer.valueOf(s.substring(i - 1, i));	// i-th in dp is (i-1)-th in original string
			int combo  = Integer.valueOf(s.substring(i - 2, i));
			if (single != 0)
				dp[i] = dp[i - 1];
			if (combo >= 10 && combo <= 26)
				dp[i] += dp[i - 2];
		}
		return dp[n];
	}
}