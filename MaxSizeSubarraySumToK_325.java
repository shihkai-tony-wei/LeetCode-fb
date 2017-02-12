/*325. Maximum Size Subarray Sum Equals k

(1) If only positive numbers use two pointers is enough.
(2) When negatives exist, use prefix sum and hope for prefixSum[j] - prefixSum[i - 1] = k,
use a hash table to store the (prefixSum, index) pair for later lookup.
*/
public class MaxSizeSubarraySumToK_325 {
    public int maxSubArrayLen(int[] nums, int k) {
    	int maxSz = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) 
                maxSz = i + 1;
        	else if (map.containsKey(sum - k)) {
        		maxSz = Math.max(maxSz, i - map.get(sum - k));
        	}
        	// keep the duplicate key's index as small as possible
        	if (!map.containsKey(sum))
        	    map.put(sum, i);
        }
        return maxSz;
	}
}