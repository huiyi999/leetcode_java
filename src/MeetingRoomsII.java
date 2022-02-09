import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    /**
     * 253. Meeting Rooms II
     *
     * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
     */
    public int minMeetingRooms(int[][] intervals) {

        // average time complexity is O(nlogn).
        if(intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);        // java8

        // Sort the array by first column
        // Arrays.sort(intervals, new Comparator<Interval>() {
        //     public int compare(Interval a, Interval b) { return a.start - b.start; }
        // });


        // sort by end time
        PriorityQueue<int[]> pq = new PriorityQueue<>(intervals.length,(a, b) -> a[1]-b[1]);

        int ans=0;
        for(int i=0;i<intervals.length;i++){
            while(!pq.isEmpty() && intervals[i][0] >= pq.peek()[1]){
                pq.poll();
            }
            pq.offer(intervals[i]);
            ans = Math.max(ans,pq.size());
        }
        return ans;

        // method 2
        // Intuition: Track the change of room numbers in time order.
        // Explanation:
        // Save all time points and the change on current meeting rooms.
        // Sort all the changes on the key of time points.
        // Track the current number of using rooms cur and update result res.

        // Time Complexity:
        // Time O(NlogN), Space O(N)
//         Map<Integer, Integer> m = new TreeMap<>();
//         for (int[] t : intervals) {
//             m.put(t[0], m.getOrDefault(t[0], 0) + 1);  // treemap store key in natural order
//             m.put(t[1], m.getOrDefault(t[1], 0) - 1);
//         }

//         for (int i : m.keySet())
//             System.out.println(i + " "+ m.get(i));
//         int res = 0, cur = 0;
//         for (int v : m.values()) {
//             res = Math.max(res, cur += v);
//         }
//         return res;

        //  method 3.
        // if starts[i] < ends[endItr], we cannot use a room we used before, otherwise we can reuse a room.

        // int[] starts = new int[intervals.length];
        // int[] ends = new int[intervals.length];
        // for(int i=0; i<intervals.length; i++) {
        //     starts[i] = intervals[i].start;
        //     ends[i] = intervals[i].end;
        // }
        // Arrays.sort(starts);
        // Arrays.sort(ends);
        // int rooms = 0;
        // int endsItr = 0;
        // for(int i=0; i<starts.length; i++) {
        //     if(starts[i]<ends[endsItr])
        //         rooms++;
        //     else
        //         endsItr++;
        // }
        // return rooms;
    }
}
