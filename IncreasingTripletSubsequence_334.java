/*334. Increasing Triplet Subsequence

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Idea: record the smallest and second smallest so far.
*/

public class IncreasingTripletSubsequence_334 {
    public boolean increasingTriplet(int[] nums) {
        int smallest = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int x : nums) {
        	if (x <= smallest) {
        		smallest = x;
        	} else if (x <= second) {
        		second = x;
        	} else {
        		return true;
        	}
        }
        return false;
    }
}