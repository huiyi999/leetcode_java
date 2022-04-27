import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1868. Product of Two Run-Length Encoded Arrays
 *
 * Run-length encoding is a compression algorithm that allows for an integer array nums with many segments of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded. Each encoded[i] = [vali, freqi] describes the ith segment of repeated numbers in nums where vali is the value that is repeated freqi times.
 *
 * For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]]. Another way to read this is "three 1's followed by five 2's".
 * The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:
 *
 * Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
 * Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
 * Compress prodNums into a run-length encoded array and return it.
 * You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2 respectively. Both nums1 and nums2 have the same length. Each encoded1[i] = [vali, freqi] describes the ith segment of nums1, and each encoded2[j] = [valj, freqj] describes the jth segment of nums2.
 *
 * Return the product of encoded1 and encoded2.
 *
 * Note: Compression should be done such that the run-length encoded array has the minimum possible length.
 */

public class ProductofTwoRunLengthEncodedArrays {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> ans = new ArrayList<>();

        int p1 = 0, p2 = 0;

        while(p1 < encoded1.length){
            int len = Math.min(encoded1[p1][1], encoded2[p2][1]);  // get the min freq of number
            int product = encoded1[p1][0] * encoded2[p2][0];

            if(ans.size() > 0 && ans.get(ans.size()-1).get(0) == product)  ////to handle cases like [[1,3],[2,3]] * [[6,3],[3,3]] --> [[6,6]]
                ans.get(ans.size()-1).set(1, ans.get(ans.size()-1).get(1) + len);
            else
                ans.add(Arrays.asList(product, len));

            encoded1[p1][1] -= len;
            encoded2[p2][1] -= len;
            if(encoded1[p1][1] == 0) p1++;
            if(encoded2[p2][1] == 0) p2++;
        }
        return ans;
        // time O(N)   n：length of full encode array
        // space O(1)

    }
}
