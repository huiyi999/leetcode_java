import java.util.HashMap;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        int[] result = new int[2];
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]),i};
            }
            map.put(target-nums[i], i);
        }

        return result;

    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] res1 = twoSum(nums1, target1);
        int[] res2 = twoSum(nums2, target2);
        int[] res3 = twoSum(nums3, target3);


    }
}
