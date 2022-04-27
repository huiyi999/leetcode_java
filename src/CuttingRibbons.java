/**
 * 1891. Cutting Ribbons
 * <p>
 * You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k.
 * You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
 * <p>
 * For example, if you have a ribbon of length 4, you can:
 * Keep the ribbon of length 4,
 * Cut it into one ribbon of length 3 and one ribbon of length 1,
 * Cut it into two ribbons of length 2,
 * Cut it into one ribbon of length 2 and two ribbons of length 1, or
 * Cut it into four ribbons of length 1.
 * Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.
 * <p>
 * Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.
 */
public class CuttingRibbons {


    public int maxLength(int[] ribbons, int k) {
        int lo = 1, hi = 1;
        for (int n : ribbons) {
            hi = Math.max(hi, n);
        }

        // binary search
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (helper(ribbons, mid) < k) {
                hi = mid - 1;
            } else
                lo = mid + 1;
        }

        // upper bound
        return hi;
        //return hi > 0 ? hi : 0;

        // 410. Split Array Largest Sum  lower bound
    }

    int helper(int[] ribbons, int target) {
        int ret = 0;
        for (int n : ribbons) {
            ret += n / target;
        }
        return ret;
    }
    // The final running time is O(N log (max length)) with O(1) auxilary space.
}