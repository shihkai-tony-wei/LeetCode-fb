/*28. Implement strStr()

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.*/

public class StrStr_28 {
    public int strStr(String haystack, String needle) {
    	int len = needle.length();
        if (haystack.length() < len) return -1;
        for (int i = 0; i + len - 1 < haystack.length(); ++i) {		// note here the end of needle should be in range
        	if (haystack.substring(i, i + len).equals(needle))
        		return i;
        }
        return -1;
    }
}