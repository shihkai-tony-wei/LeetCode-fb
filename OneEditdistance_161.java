/* 161. One Edit Distance

Length difference is very important.
(1) replace:
	s = a b c d
	t = a z c d
(2) delete:
	s = a b c d
	t = a   c d
(3) add:
	s = a   c d
	t = a b c d
*/

public class OneEditdistance_161 {
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length(), nt = t.length();
        if (Math.abs(ns - nt) > 1) return false;

        for (int i = 0; i < Math.min(ns, nt); ++i) {
        	if (s.charAt(i) != t.charAt(i)) {
        		if (ns == nt) {		// only replacement possible
        			return s.substring(0, i).equals(t.substring(0, i)) 
        				&& s.substring(i + 1, ns).equals(t.substring(i + 1, nt));
        		} else if (ns == nt + 1) {	// delete	
        			return s.substring(0, i).equals(t.substring(0, i)) 
        				&& s.substring(i + 1, ns).equals(t.substring(i, nt));
        		} else {	// add
        			return s.substring(0, i).equals(t.substring(0, i)) 
        				&& s.substring(i, ns).equals(t.substring(i + 1, nt));
        		}
        	}
        }
        // *** corner case: one string is empty, for loop never runs
        return ns != nt;
    }
}