/* 39. Combination Sum
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
*/


/* Backtracking: O(2^n) time
	for each possible move:
		do current move;
		recursive calls;
		undo current move
*/
public class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return ans;
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, ans, new ArrayList<Integer>());
        return ans;
    }

    private void backtrack(int[] nums, int remaining, int pos, List<List<Integer>> ans, List<Integer> list) {
    	if (remaining < 0)		// if more than target, return and we'll undo this move
    		return;
    	else if (remaining == 0)	// if same as target, add to ans
    		ans.add(new ArrayList<>(list));		// -----> Note: must create a new ArrayList, otherwise it will change later	
    	else {	// if not target yet, add the same number as many as possible
    		for (int i = pos; i < nums.length; ++i) {
    			list.add(nums[i]);
    			backtrack(nums, remaining - nums[i], i, ans, list);		// note: the next starting point is still i
    			list.remove(list.size() - 1);
    		}
    	}
    }
}

