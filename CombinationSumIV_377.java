/* 377. Combination Sum IV
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.*/


// dp[i] = number of combinations that sum up to i
public class CombinationSumIV_377 {
    public int combinationSum4(int[] nums, int target) {
    	if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int sum = 1; sum < dp.length; ++sum) {
        	for (int j = 0; j < nums.length; ++j) {
        		if (sum >= nums[j]) {
        			dp[sum] += dp[sum - nums[j]];
        		}
        	}
        }
        return dp[target];
    }
}