import java.util.Arrays;

/**
 * 259. 3Sum Smaller
 * <p>
 * Given an array of n integers nums and an integer target,
 * find the number of index triplets i, j, k with 0 <= i < j < k < n
 * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 */
public class SumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums); // sort an input array

        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {

            int lo = i + 1, hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];

                if (sum >= target) {
                    hi--;
                } else {
                    ans += hi - lo;
                    //any elements that smaller than nums[right] and larger than nums[left] can still match it, as the sum can only go smaller, no duplicates
                    lo++;
                }
            }
        }
        return ans;

        // time O(N*N)
    }
}
