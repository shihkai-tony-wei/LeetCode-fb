/* 
Find the timestamp that appears most in given intervals [start, end).

e.g.
[1, 3), [2, 8), [3, 4), [5, 7)

Corner cases:
1. when iterating through all possible integers, we may get duplicate timestamps.
	-- every time a timestamp does not give maxCount, simply move i to the next timestamp in events.
*/

import java.util.*;

class Interval {
	int start, end;
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class TimestampOfIntervals {

	private static class Event implements Comparable<Event> {
		int timestamp;
		int change;
		Event(int ts, int ch) {
			timestamp = ts;
			change = ch;
		}

		public int compareTo(Event that) {
			if (this.timestamp != that.timestamp) {
				return this.timestamp - that.timestamp;
			} else {
				return this.change - that.change;	// end is prior to start for the same timestamp
			}
		}
	}

	public static List<Integer> mostFrequentTimestamp(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) return new ArrayList<Integer>();
		// ***********************************************
		// 1. create a list of Events and sort
		Event[] events = new Event[2 * intervals.length];
		int k = 0;
		for (Interval v : intervals) {
			events[k++] = new Event(v.start, 1);
			events[k++] = new Event(v.end, -1);
		}
		Arrays.sort(events);
		// ***********************************************
		// 2. iterate through all possible timestamps (no need to consider the max timestamp overall),
		// and find the maximum count while updating the answer
		List<Integer> ans = new ArrayList<>();
		int count = 0, maxCount = 0;
		int i = events[0].timestamp, ptr = 0;
		
		while(i < events[events.length - 1].timestamp) {
			if (i == events[ptr].timestamp) {		// if this timestamp is in the events list
				count += events[ptr++].change;		// update count and move ptr
				if (count > maxCount) {
					maxCount = count;
					ans.clear();
					ans.add(i++);
				} else if (count == maxCount) {
					ans.add(i++);
				} else {	// this timestamp and "all the timestamps before the next in events list" are not possible
					i = events[ptr].timestamp;
				}				
			} else {	// this timestamp is in between of two other timestamps in the events list
				// no need to check if maxCount is satisfied, because disqualified points are skipped already
				ans.add(i++);
			}
		}
		return ans;
	}


	/*
	Follow-up: if start means a new employee comes in and end means an employee leaves
	print the number of employees in each separate time interval.
	*/
	public static void numberOfTimestamps(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) return;
		Event[] events = new Event[2 * intervals.length];
		int k = 0;
		for (Interval v : intervals) {
			events[k++] = new Event(v.start, 1);
			events[k++] = new Event(v.end, -1);
		}
		Arrays.sort(events);

		int count = 0;
		for (int i = 0; i < events.length - 1; ++i) {
			count += events[i].change;
			if (events[i].timestamp == events[i + 1].timestamp) {				
				continue;	// don't print the Interval with same timestamps
			}
			System.out.println(events[i].timestamp + "-" + events[i + 1].timestamp + ": " + count);
		}
	}

	public static void main(String[] args) {
		Interval a = new Interval(2005, 2016);
		Interval b = new Interval(2008, 2014);
		Interval c = new Interval(2006, 2008);
		Interval d = new Interval(2010, 2014);
		Interval e = new Interval(1,1);

		Interval[] intervals = new Interval[]{a, b, c, d};
		List<Integer> ans = TimestampOfIntervals.mostFrequentTimestamp(intervals);
		System.out.println(ans); 
		TimestampOfIntervals.numberOfTimestamps(intervals);
	}
}