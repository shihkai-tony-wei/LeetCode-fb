/*
LC 75. Sort Colors
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Idea: Three-way partition, using 1 as pivot value.
while the pointer i doesn't exceed hi, do the partition into three parts:
< v ||  == v || > v
      lo       hi

lo always points to the start of 1's block
hi always points to the left of 2's block
*/

public class SortColors_75 {
	// Three-way partition: O(n)
    public void sortColors(int[] nums) {
        int i = 0, lo = 0, hi = nums.length - 1;
        while (i <= hi) {	// need to consider when pointers cross
        	if (nums[i] == 0) 		exch(nums, lo++, i++);
        	else if (nums[i] == 2)  exch(nums, hi--, i);		// i might still need change position
        	else 					i++;
        }
    }

    private void exch(int[] a, int i, int j) {
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
}




// follow up K categories: 0, 1, 2, ..., k-2, k-1
// 0 = L, {2...k-2} = M, k-1 = H
public void sortKCategory(int[] nums, int k) {
       //assume getCat returns 1, ...k
        int pl = 0;
        int pr = nums.length - 1;
        int i = 0;
        int min = 1, max = k;
        while (min < max) {
            while (i <= pr) {
                if (getCat(nums[i]) == min) {
                    swap(nums, pl, i);
                    i++;
                    pl++;
                } else if (getCat(nums[i]) == max) {-google 1point3acres
                    swap(nums, pr, i);
                    pr--;
                } else {
                    i++;
                }
            }
            i = pl;
            min++;
            max--;
        }
    }

    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }