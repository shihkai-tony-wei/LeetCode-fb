/* 1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.
*/
import java.util.*;
public class TwoSum_1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();	// (target-num, index)
        int[] idx = new int[2];
        for (int i = 0; i < nums.length; ++i) {
        	if (map.containsKey(nums[i])) {
        		idx[0] = map.get(nums[i]);
        		idx[1] = i;
        		break;
        	}
        	map.put(target - nums[i], i);
        }
        return idx;
    }

	/* follow-up:
		There may be duplicate numbers.
	*/
    public static List<int[]> twoSumDup(int[] nums, int target) {
    	List<int[]> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();	// (target-num, index)
        for (int i = 0; i < nums.length; ++i) {
        	if (map.containsKey(nums[i])) {
        		int[] idx = new int[2];
        		idx[0] = map.get(nums[i]);
        		idx[1] = i;
        		ans.add(idx);
        	}
        	map.put(target - nums[i], i);
        }
        return ans;
    }

    public static void main(String[] args) {
    	int[] nums = {1,5,3,5,8,7,-1,9,0,6};
    	List<int[]> ans = TwoSum_1.twoSumDup(nums, 8);
    	for (int[] v : ans) {
    		System.out.println("[" + v[0] + ", " + v[1] + "]");
    	}
    }
}