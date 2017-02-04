/*
LC 125. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
*/

public class ValidPalindrome_125 {
	public boolean isPalindrome(String s) {
		if (s.length() <= 1) return true;
        int i = 0, j = s.length() - 1;
        while (i < j) {
        	while (i < j && !Character.isLetterOrDigit(s.charAt(i)))
        		i++;
        	while (i < j && !Character.isLetterOrDigit(s.charAt(j)))
        		j--;
        	if (i < j) break;
        	if (s.charAt(i).toLowerCase() != s.charAt(j).toLowerCase()) 
        		return false;
        	i++; j--;
        }
        return true;
    }

    // using regular expression
    public boolean isPalindrome_regex(String s) {
		if (s.length() <= 1) return true;
		s = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
        	if (s.charAt(i) != s.charAt(j))
        		return false;
        	i++; j--;
        }
        return true;
    }
}