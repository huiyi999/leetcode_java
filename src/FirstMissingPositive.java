import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {

        if (nums.length == 0)
            return 1;

        Arrays.sort(nums);
//        for (int n : nums) {
//            System.out.println(n);
//        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                list.add(nums[i]);
        }
        if (list.size() == 0 || list.get(0) > 1)
            return 1;
        for (int i = 1; i < list.size(); i++) {
            int tmp = list.get(i - 1);
            if (list.get(i) > tmp + 1)
                return tmp + 1;
        }

        return list.get(list.size() - 1) + 1;
    }

    /**
     *approach 2: 0ms
     */
    public int firstMissingPositive1(int[] nums) {
        int i = 0;
        int n = nums.length;
        while (i < nums.length) {
            if ((nums[i] > 0) && (nums[i] <= n) && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i]);
            } else
                i++;
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1)
                return j + 1;
        }
        return n + 1;
    }

    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b - 1];
        nums[b - 1] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        int[] nums4 = {-7, -8, -9, -11, -12};

        System.out.println(firstMissingPositive(nums1)); //Output: 3
        System.out.println(firstMissingPositive(nums2)); //Output: 2
        System.out.println(firstMissingPositive(nums3)); //Output: 1
        System.out.println(firstMissingPositive(nums4)); //Output: 1
    }
}
