/*
Remove parentheses (return one result).
Using a stack to track the index of left '('.
*/

import java.util.*;
public class RemoveParentheses {
	public static String removeInvalid(String par) {
		char[] arr = par.toCharArray();
		Deque<Integer> leftStack = new LinkedList<>();
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] == '(') {
				leftStack.push(i);	// only the index
			} else {
				if (!leftStack.isEmpty()) leftStack.pop();
				else	// it is an invalid right parenthesis
					arr[i] = '#';
			}
		}
		// stack not empty means redundant left parentesis
		while (!leftStack.isEmpty()) {
			arr[leftStack.pop()] = '#';
		}
		StringBuilder sb = new StringBuilder();
		for (char ch : arr) {
			if (ch != '#')
				sb.append(ch);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String par = "(()()))";
		System.out.println(RemoveParentheses.removeInvalid(par));
	}
}