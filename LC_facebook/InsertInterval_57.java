/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class InsertInterval_57 {
	// intervals initially sorted by start times
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	// no need to check for empty input
    	List<Interval> ans = new ArrayList<>();  
        // step 1: add all intervals whose end < newInterval.start
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
        	ans.add(intervals.get(i++));
        }
        // step 2: merge intervals that overlap (including less than or EQUAL)
        int beg = newInterval.start, end = newInterval.end;
        while (i < intervals.size() && intervals.get(i).start <= end) {
        	beg = Math.min(beg, intervals.get(i).start);
        	end = Math.max(end, intervals.get(i).end);
        	i++;
        }
        ans.add(new Interval(beg, end));
        // step 3: add all remaining intervals
        while (i < intervals.size()) {
        	ans.add(intervals.get(i++));
        }
        return ans;
    }
}

// time: O(n)
// space: O(1) not including return