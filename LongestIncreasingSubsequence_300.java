/*
LC300. Longest Increasing Subsequence
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 4],
The longest increasing subsequence is [2, 3, 4], therefore the length is 3. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/

/* Idea: dynamic programming
Just like the 2d increasing subsequence, we focus on the TAIL of a subsequence.
If j is the tail of a subsequence, then find the maxLen ending at j.
dp[j] = max len of the subsequence that ends at j.
*/

public class LongestIncreasingSubsequence_300 {
	// method 1: dp
	// time O(n^2)
	// space O(n)
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int n = nums.length;
		int[] dp = new int[n];
		int ans = 0;
		for (int j = 0; j < n; j++) {
			dp[j] = 1;	// initialize to 1
			// go back and find best subsequence
			for (int i = 0; i < j; i++) {
				if (nums[i] < nums[j]) {
					// if found a smaller one than the tail, update dp[j] when necessary
					dp[j] = Math.max(dp[j], dp[i] + 1);
				}
			}
			ans = Math.max(ans, dp[j]);
		}
		return ans;
    }

    /* method 2: binary search: keep the tail of a sequence of all possible lengths
    tail[i] = tail number is a sequence of size (i + 1)
    For each newcomer A[i]:
    	a. if A[i] is the smallest among all tails -- a potential starting point
    	b. if A[i] is the largest among all tails
    		- clone the largest, append by A[i], and add this to tail list[sz]
    		- increment the maximum size we are considering
    	c. if A[i] is in between
    		- Find the smallest tail that is larger than A[i] (binary search)
    		- clone that one, append by A[i], and replace the tail element of same size

    Time: O(nlogn)
    */
    public int lengthOfLIS(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	int n = nums.length;
    	int[] tail = new int[n];
		int sz = 0;
		for (int x : nums) {
			int lo = 0, hi = sz;
			while (lo < hi) {
				int mid = lo + (hi - lo) / 2;
				if (tail[mid] < x)
					lo = mid + 1;
				else	// tail[mid] >= x (can handle duplicate elements)
					hi = mid;
			}
			// now lo points to the largest tail which is less than x
			tail[lo] = x;		// update the tail next to lo
			if (lo == sz) ++sz;
		}
		return sz;
    }
}