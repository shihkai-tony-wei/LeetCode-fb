// LC 17. Letter Combinations of a Phone Number

public class LetterCombinationOfPhoneNumber_17 {
	/*  Method 1: iterative
        Use a FIFO queue to track the characters.
		For each digit in the input string, append the corresponding letter at the end of each dequed element.
		Remember to record the size of the queue in each iteration.
	*/
    public List<String> letterCombinations(String digits) {
    	if (digits.length() == 0) return new ArrayList<>();
    	String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    	// use linkedlist as the type of q, because List interface doesn't support "offer/poll"
       	LinkedList<String> q = new LinkedList<String>();
       	q.offer("");
       	for (char ch : digits.toCharArray()) {
       		int i = ch - '0';
       		int sz = q.size();		// record the size before any deque or enqueue operations
       		while (sz-- > 0) {
       			String curr = q.poll();
       			for (char letter : letters[i].toCharArray()) {
       				q.offer(curr + letter);
       			}
       		}
       	}
       	return q;
    }

    /* Method 2: recursion -- backtracking

       Template of backtracking:
            - for each possible current move:
                a. try a move
                b. if valid, recursive calls on all future moves (that satisfies a certain condition)
                   otherwise break
                c. once done with step b for all future moves, undo the current move.

    */
    private String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        backtrack(digits, ans, new StringBuilder(), 0);
        return ans;
    }

    private void backtrack(String digits, List<String> ans, StringBuilder sb, int pos) {
        if (pos == digits.length()) {   // when pos is out-of-bound
            ans.add(sb.toString());
            return;
        }

        String curr = letters[digits.charAt(pos) - '0'];
        for (int i = 0; i < curr.length(); ++i) {
            // add current char to sb
            sb.append(curr.charAt(i));

            // recursive calls on the remaining parts
            backtrack(digits, ans, sb, pos + 1);

            // after we are done with the trailing string, undo the current move
            sb.setLength(sb.length() - 1);
        }
    }
}