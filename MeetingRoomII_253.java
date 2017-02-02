/**
 * LC 253
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// METHOD 1: sweep-line 1
public class MeetingRoomII_253 {
	private class Event implements Comparable<Event> {
		int timestamp;
		int change;
		Event(int t, int c) {
			timestamp = t;
			change = c;
		}

		public int compareTo(Event that) {
			if (this.timestamp != that.timestamp)
				return this.timestamp - that.timestamp;
			else
				return this.change - that.change;
		}
	}

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Event[] eventList = new Event[2 * intervals.length];
        int k = 0;
        for (Interval v : intervals) {
        	eventList[k++] = new Event(v.start, 1);
        	eventList[k++] = new Event(v.end, -1);
        }
        Arrays.sort(eventList);

        int rooms = 0, curr = 0;
        for (Event ev : eventList) {
        	curr += ev.change;
        	rooms = Math.max(rooms, curr);
        }

        return rooms;
    }
}



// METHOD 2: sweep-line simplified -- two pointers
public class MeetingRoomII_253 {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
        	starts[i] = intervals[i].start;
        	ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int j = 0;
        // Two pointers: i++, j points to the ending event
        for (int i = 0; i < intervals.length; i++) {
        	if (starts[i] < ends[j])	// if start before the previous meeting ends, add a room
        		rooms++;
        	else		// otherwise move the pointer j
        		j++;
        }
        return rooms;
    }
}

// METHOD 3: priority queue
/* (1) sort intervals by start time and offer into minpq the first one
 * (2) minpq uses the end time as comparator
 *     if the current peek().end is <= than next's start, poll() from heap -- because no conflict!
 *	   no matter what...add current interval to heap
 * (3) once we have processed ALL intervals, the heap size is the number of rooms we need.
 */

public class MeetingRoomII_253 {
    public int minMeetingRooms(Interval[] intervals) {
    	if (intervals == null || intervals.length == 0) return 0;
    	Arrays.sort(intervals, (a, b) -> a.start - b.start);
    	PriorityQueue<Interval> minpq = new PriorityQueue<Interval>((a, b) -> a.end - b.end);
    	minpq.offer(intervals[0]);
    	for (int i = 1; i < intervals.length; i++) {
    		if (intervals[i].start >= minpq.peek().end)
    			minpq.poll();
    		minpq.offer(intervals[i]);
    	}
    	return minpq.size();
    }
}