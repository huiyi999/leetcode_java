import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */

// rob[i] = max(rob[i-2]+num[i],rob[i-1])

public class HouseRobber {
    /**
     * Top Down DP
     */
    static Integer[] dp;

    public static int rob(int[] nums) {
        dp = new Integer[nums.length];
        return rob(nums, nums.length - 1);
    }

    private static int rob(int[] nums, int i) {
        if (i < 0) return 0;
        if (dp[i] != null) return dp[i];
        return dp[i] = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));


    }


    /**
     * Bottom-Up DP
     */
    public static int rob1(int[] nums) {
        int before1 = 0, before2 = 0, max_amount = 0;   //before1 = rob[i-1] before2 = rob[i-2]
        for (int num : nums) {
            max_amount = Math.max(before1, num + before2);  //rob[i] = max(rob[i-1], rob[i-2]+num[i])
            before2 = before1;
            before1 = max_amount;
        }
        return max_amount;
    }

    /**
     * Recursive
     */
//    public static int rob(int[] nums) {
//
//
//        return rob(nums, nums.length - 1);
//
//    }
//
//
//    private static int rob(int[] nums, int i) {
//        if (i < 0) return 0;
//        return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
//    }
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {2, 1, 1, 2};

        System.out.println(rob(nums1));  //Output: 4; Rob house 1 (money = 1) and then rob house 3 (money = 3).
        // Total amount you can rob = 1 + 3 = 4.
        System.out.println(rob(nums2));  //Output: 12; Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        // Total amount you can rob = 2 + 9 + 1 = 12.
        System.out.println(rob(nums3));  //Output: 4;


        System.out.println(rob1(nums1));  //Output: 4; Rob house 1 (money = 1) and then rob house 3 (money = 3).
        // Total amount you can rob = 1 + 3 = 4.
        System.out.println(rob1(nums2));  //Output: 12; Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        // Total amount you can rob = 2 + 9 + 1 = 12.
        System.out.println(rob1(nums3));  //Output: 4;

    }
}
