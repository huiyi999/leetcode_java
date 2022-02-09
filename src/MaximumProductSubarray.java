import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 */

public class MaximumProductSubarray {

    /**
     *  Dynamic Programming
     * When iterating the array, each element has two possibilities:
     * positive number or negative number. We need to track a minimum value,
     * so that when a negative number is given, it can also find the maximum value.
     * We define two local variables, one tracks the maximum and the other tracks the minimum.
     */
    public static int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i]>0){
                max[i]=Math.max(nums[i], max[i-1]*nums[i]);
                min[i]=Math.min(nums[i], min[i-1]*nums[i]);
            }else{
                max[i]=Math.max(nums[i], min[i-1]*nums[i]);
                min[i]=Math.min(nums[i], max[i-1]*nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;


        //memory exceed
//        ArrayList<Integer> list = new ArrayList<>();
//        if (nums.length==1)
//            return nums[0];
//
//        for (int i = 0; i < nums.length; i++) {
//            list.add(nums[i]);
////            System.out.println("nums[i]: "+nums[i]);
//            for (int j = i + 1; j < nums.length; j++) {
//                int tmp = list.get(list.size() - 1);
////                System.out.println("tmp: "+tmp);
////                System.out.println("nums[j]: "+nums[j]);
////                System.out.println("tmp * nums[j]: "+tmp * nums[j]);
//                list.add(tmp * nums[j]);
//            }
//        }
//
//        int product = Collections.max(list);
//        return product;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, -2, 4};
        int[] nums2 = {-2, 0, -1};
        int[] nums3 = {-2};
        int[] nums4 = {0, 2};
        int[] nums5 = {-2, 3, -4};
        int[] nums6 = {2, -5, -2, -4, 3};


        System.out.println(maxProduct(nums1));   //Output: 6.   Explanation: [2,3] has the largest product 6.
        System.out.println(maxProduct(nums2));   //Output: 0.   Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
        System.out.println(maxProduct(nums3));   //Output: -2.
        System.out.println(maxProduct(nums4));   //Output: 2.
        System.out.println(maxProduct(nums5));   //Output: 24.
        System.out.println(maxProduct(nums6));   //Output: 24.


    }
}

//Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
//space complexity and make it to be O(1).
//public int maxSubArray(int[] nums) {
//    int result = nums[0];
//    int sum = nums[0];
//
//    for(int i=1; i<nums.length; i++){
//        sum = Math.max(nums[i], sum + nums[i]);
//        result = Math.max(result, sum);
//    }
//
//    return result;
//}