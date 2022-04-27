/**
 * 774. Minimize Max Distance to Gas Station
 * <p>
 * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.
 * <p>
 * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.
 * <p>
 * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
 * <p>
 * Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.
 */
public class MinimizeMaxDistancetoGasStation {
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length, count;
        double left = 0, right = stations[n - 1] - stations[0], mid;


        // When left + 1e-6 >= right, it means the answer within 10^-6 of the true value and it will be accepted.
        while (left + 1e-6 < right) {
            mid = left + (right - left) / 2;
            count = 0;

            for (int i = 0; i < n - 1; ++i) {
                count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
            }
            if (count > k) left = mid;  // it means mid is too small to realize using only K more stations.
            else right = mid;
        }
        return right;
    }
//   Time complexity:
// O(NlogM), where N is station length and M is st[N - 1] - st[0]


    // consider two stations, distance= b-a, target distance=D
    // b-a <= D  don't need to add new stations
    // b-a > D  generally: add N new stations,
    // average distance = (b-a)(N+1) <=D
    // (b-a) / D -1 <= N  是至少要增加的数量
    // (b-a) / D 可能是1.3， 所以取ceil
}
