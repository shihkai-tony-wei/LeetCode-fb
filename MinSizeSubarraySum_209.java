/*209. Minimum Size Subarray Sum
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
*/

public class MinSizeSubarraySum_209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int minLen = Integer.MAX_VALUE;
        int j = 0, sum = 0;
        for (int i = 0; i < nums.length; ++i) {
        	while (j < nums.length && sum < s) {
        		sum += nums[j++];
        	}
        	if (sum >= s)	// need to check b/c j might be out-of-bounds
        		minLen = Math.min(minLen, j - i);
        	sum -= nums[i];
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;	// may not exist at all!!!
    }
}