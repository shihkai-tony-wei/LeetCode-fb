/*
Return random number by weights.
Cumulative sum and random sample.
e.g.
nums   = [1 5 3 2]
weight = [3 2 1 4]
-------------------
cumwt  = [3 5 6 10]
*/

import java.util.*;

public class WeightedRandomNumber {
	public static int weighedRand(int[] nums, int[] weights) {
		int n = nums.length;
		int[] cumsum = new int[n];
		cumsum[0] = weights[0];
		for (int i = 1; i < n; ++i) {
			cumsum[i] = cumsum[i - 1] + weights[i];
		}

		Random rnd = new Random();
		int r = rnd.nextInt(cumsum[n - 1]) + 1;	// r is in [0, max - 1] + 1

		// loop again
		for (int i = 0; i < n; ++i) {
			if (r <= cumsum[i])
				return nums[i];
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		int[] weights = {10,5,1,1};
		for (int i = 0; i < 10; ++i) {
			int rand = WeightedRandomNumber.weighedRand(nums, weights);
			System.out.println(rand);
		}
	}
}