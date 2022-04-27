import java.util.LinkedList;
import java.util.Queue;

/**
 * 487. Max Consecutive Ones II
 * <p>
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 * <p>
 * Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */
public class MaxConsecutiveOnesII {
    // less efficient
    // Time complexity : O(n). Since both the pointers only move forward, each of the left and right pointer traverse a maximum of n steps. Therefore, the timecomplexity is O(n).
    // Space complexity : O(1)
    public int findMaxConsecutiveOnes1(int[] nums) {
        int numZeros = 0, k = 1, maxSeq = 0;

        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                numZeros++;
            while (numZeros > k) {
                if (nums[l++] == 0)   // less efficient;  for infinite stream, use Queue
                    numZeros--;
            }
            maxSeq = Math.max(maxSeq, h - l + 1);
        }
        return maxSeq;
    }

    //Time: O(n) Space: O(k)
    public int findMaxConsecutiveOnesk(int[] nums) {
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>();
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxSeq = 0, pre = -1;  // pre record the index of last zero
        // q stores the index of zero within the window [l, h] so its role is similar to Queue in the above solution
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                l = pre + 1;
                pre = h;
            }
            maxSeq = Math.max(maxSeq, h - l + 1);
        }

        return maxSeq;
    }
    // Time complexity : O(n)
    // Space complexity : O(1)
}
