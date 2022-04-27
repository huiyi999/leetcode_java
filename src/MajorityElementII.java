import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Note: The algorithm should run in linear time and in O(1) space.
 * <p>
 * Hide Hint #1
 * How many majority elements could it possibly have?
 * Do you have a better hint? Suggest it!
 */
public class MajorityElementII {
    public static List<Integer> majorityElement(int[] nums) {
        /**
         * appraoch 1: 8ms
         */
        List<Integer> list = new ArrayList<>();
        int times = Math.floorDiv(nums.length, 3);
        System.out.println("majority times: " + times);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                int tmp = map.get(i) + 1;
                map.put(i, tmp);
            } else map.put(i, 1);
        }
        for (Map.Entry<Integer, Integer> mm : map.entrySet()) {
            if (mm.getValue() > times)
                list.add(mm.getKey());
        }

        /**
         * approach 2: 2ms; the number of more than ⌊ n/3 ⌋ times < 3
         */
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        int cand1 = 0, cand2 = 0;

        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == cand1) {
                count1++;
                continue;
            }

            if (num == cand2) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                cand1 = num;
                count1 = 1;
                continue;
            }

            if (count2 == 0) {
                cand2 = num;
                count2 = 1;
                continue;
            }

            count1--;
            count2--;

        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == cand1) {
                count1++;
            } else if (num == cand2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3)
            result.add(cand1);

        if (count2 > nums.length / 3)
            result.add(cand2);

        return list;
    }

    public static void main(String[] args) {


        int[] nums1 = {3, 2, 3};
        int[] nums2 = {1, 1, 1, 3, 3, 2, 2, 2};

        List<Integer> result1 = majorityElement(nums1);   // Output: [3]
        List<Integer> result2 = majorityElement(nums2);   // Output: [1,2]

        for (int i : result1) {
            System.out.println(i);
        }
        for (int i : result2) {
            System.out.println(i);
        }
    }
}
