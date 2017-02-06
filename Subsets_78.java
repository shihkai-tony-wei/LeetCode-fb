/*
LC78. Subsets -- distinct input
Idea: backtracking
*/
import java.util.*;
public class Subsets_78 {

	// method 1: backtraking
	// time O(2^n)
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, ans, new ArrayList<Integer>());
        return ans;
    }

    private void backtrack(int[] nums, int start, List<List<Integer>> ans, List<Integer> sublist) {
    	// first add the current sublist into answer
    	ans.add(new ArrayList<Integer>(sublist));
    	for (int i = start; i < nums.length; ++i) {
    		// add current element to sublist
    		sublist.add(nums[i]);

    		// recursively call from the rest of array
    		backtrack(nums, i + 1, ans, sublist);

    		// when we are done with all backtrackings, undo the current move
    		sublist.remove(sublist.size() - 1);
    	}
    }
 
    /* method 2: bit manipulation
		Suppose there are n numbers in the nums array -- 2^n subsets.
		Use a binary representation of n-bits to denote if the j-th number is in the set.
		--for i in 2^n = (1 << n)
			-- for j in 0 to n - 1
				if i & (1 << j) is not 0, then j-th number is in the set.
    */
                
	public List<List<Integer>> subsets_bit(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < (1 << n); ++i) {
        	List<Integer> sublist = new ArrayList<>();
        	for (int j = 0; j < n; ++j) {
        		if ((i & (1 << j)) != 0)
        			sublist.add(nums[j]);
        	}
        	ans.add(sublist);
        }
        return ans;
    }

    public static void main(String[] args) {
    	Subsets_78 ss = new Subsets_78();
    	int[] nums = {1,2,3};
    	List<List<Integer>> ans = ss.subsets_bit(nums);
    	for (List<Integer> list : ans) {
    		System.out.println(list.toString());
    	}
    }
}