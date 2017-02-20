/*
LC 282. Expression Add Operators
Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
*/

/*
Idea: backtracking with recursion
e.g. num = "1234", target = 3
(1) try (1 + 2), (1 - 2), (1 * 2), keep track of the eval result AND the last number for potential multiplication later
(2) If we have eval = (1 + 2) = 3 in step (1), then try
	(1 + 2) + 3, (1 + 2) - 3  --> eval = eval + curr
	[Here eval = 1 + 2 = 3, mult = 2 (last number)]

	OR special for multiplication:
	1 + 2 * 3 = [(1 + 2) - 2] + 2 * 3 --> eval = (eval - mult) + mult * curr
	
(3) Do this until the end, if the eval is the target, add the current path to ans. 
	Thus, a path of history is also to be recorded along the recursion.
(4) Corner cases:
	a. overflow -- use long for (curr, eval, mult)
	b. a sequence (of more than 1 character) cannot start with '0'
**********************************************************************
Common error using StringBuilder: append too many at the end.
Solution: before doing anything to the sb, record its length, and setLength back after.
*/

public class ExpressionAddOperators_282 {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<String>();
        helper(ans, new StringBuilder(), num, target, 0, 0, 0);
        return ans;
    }

    private void helper(List<String> ans, StringBuilder path, String num, int target, int pos, long eval, long mult) {
    	if (pos == num.length()) {
    		if (eval == target) {
    			ans.add(path.toString());
    			return;
    		}
    	}

    	for (int i = pos; i < num.length(); i++) {
    		if (i > pos && num.charAt(pos) == '0') return;        // a non digit can't start with 0
    		long curr = Long.valueOf(num.substring(pos, i + 1));  // try all possible number
    		int len = path.length();
    		if (pos == 0) {       // special case: index 0 doesn't need an preceding operator
    			helper(ans, path.append(curr), num, target, i + 1, curr, curr);
    			path.setLength(len);
    		} else {
    			// try plus
    			helper(ans, path.append("+").append(curr), num, target, i + 1, eval + curr, curr);
    			path.setLength(len);


    			// minus
    			helper(ans, path.append("-").append(curr), num, target, i + 1, eval - curr, -curr);
    			path.setLength(len);


    			// multiply: tricky one
    			helper(ans, path.append("*").append(curr), num, target, i + 1, eval - mult + mult * curr, mult * curr);
    			path.setLength(len);
    		}
    	}
    }
}

// time: O(3^n), because for each number you get 3 choices in front of it (except the first one)
// space: O(n) for recursion stack, output is O(3^n)