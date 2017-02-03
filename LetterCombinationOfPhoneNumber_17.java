// LC 17. Letter Combinations of a Phone Number

public class LetterCombinationOfPhoneNumber_17 {
	/*  Use a FIFO queue to track the characters.
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
}