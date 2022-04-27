/**
 * @author : chy
 * @date: 2022-04-27 4:15 p.m.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2.
 * You may assume that multiplication is always possible.
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;

        int[][] res = new int[m][n];

        List[] list1 = new List[m];

        for (int i = 0; i < m; i++) {
            List<Integer> nums1 = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                if (mat1[i][j] != 0) {
                    nums1.add(j);
                    nums1.add(mat1[i][j]);
                }
            }
            list1[i] = nums1;
        }

        for (int i = 0; i < m; i++) {
            List<Integer> nums1 = list1[i];
            for (int t = 0; t < nums1.size() - 1; t += 2) {
                int col1 = nums1.get(t);
                int val1 = nums1.get(t + 1);
                for (int j = 0; j < n; j++) {
                    int val2 = mat2[col1][j];
                    res[i][j] += val1 * val2;
                }
            }
        }
        return res;
    }
    // time O(m * k * n)
    // space O(m * k)  compress mat1
}
