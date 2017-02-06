/* 31. Next Permutation
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/*
Algorithm: O(n) time + O(1) space
(1) Find the largest k, s.t. a[k] < a[k + 1] -- the peak in array
	- We can't make the permutation smaller by swapping with anything on the right of k.
	- If no such k, we know a is reversely sorted. Return its reverse.
(2) Find the largest j, s.t. a[k] < a[j] -- a[k]'s successor
	- After step 1, we know a[k + 1] > a[k + 2] > ... > a[n - 1].
(3) Swap a[k] and a[j]
(4) Reverse from k to n - 1
	- Change the decreasing sequence to increasing.
*/

public class NextPermutation_31 {
	public void nextPermutation(int a[]) {
		// step 1: starting from end, find the peak position k
		int k = a.length - 2;
		while (k >= 0 && a[k + 1] <= a[k]) {		// also handles duplicate values
			--k;
		}
		if (k < 0) {
			reverse(a, 0, a.length - 1);
			return;
		}

		// step 2: starting from end, find the largest a[j] that is larger than a[k]
		int j = a.length - 1;
		while (j > k && a[j] <= a[k]) {
			j--;
		}

		// step 3, 4
		exch(a, k, j);
		reverse(a, k + 1, a.length - 1);
	}

	private void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private void reverse(int[] a, int lo, int hi) {
		while (lo < hi) {
			exch(a, lo++, hi--);
		}
	}
}