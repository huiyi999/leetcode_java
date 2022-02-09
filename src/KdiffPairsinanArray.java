import java.util.*;

/**
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * a <= b
 * b - a == k
 */

public class KdiffPairsinanArray {

    public static int findPairs(int[] nums, int k) {
        if (nums.length <= 1)
            return 0;
        Arrays.sort(nums);

        int count = 0;
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {

            if (!map.containsKey(nums[i]))
                map.put(nums[i], new HashSet<>());

            for (int j = i + 1; j < nums.length; j++) {
//                System.out.println("diff: " + (nums[j] - nums[i]));
                if ((nums[j] - nums[i]) == k) {

                    HashSet<Integer> list = map.get(nums[i]);

                    if (!list.contains(nums[j])) {
                        list.add(nums[j]);
                        map.put(nums[i], list);
                        count++;

                    }
                }

            }

        }


        return count;
    }

    public static int findPairs3(int[] nums, int k) {
        if(k < 0) return 0;
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            //包含+k时直接添加到set2
            if(set1.contains(nums[i] + k)) {
                set2.add(nums[i]);
            }
            //包含-k时直接添加到set2
            if(set1.contains(nums[i] - k)) {
                set2.add(nums[i] - k);
            }
            //把所有值存入set1
            set1.add(nums[i]);
        }
        //返回2中的数量即为结果
        return set2.size();
    }



    int numPairs = 0;

    public int findPairs2(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = 0;

        if (k < 0) {
            return 0;
        }

        while (i < nums.length && j < nums.length) {
            if (i != j && nums[i] + k == nums[j]) {
                numPairs++;
                while (j < nums.length && nums[i] + k == nums[j]) j++; //To eliminate duplicate
            } else if (nums[i] + k > nums[j]) {
                j++;
            } else {
                i++;
            }
        }
        return numPairs;
    }



    public static void main(String[] args) {
        int[] nums1 = {3, 1, 4, 1, 5};
        int k1 = 2;

        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 1;

        int[] nums3 = {1, 3, 1, 5, 4};
        int k3 = 0;

        int[] nums4 = {1, 2, 4, 4, 3, 3, 0, 9, 2, 3};
        int k4 = 3;

        int[] nums5 = {-1, -2, -3};
        int k5 = 1;

        System.out.println("1:" + findPairs(nums1, k1));   //        Output: 2
        //        Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
        //        Although we have two 1s in the input, we should only return the number of unique pairs.
        System.out.println("2:" + findPairs(nums2, k2));   //        Output: 4
        //        Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
        System.out.println("3:" + findPairs(nums3, k3));   //        Output: 1
        //        Explanation: There is one 0-diff pair in the array, (1, 1).
        System.out.println("4:" + findPairs(nums4, k4));   //        Output: 2
        System.out.println("5:" + findPairs(nums5, k5));   //        Output: 2


        System.out.println("1:" + findPairs3(nums1, k1));   //        Output: 2
        //        Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
        //        Although we have two 1s in the input, we should only return the number of unique pairs.
        System.out.println("2:" + findPairs3(nums2, k2));   //        Output: 4
        //        Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
        System.out.println("3:" + findPairs3(nums3, k3));   //        Output: 1
        //        Explanation: There is one 0-diff pair in the array, (1, 1).
        System.out.println("4:" + findPairs3(nums4, k4));   //        Output: 2
        System.out.println("5:" + findPairs3(nums5, k5));   //        Output: 2

    }
}
