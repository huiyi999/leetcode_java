/**
 * 1060. Missing Element in Sorted Array
 * <p>
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.
 */

public class MissingElementinSortedArray {

    public int missingElement(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1, mid;

        int missing = nums[hi] - nums[lo] - hi;

        // case 1: missingNum < k, then return nums[n - 1] + k - missingNum
        if (missing < k) return nums[hi] + (k - missing);

//         while(lo <= hi){
//             mid = lo + (hi - lo) / 2;
//             missing = nums[mid] - nums[0] - mid;

//             if(missing < k)
//                 lo = mid + 1;  // number of missing numbers from nums[0] to mid is less than k, so go right
//             else
//                  hi = mid - 1;
//         }
//         if (missing >= k) {
//             // desired number is to the left of nums[left].
//             // Minus 1, because we don't include nums[left]
//             return nums[lo] - 1 - (missing - k);
//         }

//         return nums[hi]+k-missing;


        // case 2: missingNum >= k, then use binary search(during the search k will be updated) to find the index in the array, where the kth missing number will be located in (nums[index], nums[index + 1]), return nums[index] + k
        while (lo < hi - 1) {
            mid = lo + (hi - lo) / 2;
            missing = nums[mid] - nums[lo] - (mid - lo);

            if (missing >= k) {
                // when the number is larger than k, then the index won't be located in (m, h]
                hi = mid;
            } else { // when the number is smaller than k, then the index won't be located in [l, m), update k -= missing

                k -= missing;
                lo = mid;
            }
        }
        return nums[lo] + k;
    }
    // why lo < hi - 1
    // because lo and hi will be the neighbors when they stop. So we could directly do nums[l] + k instead of moving the l back the previous valid position

    // O(logN) time O(1) space
}
