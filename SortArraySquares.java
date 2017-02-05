/*
Sort the squares of a sorted array. 
Similar to LC 360. Sort Transformed Array
e.g. a = [-8, -5, 1, 2, 6, 8, 10]

Idea: starting from lo and hi, compare their absolute value until pointers cross.
time: O(n) + space O(n)
*/
import java.util.*;
public class SortArraySquares {
	public static int[] sortSquares(int[] a) {
		int[] aux = new int[a.length];
		int lo = 0, hi = a.length - 1;
		int k = aux.length - 1;
		while (lo <= hi) {
			if (Math.abs(a[lo]) < Math.abs(a[hi])) {
				aux[k--] = a[hi] * a[hi];
				--hi;
			} else {
				aux[k--] = a[lo] * a[lo];
				++lo;
			}
		}
		return aux;
	}

	public static void main(String[] args) {
		int[] a = {-8, -5, 1, 2, 6, 8, 10};
		int[] aux = SortArraySquares.sortSquares(a);
		System.out.println(Arrays.toString(aux));
	}
}