/**
 * 1231. Divide Chocolate
 * <p>
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * <p>
 * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.
 * <p>
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * <p>
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 */

public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int k) {
        int lo = 100000, hi = 0, mid;
        for (int sweet : sweetness) {
            lo = Math.min(lo, sweet);
            hi += sweet;
        }
        // while (lo < hi) {
        //     mid = (hi + lo +1) / 2;
        //     int cur = 0, cuts = 0;
        //     for (int sweet : sweetness) {
        //         cur += sweet;
        //         if (cur >= mid) {
        //             cur = 0;
        //             if (++cuts > k) break;
        //         }
        //     }
        //
        //     if (cuts > k)
        //         lo = mid ;
        //
        //         // Test if we can cut into `k + 1`  or more parts
        //     else
        //         hi = mid - 1;
        // }
        // return lo;


        k++;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int cur = 0, cuts = 0;
            for (int sweet : sweetness) {
                cur += sweet;
                if (cur >= mid) {
                    cur = 0;
                    if (++cuts > k) break;
                }
            }

            if (cuts < k)
                hi = mid - 1;
                // Test if we can cut into `k + 1`  or more parts
            else
                lo = mid + 1;
        }
// lo = mid+1;
// `lo` increases by 1 after every correct sweet level, so the final correct answer must be `l - 1`
        //return lo - 1;
        return hi;   // rightmost  maximum total sweetnes


    }
    // Time: O(nlonm) where n - is amount of chunks; m - sum of chunks
    // Space: O(1)
}
