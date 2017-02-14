/*
Number of palindromes in a string.
1. Try extending the palindromes from each index
2. consider both from a single index i -- odd length, and from (i, i + 1) -- even length
*/

public class PalindromeCount {
	public static int numberOfPalindromes(String s) {
		if (s == null || s.length() == 0) return 0;
		int total = 0;
		for (int i = 0; i < s.length() - 1; ++i) {
			total += count(s, i, i);
			total += count(s, i, i + 1);
		}
		return total + 1;	// *** the last element is a palindrome!!! ***
	}

	// consider even/odd length by extending from some index
	private static int count(String s, int lo, int hi) {
		int cnt = 0;
		while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
			++cnt;
			--lo;
			++hi;
		}
		return cnt;
	}

	public static void main(String[] args) {
		String s = "abba";
		System.out.println(PalindromeCount.numberOfPalindromes(s));
	}
}