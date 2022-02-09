import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 * <p>
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * <p>
 * After doing so, return the number of remaining intervals.
 */
public class RemoveCoveredIntervals {


    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                if (x[0] < y[0]) {
                    return -1;
                } else if (x[0] > y[0]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

//        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));


//        Arrays.sort(intervals);
        List<int[]> list = new ArrayList();
        for (int i = 0; i < intervals.length; i++) {
            list.add(intervals[i]);
        }


        for (int i = 0; i < intervals.length; i++) {
            for (int j = 1; j < intervals.length; j++) {

                if (i != j && intervals[i][0] <= intervals[j][0] && intervals[j][1] <= intervals[i][1]) {
                    list.remove(intervals[j]);
//                    System.out.println("remove");
                }else if (i != j && intervals[i][0] == intervals[j][0] && intervals[i][1] < intervals[j][1]) {
                    list.remove(intervals[i]);
//                    System.out.println("remove");
                }

            }
        }

        return list.size();
    }
    public static int removeCoveredIntervals1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        int len =intervals.length;
        for (int i = 0; i < intervals.length-1; i++) {
//            if(intervals[i]==null)
//                continue;
            for (int j = i+1; j < intervals.length; j++) {
                if(intervals[i]==null)
                    continue;
                if(intervals[j]==null)
                    continue;


                if (intervals[i][0] <= intervals[j][0] && intervals[j][1] <= intervals[i][1]) {
                    // list.remove(intervals[j]);
                    intervals[j]=null;
                    len--;

                }else if (intervals[i][0] == intervals[j][0] && intervals[i][1] < intervals[j][1]) {
                    // list.remove(intervals[i]);
                    intervals[i]=null;
                    len--;

                }

            }
        }

        return len;
    }

    public static int removeCoveredIntervals2(int[][] intervals) {
        int len = intervals.length;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i] == null)
                continue;
            int a = intervals[i][0], b = intervals[i][1];
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j] == null)
                    continue;
                int c = intervals[j][0], d = intervals[j][1];
                if (c <= a && b <= d) {
                    //  被包围
                    len--;
                    break;
                } else if (c >= a && b >= d) {
                    //  包围其他
                    intervals[j] = null;
                    len--;
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 4}, {3, 6}, {2, 8}};
        int[][] intervals2 = {{1, 4}, {2, 3}};
        int[][] intervals3 = {{0, 10}, {5, 12}};
        int[][] intervals4 = {{3, 10}, {4, 10}, {5, 11}};
        int[][] intervals5 = {{1, 2}, {1, 4}, {3, 4}};
        int[][] intervals6 = {{1, 2}};

        System.out.println(removeCoveredIntervals(intervals1));  // Output: 2
//        Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
        System.out.println(removeCoveredIntervals(intervals2));  // Output: 1
        System.out.println(removeCoveredIntervals(intervals3));  // Output: 2
        System.out.println(removeCoveredIntervals(intervals4));  // Output: 2
        System.out.println(removeCoveredIntervals(intervals5));  // Output: 1
        System.out.println(removeCoveredIntervals(intervals6));  // Output: 1

        System.out.println("222222");
        System.out.println(removeCoveredIntervals1(intervals1));  // Output: 2
//        Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
        System.out.println(removeCoveredIntervals1(intervals2));  // Output: 1
        System.out.println(removeCoveredIntervals1(intervals3));  // Output: 2
        System.out.println(removeCoveredIntervals1(intervals4));  // Output: 2
        System.out.println(removeCoveredIntervals1(intervals5));  // Output: 1
        System.out.println(removeCoveredIntervals1(intervals6));  // Output: 1

        System.out.println("333333");
        System.out.println(removeCoveredIntervals2(intervals1));  // Output: 2
//        Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
        System.out.println(removeCoveredIntervals2(intervals2));  // Output: 1
        System.out.println(removeCoveredIntervals2(intervals3));  // Output: 2
        System.out.println(removeCoveredIntervals2(intervals4));  // Output: 2
        System.out.println(removeCoveredIntervals2(intervals5));  // Output: 1
        System.out.println(removeCoveredIntervals2(intervals6));  // Output: 1
    }
}
