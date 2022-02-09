/**
 * Your are given an array of positive integers nums.
 * <p>
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Hide Hint #1
 * For each j, let opt(j) be the smallest i so that nums[i] * nums[i+1] * ... * nums[j] is less than k. opt is an increasing function.
 */

public class SubarrayProductLessThanK {

    //采用滑窗法，结合数学
    // Two pointers问题：我们每次增加一个nums[end]，然后从start开始，一旦发现[start, end]区间内的乘积大于k，
    // 就增加start。当[start, end]区间内的乘积小于k的时候，就会有(end - start + 1)个子区间符合条件。
    // 最后返回总的个数即可。
    // 算法的时间复杂度是O(n)，空间复杂度是O(1)。
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1)
            return 0;

        int count = 0;
        int right, left = 0, product = 1;
        for (right = 0; right < nums.length; right++) {

            product *= nums[right];
            while (product >= k) {
                product /= nums[left++];
            }
            count += right - left + 1;   //length of subarray
        }


        return count;

    }

    //直接求解，时间复杂度O（n2）
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        long product = 1;
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            product = 1;
            for (int j = i; j < nums.length; ++j) {
                product *= nums[j];
                if (product < k) {
                    cnt++;
                } else {
                    break;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 5, 2, 6};
        int k1 = 100;

        System.out.println(numSubarrayProductLessThanK(nums1, k1));   //Output: 8
//        Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
//        Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

    }
}
