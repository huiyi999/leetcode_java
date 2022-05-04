import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : chy
 * @date: 2022-04-28 1:47 p.m.
 */
public class NumberOfPairs {

    /**
     * Given an integer array, return the number of pairs of indices such that its array values sum to a power of 2. For reference, powers of 2 are: 2^0 = 1, 2^1 = 2, 2^2 = 4, 2^3 = 8, 2^4 = 16, etc...
     * <p>
     * Example:
     * a = [1, -1, 2, 3]
     * Output: 5
     * (0,0) = 1 + 1 = 2 (which is a power of 2)
     * (1,2) = -1 + 2 = 1 (which is a power of 2)
     * (1,3) = -1 + 3 = 2 (which is a power of 2)
     * (0,3) = 1 + 3 = 4 (which is a power of 2)
     * (2,2) = 2 + 2 = 4 (which is a power of 2)
     * Therefore, there are 5 pairs of indices whose array values sum is a power of 2 so the output is 5.
     * As implied by the above example, indices can be re-used.
     */


    // time O(31*n)
    static int countPair(int[] arr, int n) {
// Stores the frequency of each element of the array
        Map<Integer, Integer> m = new HashMap<>();

        // Update the frequency of array elements
        for (int i = 0; i < n; i++)
            m.put(arr[i], m.getOrDefault(arr[i], 0) + 1);

        // Stores the count of pairs
        int ans = 0;

        // Generate powers of 2
        for (int i = 0; i < 31; i++) {

            // Generate current power of 2
            int key = (int) Math.pow(2, i);

            // Traverse the array
            for (int j = 0; j < arr.length; j++) {

                int k = key - arr[j];

                // Increase ans by m[k], if pairs with sum 2^i exists
                ans += m.getOrDefault(k, 0);

                // Increase ans again if k = arr[j]
                if (k == arr[j])
                    ans++;
            }
        }

        // Return count of pairs
        return ans / 2;
    }

    public static void main(String[] args) {
        int[] arr = {1, -1, 2, 3};
        int n = arr.length;
        System.out.println(countPair(arr, n));
    }
}
