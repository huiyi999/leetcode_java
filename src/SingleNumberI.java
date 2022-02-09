/**
 * 136, Single number
 */

// bit operator  XOR
public class SingleNumberI {

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums)
            res ^= n;
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 ={1,2,2,1,3};
        int[] nums2 ={9,1,9,0,2,2,1};

        System.out.println(singleNumber(nums1));
        System.out.println(singleNumber(nums2));

    }
}