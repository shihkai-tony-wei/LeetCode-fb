/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class MeetingRoomI_252 {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length <= 1) return true;
        Arrays.sort(intervals, (a, b)->a.start - b.start);
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start < intervals[i - 1].end)
                return false;
        }
        return true;
    }
}