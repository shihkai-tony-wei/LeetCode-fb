/*
LC 278. First Bad Version
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

The isBadVersion API is defined in the parent class VersionControl.
    boolean isBadVersion(int version); 

Binary search: O(log n) time
*/


public class FirstBadVersion_278 extends VersionControl {
	public int firstBadVersion(int n) {
		int lo = 0, hi = n;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (isBadVersion(mid))
				hi = mid;
			else
				lo = mid + 1;
		}
		return lo;
	}
}