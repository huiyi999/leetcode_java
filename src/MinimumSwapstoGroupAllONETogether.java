/**
 * 1151. Minimum Swaps to Group All 1's Together
 * <p>
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 */

public class MinimumSwapstoGroupAllONETogether {
    public int minSwaps(int[] data) {
        int cnt = 0;
        for (int d : data) {    // O(N),       binary search: O(logN)
            cnt += d;
        }

        int n = data.length, ans = data.length;
        int left = 0, zeros = 0;
        for (int right = 0; right < n; ++right) {

            if (data[right] == 0) zeros++;

            if (right - left + 1 > cnt) {
                if (data[left] == 0) zeros--;
                left++;
            }
            if (right - left + 1 == cnt)
                ans = Math.min(ans, zeros);

        }
        // int curOne = 0, maxOne = 0;
        // for (int i = 0; i < n; i++) {
        //     curOne += data[i];
        //     if (i >= windowSize) {
        //         // move the left pointer
        //         curOne -= data[i - windowSize];
        //     }
        //     maxOne = Math.max(maxOne, curOne);
        // }
        // return windowSize - maxOne;
        return ans;
    }

}
