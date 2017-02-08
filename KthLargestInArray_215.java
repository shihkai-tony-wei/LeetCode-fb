/*215. Kth Largest Element in an Array

O(n) average: quick-select with SHUFFLING
O(n*logk): heap
*/

public class KthLargestInArray_215 {
    public int findKthLargest(int[] nums, int k) {
        // quick select
        k = nums.length - k;
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
        	int j = partition(nums, lo, hi);
        	if (j < k) lo = j + 1;
        	else if (j > k) hi = j - 1;
        	else return nums[k];
        }
        return nums[lo];
    }

    private int partition(int[] a, int lo, int hi) {
    	int i = lo, j = hi + 1;
    	int pivot = a[lo];
    	while (true) {
    		while (a[++i] < pivot) {
    			if (i == hi) break;
    		}
    		while (a[--j] > pivot) {
    			if (j == lo) break;
    		}
    		if (i >= j) break;
    		swap(a, i, j);
    	}
    	swap(a, lo, j);
    	return j;
    }

    private void swap(int[] a, int i, int j) {
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }

    private void shuffle(int[] a) {
    	Random rnd = new Random();
    	for (int i = 0; i < a.length; ++i) {
    		int r = rnd.nextInt(i + 1);
    		if (r != i) swap(a, r, i);
    	}
    }
}