/*
cycle sort: minimize the number of memory write --
each element is written AT MOST once!
Time: O(n^2) + Space: O(1) in place
*/

import java.util.*;
public class CycleSort {
	public static int sort(int[] a) {
		int writes = 0;		// record number of total memory writes

		for (int cycle_start = 0; cycle_start < a.length - 1; ++cycle_start) {
			int curr = a[cycle_start];

			// count the number of values smaller than current value
			int pos = cycle_start;
			for (int i = cycle_start + 1; i < a.length; ++i) {
				if (a[i] < curr)
					++pos;
			}

			// no smaller elements
			if (pos == cycle_start) continue;

			// skip duplicates
			while (curr == a[pos]) ++pos;

			// put current into FINAL position
			int temp = a[pos];
			a[pos] = curr;
			curr = temp;
			++writes;

			// repeat the process until cycle gets back to starting point
			while (pos != cycle_start) {
				pos = cycle_start;
				for (int i = cycle_start + 1; i < a.length; ++i) {
					if (a[i] < curr)
						++pos;
				}

				while (curr == a[pos]) ++pos;

				temp = a[pos];
				a[pos] = curr;
				curr = temp;
				++writes;
			}
		}
		return writes;
	}

	public static void main(String[] args) {
		int[] a = {3,1,9,2,6,8,5,7,2,4,0,9};
		int writes = CycleSort.sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println("Total number of writes = " + writes + ".");
	}
}