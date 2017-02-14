/*
Given an array with no duplicates,
find all subsets of this array such that min(subset) + max(subset) < target

Idea: sort + two pointers + some bit manipulations
1. start lo, hi at two ends
2. while they do not cross
	- if sum >= target, --hi
	- else (2 steps: tricky part)
		--count how many possible pairs have sum less than target
		--given each pair, count how many subsets can be generated

Time: O(n*logn)


e.g. [1, 2, 4, 5, 8]
----------------
lo = 0, hi = 4
----------------
lo = 0, hi = 3
pairs: 
(1,5) -- 2^2 subsets
(1,4) -- 2^1 subsets
(1,2) -- 2^0 subsets
*********************************************************************
HERE, we are adding 2^2 + 2^1 + 2^0 = 2^3 - 1 = (1 << (hi - lo)) - 1 subsets
	-- put bit operations in (...)
*********************************************************************
we are done with index 0 now, ++lo
----------------
lo = 1, hi = 3
----------------
lo = 1, hi = 2
pairs:
(2,4) -- 0^2 subsets
we are done with index 1 now, ++lo
----------------
lo = 2, hi = 2 STOP
----------------
*/

import java.util.*;
public class SubsetsLessThanTarget {
	public static int numOfSubsets(int[] nums, int target) {
		Arrays.sort(nums);
		int total = 0;
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int sum = nums[lo] + nums[hi];
			if (sum < target) {
				System.out.println("lo = " + lo + ", hi = " + hi);
				System.out.println("subsets added now: " + ((1 << (hi - lo)) - 1));
				total += (1 << (hi - lo)) - 1;
				++lo;
			} else {
				--hi;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[] nums = {1,3,5,6,8,9};
		System.out.println(SubsetsLessThanTarget.numOfSubsets(nums, 10));
	}
}