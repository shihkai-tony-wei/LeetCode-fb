/* 277. Find the Celebrity

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

You are given a helper function bool knows(a, b) which tells you whether A knows B. 
Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
Time: O(n) -- two passes
*/

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class FindCelebrity_277 extends Relation {
    public int findCelebrity(int n) {
        // 1. find potential candidate
        if (n <= 1) return n;
        int candidate = 0;
        for (int i = 1; i < n; ++i) {
        	if (!knows(i, candidate))
        		candidate = i;
        }

        // 2. validate this candidate is indeed a celebrity
        for (int i = 0; i < n; ++i) {
        	if (i == candidate) continue;
        	if (!knows(i, candidate) || knows(candidate, i))
        		return -1;
        }
        return candidate;
    }
}