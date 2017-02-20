/*140. Word Break II
Return all possible breaks.
*/

public class WordBreakII_140 {
	private Map<String, LinkedList<String>> map = new HashMap<>();		// memorized version

    public List<String> wordBreak(String s, List<String> wordDict) {
    	if (map.containsKey(s)) {	// if we have searched already, just return
    		return map.get(s);
    	}

    	LinkedList<String> ans = new LinkedList<>();	// otherwise, create a new List of answer
    	// suppose the whole word is in wordDict, then we would call on empty string later ""
    	// this is the termination criterion for the recursion!!!
    	if (s.length() == 0) {
    	    // ***VERY IMPORTANT: add empty string for termination
    	    // otherwise, in the for loop below: if sublist == null, then word cannot be added to result
    	    ans.add("");   
    		return ans;
    	}

    	for (String word : wordDict) {
    		if (s.startsWith(word)) {
    			// recursively find the list that starts at the end of current word
    			List<String> sublist = wordBreak(s.substring(word.length()), wordDict);
    			for (String sub : sublist) {
    			    if (sub.equals(""))
    			        ans.add(word);
    			    else
        				ans.add(word + " " + sub);
    			}
    		}
    	}
    	map.put(s, ans);
    	return ans;
    }
}