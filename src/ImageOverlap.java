/**
 * Two images A and B are given, represented as binary, square matrices of the same size.
 * (A binary matrix has only 0s and 1s as values.)
 * <p>
 * We translate one image however we choose (sliding it left, right, up,
 * or down any number of units), and place it on top of the other image.
 * After, the overlap of this translation is the number of positions that have a 1 in both images.
 * <p>
 * (Note also that a translation does not include any kind of rotation.)
 * <p>
 * What is the largest possible overlap?
 */

/**
 * python version :
 * <p>
 * from scipy.ndimage import convolve
 * import numpy as np
 * <p>
 * class Solution:
 * def largestOverlap(self, A, B):
 * B = np.pad(B, len(A), mode='constant', constant_values=(0, 0))
 * return np.amax(convolve(B, np.flip(np.flip(A,1),0), mode='constant'))
 */


public class ImageOverlap {

    public static int largestOverlap(int[][] A, int[][] B) {
        int largestOverlap = 0;
        for (int row = -A.length + 1; row < A.length; row++)
            for (int col = -A[0].length + 1; col < A[0].length; col++)
                largestOverlap = Math.max(largestOverlap, overlappingOnes(A, B, row, col));
        return largestOverlap;
    }

    public static int overlappingOnes(int[][] A, int[][] B, int rowOffset, int colOffset) {
        int overlapOnes = 0;
        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[0].length; col++) {
                if ((row + rowOffset < 0 || row + rowOffset >= A.length) ||
                        (col + colOffset < 0 || col + colOffset >= A.length) ||
                        (A[row][col] + B[row + rowOffset][col + colOffset] != 2))
                    continue;
                overlapOnes++;
            }
        }
        return overlapOnes;
    }

    public static void main(String[] args) {

        int[][] A = {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] B = {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};

        int[][] A1 = {{0, 1}, {1, 1}};
        int[][] B1 = {{1, 1}, {1, 0}};

        System.out.println(B[-2][-2]);
        System.out.println(largestOverlap(A, B));
        System.out.println(largestOverlap(A1, B1));
    }
}
