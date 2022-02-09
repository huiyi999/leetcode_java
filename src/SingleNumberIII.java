import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an integer array nums, in which exactly two elements appear only once
 * and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 * <p>
 * Follow up: Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant space complexity?
 */

/**
 * XOR Property `
 *
 * A ^ 0 = A
 * A ^ A = 0
 */
public class SingleNumberIII {
    public static int[] singleNumber(int[] nums) {
        /**
         * Solution 1: Count frequency using Hashmap
         * TC - O(n)
         * SC - O(n)
         */
        HashMap<Integer, Integer> map = new HashMap();
        for (int n:nums ) {
            if (map.containsKey(n)) {
                int count = map.get(n) + 1;
                map.put(n, count);
            } else {
                map.put(n, 1);
            }
        }
        int[] res = new int[2];
        int i = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                res[i++] = key;
            }
        }

/**
 * hashset
 */
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n))
                set.remove(n);
            else
                set.add(n);
        }
        int[] output = new int[2];
        int idx = 0;
        for(int n : set)
            output[idx++]=n;

        return res;
    }

    /**
     * Solution 2: Using Bitwise Xor and its properties
     * TC - O(n)
     * SC - O(1)
     *
     * So, now we will form mask using that bit we can do this many ways
     *
     * Simple find the location of that bit then use left shift operation that many time
     * Use idea from the Brian Kerningham Algorithm => n & (n - 1) => all bit right bit
     * including last set (1) bit will become 0 now xor with n we will get mask. mask = xor & (xor - 1) ^ xor
     * mask = xor ^ -xor
     *
     * Now use this mask and do xor of number for which that bit is not set(0) => (mask & n) == 0
     */
    public int[] singleNumber1(int[] nums) {
        int xor = 0;
        for(int n: nums) xor ^= n;

        int mask = (xor & (xor - 1)) ^ xor;

        int num1 = 0;
        for(int n: nums)
            if((mask & n) == 0)
                num1 ^= n;

        return new int[]{num1, xor ^ num1};
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 1, 3, 2, 5};
        int[] nums2 = {-1, 0};
        int[] nums3 = {0, 1};

        int [] res1 = singleNumber(nums1);
        int [] res2 = singleNumber(nums2);
        int [] res3 = singleNumber(nums3);
    }
}
