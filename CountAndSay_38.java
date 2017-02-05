public class CountAndSay_38 {
    public String countAndSay(int n) {
    	if (n <= 0) return "";
    	String s = "1";
    	StringBuilder sb;
    	while (--n > 0) {
    		sb = new StringBuilder();
    		for (int i = 0, count = 1; i < s.length(); ++i) {
    			// compare i with i+1 here because we need access to "current" number
    			while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
    				++count;
    				++i;
    			}
    			// final number is already accounted for
    			sb.append(count).append(s.charAt(i));
    			count = 1;	// reset count after number changed
    		}
    		s = sb.toString();
    	}
    	return s;
    }
}