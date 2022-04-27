import java.util.Arrays;

/**
 * 252. Meeting Rooms
 * <p>
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null)
            return false;

        // Sort the intervals by start time

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0])
                return false;
        }
        return true;
    }
    // Time complexity : O(nlogn).
    // Space complexity : O(1)
}
