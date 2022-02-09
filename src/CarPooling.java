/**
 * You are driving a vehicle that has capacity empty seats initially available for passengers.
 * The vehicle only drives east (ie. it cannot turn around and drive west.)
 * <p>
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location]
 * contains information about the i-th trip: the number of passengers that must be picked up,
 * and the locations to pick them up and drop them off.
 * The locations are given as the number of kilometers due east from your vehicle's initial location.
 * <p>
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Constraints:
 * <p>
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 * <p>
 * Hide Hint #1
 * Sort the pickup and drop-off events by location, then process them in order.
 */

public class CarPooling {
    public static boolean carPooling(int[][] trips, int capacity) {

//        System.out.println("排序前:");
//        for (int i = 0; i < trips.length; i++) {
//            System.out.println(Arrays.toString(trips[i]));
//        }

        Arrays.sort(trips, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                int[] one = (int[]) o1;
                int[] two = (int[]) o2;

                if (one[1] > two[1]) {
                    return 1;
                } else if (one[1] < two[1]) {
                    return -1;
                } else
                    return 0;
            }
        });


        int[] location = new int[1001];
        int end_location = 0;
        for (int i = 0; i < trips.length; i++) {
            location[trips[i][1]] += trips[i][0];
            location[trips[i][2]] -= trips[i][0];
            end_location = Math.max(end_location, Math.max(trips[i][1], trips[i][2]));
        }
        int tmp = 0;
        for(int i=0;i<=end_location;i++){
            tmp+=location[i];
            if(tmp>capacity)
                return false;
        }
        return true;

    }


    public static void main(String[] args) {
        int[][] trips1 = {{2, 1, 5}, {3, 3, 7}};
        int capacity1 = 4;

        int[][] trips2 = {{2, 1, 5}, {3, 3, 7}};
        int capacity2 = 5;

        int[][] trips3 = {{2, 1, 5}, {3, 5, 7}};
        int capacity3 = 3;

        int[][] trips4 = {{3, 2, 7}, {3, 7, 9}, {8, 3, 9}};
        int capacity4 = 11;


        System.out.println(carPooling(trips1, capacity1));    //Output: false
        System.out.println(carPooling(trips2, capacity2));    //Output: true
        System.out.println(carPooling(trips3, capacity3));    //Output: true
        System.out.println(carPooling(trips4, capacity4));    //Output: true


    }
}
