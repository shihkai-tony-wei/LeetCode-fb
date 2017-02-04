/*
Follow-up to 3 sum. Find k-sum.
Idea: reduction -- k -> k-1 -> k-2 ...-> 2 sum

time O(n^(k-1)) because it stops at 2 sum.
*/
import java.util.*;
public class KSum {
	public static List<List<Integer>> kSum(int[] nums, int k, int target) {
		Arrays.sort(nums);
		return kSum_helper(nums, k, target, 0);
	}

	// Input here should be sorted!
	private static List<List<Integer>> kSum_helper(int[] nums, int k, int target, int pos) {
		// base case - 2 sum
		if (k == 2) {
			return twoSum(nums, target, pos);
		}

		List<List<Integer>> ans = new ArrayList<>();

		for (int i = pos; i < nums.length; ++i) {
			if (i > pos && nums[i] == nums[i - 1]) continue;
			List<List<Integer>> subSum = kSum_helper(nums, k - 1, target - nums[i], i + 1);
			for (List<Integer> sublist : subSum) {
				sublist.add(0, nums[i]);
				ans.add(sublist);
			}
		}
		return ans;
	}

	private static List<List<Integer>> twoSum(int[] nums, int target, int pos) {
		List<List<Integer>> ans = new ArrayList<>();
		int i = pos, j = nums.length - 1;
		while (i < j) {
			int sum = nums[i] + nums[j];
			if (sum < target) {
				i++;
			} else if (sum > target) {
				j--;
			} else {	// found it
				ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[j])));
				while (i < j && nums[i] == nums[i + 1]) i++;
				while (i < j && nums[j] == nums[j - 1]) j--;
				i++; j--;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,7,8};
		List<List<Integer>> list = KSum.kSum(nums, 3, 8);
		for (List<Integer> sublist : list) {
			System.out.println(Arrays.toString(sublist.toArray()));
		}
	}
}