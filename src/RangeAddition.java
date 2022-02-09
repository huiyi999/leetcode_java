/**
 * 370. Range Addition
 *
 * You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
 *
 * You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
 *
 * Return arr after applying all the updates.
 */

public class RangeAddition {

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];

        for (int i = 0; i < updates.length; i++) {
            int start = updates[i][0];
            int end = updates[i][1];
            int inc = updates[i][2];
            ans[start] += inc;

            if (end < length - 1) {
                ans[end + 1] -= inc;
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += ans[i];
            ans[i] = sum;
        }
        return ans;
    }
}
