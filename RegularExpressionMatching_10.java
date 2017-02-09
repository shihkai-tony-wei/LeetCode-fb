/*10. Regular Expression Matching
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).*/

/*
1. Recursion:
consider the 2nd char in p:
	- if it is a *, we can match empty char, or match the first in both s and p
	- otherwise, we must match the first char
***********************************************************
2. DP:
----------
States:
dp[i][j] = true if s[0...i-1] (first i chars) matches p[0...j-1] (first j chars)
----------
Transition
if p[j - 1] != '*':
	dp[i][j] = s[i - 1] == p[j - 1] && dp[i - 1][j - 1] ---- final chars match, and all precedings
if p[j - 1] == '*':
	focus on the second last char in p: p[j - 2] := x
	1) x* matches empty char: dp[i][j] = dp[i][j - 2]
	2) x* matches >= 1 chars in s: dp[i][j] = (s[i - 1] == x) && dp[i - 1][j] 
if p[j - 1] == '.':
	dp[i][j] = dp[i - 1][j - 1]
----------
*/

public class RegularExpressionMatching_10 {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() == 1) {
        	if (p.equals(".")) return s.length() == 1;
        	else return p.equals(s);
        }
        // p.length() >= 2
        if (p.charAt(1) == '*') {
        	return isMatch(s, p.substring(2))	// x* matches empty character
        		|| !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p);	// x* matches 1 character
        } else {	// Must match the first character
        	return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }
    }

    // dp solution: O(m * n)
    public boolean isMatch(String s, String p) {
    	int m = s.length(), n = p.length();
    	boolean[][] dp = new boolean[m + 1][n + 1];
    	dp[0][0] = true;

    	// initialize the dp matrix's first row & column -- 1st column[1:] are all false
    	// 1st row -- true only if all are in pattern "a*b*c*d*" and so on
    	for (int j = 1; j <= n; ++j) {
    		dp[0][j] = j > 1 && p.charAt(j - 1) == '*' && dp[0][j - 2];
    	}

    	for (int i = 1; i <= m; ++i) {
    		for (int j = 1; j <= n; ++j) {
    			if (p.charAt(j - 1) != '*')
    				dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i - 1][j - 1];
    			else
    				dp[i][j] = dp[i][j - 2] 
    						|| (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j];
    		}
    	}
    	return dp[m][n];
    }
}