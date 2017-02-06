/*
283. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Idea: keep a pointer pointing to the "first 0",
whenever we see a non-zero number swap with that "first 0" and increment the pointer
*/

// Method 1: based on swapping
public class MoveZeroes_283 {
    public void moveZeroes(int[] nums) {
    	// 1. find the index of the first zero
    	int indexFirstZero = 0;
    	while (indexFirstZero < nums.length && nums[indexFirstZero] != 0) {
    		++indexFirstZero;
    	}

    	// 2. swap nonzero elements with the first zero, and increment first zero pointer by 1
    	// That next element after swap must be 0 - no matter if it is 0 originally.
    	for (int i = indexFirstZero + 1; i < nums.length; ++i) {
    		if (nums[i] != 0) {
    			exch(nums, i, indexFirstZero++);
    		}
    	}
    }

    private void exch(int[] a, int i, int j) {
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
}

// Method 2: just fill in the left with non-zeros and remaining with zeroes
public class MoveZeroes_283 {
    public void moveZeroes(int[] nums) {
    	int insertPtr = 0;		// denotes the position to overwrite
    	for (int i = 0; i < nums.length; ++i) {
    		if (nums[i] != 0) {
    			nums[insertPtr++] = nums[i];
    		}
    	}
    	// fill remaining by zeroes
    	while (insertPtr < nums.length) {
    		nums[insertPtr++] = 0;
    	}
    }
}