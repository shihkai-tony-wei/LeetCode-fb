/*Run Length Encoding
Given an input string, write a function that returns the Run Length Encoded string for the input string.

For example, if the input string is "wwwwaaadexxxxxx", then the function should return "w4a3d1e1x6".
time: O(n)
*/

import java.util.*;
public class RunLengthEncoding {
	public static String encode(String s) {
		if (s == null || s.length() == 0) return "";
		StringBuilder sb = new StringBuilder();
		int count = 1;
		for (int i = 0; i < s.length() - 1; ++i) {
			if (s.charAt(i + 1) == s.charAt(i)) {
				++count;
			} else {
				sb.append(s.charAt(i)).append(count);
				count = 1;
			}
		}
		// very important for string processing: pay attention to where for loop ends
		sb.append(s.charAt(s.length() - 1)).append(count);
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "wwwwaaaaabbxxx";
		System.out.println(RunLengthEncoding.encode(s));
	}
}