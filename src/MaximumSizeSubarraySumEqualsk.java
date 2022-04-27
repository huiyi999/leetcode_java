import java.util.HashMap;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * <p>
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead.
 */
public class MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        int currSum = 0, ans = 0;
        // set initial values for cumulative sum and max length sum to k
        HashMap<Integer, Integer> prefixSumMap = new HashMap<Integer, Integer>();
        // key: cumulative sum until index i, value: i;  to get the maxlen after truncation

        for (int i = 0; i < nums.length; i++) {
            currSum = currSum + nums[i]; // update current cumulative sum

            // case 1: cumulative sum is k, update maxLen for sure
            if (currSum == k)
                ans = Math.max(ans, i + 1);
                // case 2: cumulative sum is more than k, but we can truncate a prefix of the array
            else if (prefixSumMap.containsKey(currSum - k))
                ans = Math.max(ans, i - prefixSumMap.get(currSum - k));

            // store cumulative sum in map, only if it is not seen
            // because only the earlier (thus shorter) subarray is valuable, when we want to get the maxLen after truncation
            if (!prefixSumMap.containsKey(currSum))
                prefixSumMap.put(currSum, i);
        }
        return ans;
    }
}
