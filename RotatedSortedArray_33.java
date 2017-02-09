/*33. Search in Rotated Sorted Array

Binary search
using normal [0 ... n - 1] as indices for binary search
and MAP to the rotated array 
*/
public class RotatedSortedArray_33 {
    public int search(int[] nums, int target) {
        // 1. find minimum via binary search
        int n = nums.length;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (nums[mid] < nums[hi]) hi = mid;
        	else	lo = mid + 1;
        }

        int idx = lo;
        // now idx is minimum, binary search again
              
        lo = 0;
        hi = n - 1;
        while (lo <= hi) {
        	int mid = lo + (hi - lo) / 2;
        	int rotatedIdx = (mid + idx) % n;
        	if (target == nums[rotatedIdx]) return rotatedIdx;
        	else if (target < nums[rotatedIdx]) hi = mid - 1;
        	else lo = mid + 1;
        }
        return -1;
    }
}