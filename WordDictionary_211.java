/* 211. Add and Search Word - Data structure design
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
*/

public class WordDictionary_211 {

	private class TrieNode {
		TrieNode[] child;
		boolean isWord;
		TrieNode() {
			child = new TrieNode[26];
			isWord = false;
		}
	}

	private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
    	TrieNode x = root;
        for (int i = 0; i < word.length(); ++i) {
        	int idx = word.charAt(i) - 'a';
        	if (x.child[idx] == null)
        		x.child[idx] = new TrieNode();
        	x = x.child[idx];
        }
        x.isWord = true;
    }
    
    /** Returns if the word is in the data structure. 
    A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
    	return search(root, word, 0);
    }

    // recursion
    private boolean search(TrieNode x, String word, int idx) {
    	if (idx == word.length())
    		return x.isWord;

    	char ch = word.charAt(idx);
    	if (ch == '.') {
    		// if the current char is a dot, need to search all possible subtrees
    		// anyone matches -- return true (only need existence); 
    		for (int i = 0; i < 26; ++i) {
    			// *** Must check if null before moving to the child node!!!
    			if (x.child[i] != null && search(x.child[i], word, idx + 1))
    				return true;
    		}
    		// if didn't find a match in 26 children -- false
    		return false;
    	} else if (x.child[ch - 'a'] != null) {
    		return search(x.child[ch - 'a'], word, ind + 1);
    	} else {
    		return false;
    	}
    }
}