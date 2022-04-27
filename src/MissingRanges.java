import java.util.ArrayList;
import java.util.List;

/**
 * 163. Missing Ranges
 * <p>
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 * <p>
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 * <p>
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        String str = "";

        if (nums.length == 0) {
            if (lower == upper)
                ans.add(Integer.toString(lower));
            else
                ans.add(lower + "->" + upper);
            return ans;
        }


        if (lower != nums[0]) {

            if (nums[0] == lower + 1)
                str = Integer.toString(lower);
            else
                str = lower + "->" + (nums[0] - 1);
            ans.add(str);
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                continue;
            } else {
                if (nums[i] + 1 == nums[i + 1] - 1)
                    str = Integer.toString(nums[i] + 1);
                else
                    str = (nums[i] + 1) + "->" + (nums[i + 1] - 1);
                ans.add(str);
            }
        }
        if (upper != nums[nums.length - 1]) {
            if (nums[nums.length - 1] == upper - 1)
                str = Integer.toString(upper);
            else
                str = (nums[nums.length - 1] + 1) + "->" + upper;
            ans.add(str);
        }

        return ans;

    }
}
