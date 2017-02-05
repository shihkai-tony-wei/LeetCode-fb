/*
360. Sort Transformed Array
Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Idea: same as sort squares.
Starting at two ends -- only consider if a is positive or negative,
because that determines the order to fill in the new array.
In case a == 0, b doesn't matter, because it is monotonic now.

time: O(n) + space O(n)
*/

public class SortTransformedArray_360 {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    	int n = nums.length;
        int[] aux = new int[n];
        int k = a > 0 ? n - 1 : 0;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {	// when they are equal, also needs to process
        	if (a > 0) {
        		aux[k--] = f(a, b, c, nums[lo]) <= f(a, b, c, nums[hi]) ? f(a, b, c, nums[hi--]) : f(a, b, c, nums[lo++]);
        	} else {
        		aux[k++] = f(a, b, c, nums[lo]) <= f(a, b, c, nums[hi]) ? f(a, b, c, nums[lo++]) : f(a, b, c, nums[hi--]);
        	}
        }
        return aux;
    }

    private int f(int a, int b, int c, int x) {
    	return a * x * x + b * x + c;
    }
}