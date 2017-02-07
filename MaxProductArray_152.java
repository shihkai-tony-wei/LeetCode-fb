/* 152. Maximum Product Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

public class MaxProductArray_152 {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = nums[0];
        int max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int oldMax = max;
            max = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i] * oldMax, nums[i] * min));
            res = Math.max(res, max);
        }
        return res;
    }
}