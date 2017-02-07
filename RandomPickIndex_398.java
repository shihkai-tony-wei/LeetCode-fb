/* 398. Random Pick Index

Given an array of integers with possible duplicates, 
randomly output the index of a given target number. 
You can assume that the given target number must exist in the array.

Idea: Reservoir sampling.
Iterate (++i) through the array only once -- O(n) time
- if we find the target number, increment the count (i) and 
	-keep this index with probability 1/i,
	-keep the old index with probability 1 - 1/i.
*/

public class RandomPickIndex_398 {

	private Random rnd;
	private int[] nums;

    public RandomPickIndex_398(int[] nums) {
        rnd = new Random();
        this.nums = nums;
    }
    
    public int pick(int target) {
    	int count = 0;
        int index = -1;
        for (int i = 0; i < nums.length; ++i) {
        	if (nums[i] == target) {
        		++count;
	        	if (rnd.nextInt(count) == 0) {	
	        		// this generates a number in [0, count), with count elements
	        		// prob(random number == 0) = 1/count.
	        		index = i;
	        	}
        	}
        }
        return index;
    }
}