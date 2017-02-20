/*
Compare two strings in a natural way:
1. compare letters by order
2. letter > number
3. both are numbers: take the WHOLE number and compare
4. if everything else the same, the longer string is larger. 
*/

import java.util.*;
public class NaturalString {

	public static Comparator<String> NaturalCompare = new Comparator<String>() {
		@Override
		public int compare(String s, String t) {
			int i = 0, j = 0;
			while (i < s.length() && j < t.length()) {
				char a = s.charAt(i), b = t.charAt(j);
				// if exactly the same, just skip
				if (a == b) {
					++i; ++j; continue;
				}

				
				if (Character.isDigit(a) && Character.isDigit(b)) {		// both are numbers but different
					// move to end of number
					int num1 = 0, num2 = 0;
					while (i < s.length() && Character.isDigit(s.charAt(i))) {
						num1 = 10 * num1 + s.charAt(i) - '0';
						++i;
					}
					while (j < t.length() && Character.isDigit(t.charAt(j))) {
						num2 = 10 * num2 + t.charAt(j) - '0';
						++j;
					}
					if (num1 == num2) 
						continue;
					else 
						return num1 - num2;

				} else if (Character.isDigit(a)) {	// 
					return -1;
				} else if (Character.isDigit(b)) {
					return 1;
				} else {
					return a - b;
				}
			}	// end of while
			// now check if anyone has remaining
			if (i == s.length() && j == t.length()) 
				return 0;
			else if (i < s.length()) 
				return 1;
			else 
				return -1;
		}
	};

	public static void main(String[] args) {
		String s1 = "abc007m7";
		String s2 = "abc17mu";
		int cmp = NaturalCompare.compare(s1, s2);
		String result = cmp > 0 ? "s1 > s2" : (cmp == 0) ? "s1 == s2" : "s1 < s2";
		System.out.println(result);
	}
}