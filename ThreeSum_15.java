/*
LC 15. 3Sum
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
*/

/* Idea:
Method 1: Hash table

Method 2: sort and use two pointers as in 2-sum

For both methods:
In order to have UNIQUE elements, **when a solution is found**, skip all consecutive duplicates!
*/

public class ThreeSum_15 {
	// Method 1: hash table
	// time O(n^2), space O(n)
	public List<List<Integer>> threeSum1(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
        for (int k = 0; k < nums.length; ++k) {
        	if (k > 0 && nums[k] == nums[k - 1]) continue;
        	int target = -nums[k];
        	Set<Integer> set = new HashSet<Integer>();
        	for (int i = k + 1; i < nums.length; ++i) {
        		if (!set.contains(nums[i])) {
        			set.add(target - nums[i]);
        		} else {
        			ans.add(new ArrayList<Integer>(Arrays.asList(-target, target-nums[i], nums[i])));
        			// skip duplicates once a match is found
        			while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        		}
        	}
        }
        return ans;
    }


    // Method 2: two pointers
    // time O(n^2), space O(1)
	public List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		for (int k = 0; k < nums.length; ++k) {
			if (k > 0 && nums[k] == nums[k - 1]) continue;
			int target = -nums[k];
			int i = k + 1, j = nums.length - 1;
			while (i < j) {
				if (nums[i] + nums[j] == target) {
					ans.add(new ArrayList<Integer>(Arrays.asList(-target, nums[i], nums[j])));
					// once a solution is found, skip all duplicates
					while (i < j && nums[i] == nums[i + 1]) i++;
					while (i < j && nums[j] == nums[j - 1]) j--;
					i++; j--;		// without this update, the program will be in infinite loop...
				} else if (nums[i] + nums[j] < target) {
					i++;
				} else {
					j--;
				}
			}
		}
		return ans;
	}
}

/*
****************************************
Follow-ups to 3-sum
****************************************
*/