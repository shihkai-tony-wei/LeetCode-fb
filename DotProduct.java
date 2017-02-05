/*
Dot product between two vectors.
Vectors could be larger than memory.
*/

public class DotProduct {
	// time O(n), space O(1)
	public int dot(int[] a, int[] b) {
		int sum = 0;
		for (int i = 0; i < a.length; ++i) {
			sum += a[i] * b[i];
		}
		return sum;
	}

	// Follow up 1: vectors don't fit into memory
	// Use (key, value) = (index, val)

	private class Pair {
		int index;
		int val;
		Pair(int index, int val) {
			this.index = index;
			this.val = val;
		}
	}

	public int dot2(int[] a, int[] b) {
		List<Pair> listA = new ArrayList<>();
		List<Pair> listB = new ArrayList<>();
		for (int i = 0; i < a.length; ++i) {
			if (a[i] != 0) 
				listA.add(new Pair(i, a[i]));
		}
		for (int i = 0; i < b.length; ++i) {
			if (b[i] != 0)
				listB.add(new Pair(i, b[i]));
		}

		int sum = 0;
		for (Pair pa : listA) {
			for (Pair pb : listB) {
				if (pb.index > pa.index) break;
				if (pb.index == pa.index) sum += pa.val * pb.val;
			}
		}
	}

	// Follow up 2: one list has way more zeros than the other -- binary search on the larger one
	// time O(n*logm)
	// Assume listA is shorter than listB
	public int dot3(int[] a, int[] b) {
		List<Pair> listA = new ArrayList<>();
		List<Pair> listB = new ArrayList<>();
		for (int i = 0; i < a.length; ++i) {
			if (a[i] != 0) 
				listA.add(new Pair(i, a[i]));
		}
		for (int i = 0; i < b.length; ++i) {
			if (b[i] != 0)
				listB.add(new Pair(i, b[i]));
		}

		int sum = 0, start = 0;
		for (Pair pa : listA) {
			int lo = start, hi = listA.size() - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (listB.get(mid).index < pa.index) lo = mid + 1;
				else if (listB.get(mid).index > pa.index) hi = mid - 1;
				else {
					sum += pa.val * listB.get(mid).val;
					start = mid;
				}
			}
		}
		return sum;
	}
}