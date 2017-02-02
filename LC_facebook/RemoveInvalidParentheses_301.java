/*
LC 301
*/


public class RemoveInvalidParentheses_301 {
	public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<String>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    private void remove(String s, List<String> ans, int last_i, int last_j, char[] p) {
    	// p stores the current parentheses order
    	for (int counter = 0, i = last_i; i < s.length(); i++) {
    		if (s.charAt(i) == p[0]) counter++;
    		if (s.charAt(i) == p[1]) counter--;
    		if (counter >= 0) continue;

            // got to here only if counter is negative
			for (int j = last_j; j <= i; j++) {
				if (s.charAt(j) == p[1] && (j == last_j || s.charAt(j - 1) != p[1])) {
					// delete the j-th char, and i, j will automatically right shift by 1 char
					// recursive calls
					remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, p);
				}
			}
    		return;		// return: adds current string to ans after any time counter is negative
    	}
    	// when last_i is already the end, do its reverse string
    	String rev = new StringBuilder(s).reverse().toString();
    	if (p[0] == '(') 	// haven't checked rev yet
    		remove(rev, ans, 0, 0, new char[]{')', '('});
    	else	
    		// have already processed the rev string
    		// and in the final step, already reversed back again in the recursion
    		// now rev is in the original order
    		ans.add(rev);
    }
}