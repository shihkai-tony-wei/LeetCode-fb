/*
Check if an integer array is monotonic
*/


public class MonotonicArray {
	public static boolean isMonotonic(int[] nums) {
		if (nums == null || nums.length < 2) return false;
		int n = nums.length;
		boolean increase = nums[n - 1] - nums[0] > 0;
		for (int i = 0; i < n - 1; ++i) {
			if (increase && nums[i] > nums[i + 1])
				return false;
			if (!increase && nums[i] < nums[i + 1])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] nums = {6,4,5};
		System.out.println(MonotonicArray.isMonotonic(nums));
	}
}