/**
 * LC 56
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class MergeIntervals_56 {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return new ArrayList<Interval>();

        List<Interval> ans = new ArrayList<>();
        intervals.sort((a, b) -> a.start - b.start);
        int beg = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
        	Interval curr = intervals.get(i);
        	if (curr.start <= end) {	// overlap
        		end = Math.max(end, curr.end);
        	} else {
        		ans.add(new Interval(beg, end));
        		beg = curr.start;
        		end = curr.end;
        	}
        }
        ans.add(new Interval(beg, end));
        return ans;
    }
}

// complexity: O(nlogn) time + O(1) space (not including the return) 