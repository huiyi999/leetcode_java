import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 */


// TC - O(n)
public class InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> list = new ArrayList();
//        System.out.println(intervals.length);

        for (int i = 0; i < intervals.length; i++) {
            // case 1: current interval fully left of newIntervals, add current interval in result lis
            if (intervals[i][1] < newInterval[0]) {
                list.add(intervals[i]);
            }

            // case 2: newIntervals fully left of current interval,
            // add newInterval in result list and update newInterval with current interval.
            else if (newInterval[1] < intervals[i][0]) {
                list.add(newInterval);
                newInterval = intervals[i];
            }

            // case 3: current interval and newInterval overlaps, update the newInterval start and end with
            else {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        //After iterating all intervals add the newInterval in result list
        list.add(newInterval);

        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};

        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};

        int[][] result1 = insert(intervals1, newInterval1);
        int[][] result2 = insert(intervals2, newInterval2);
        for (int[] in : result1)
            for (int x : in)
                System.out.println(x);   //Output: [[1,5],[6,9]]

        for (int[] in : result2)
            for (int x : in)
                System.out.println(x);   //Output: [[1,2],[3,10],[12,16]]


    }
}
