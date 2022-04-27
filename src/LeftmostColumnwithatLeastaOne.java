import java.util.List;

/**
 * 1428. Leftmost Column with at Least a One
 * <p>
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 * <p>
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
 * <p>
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 * <p>
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
 */
// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
interface BinaryMatrix {
    public default int get(int row, int col) {
        return row;
    }

    public default List<Integer> dimensions() {
        return null;
    }
};


public class LeftmostColumnwithatLeastaOne {
    public int leftMostColumnWithOne2(BinaryMatrix binaryMatrix) {
        int row = binaryMatrix.dimensions().get(0);
        int col = binaryMatrix.dimensions().get(1);
        System.out.println(row + " " + col);

        int left = 0, right = col - 1, mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            boolean existOne = false;
            for (int r = 0; r < row; r++) {
                if (binaryMatrix.get(r, mid) == 1) {
                    existOne = true;
                    break;
                }
            }
            if (existOne) {
                right = mid - 1;

            } else {
                left = mid + 1;
            }
        }

        // not exists, left = mid + 1, when left > right stop loop, which means left = col
        return left >= col ? -1 : left;
    }
// solution 1: Binary Search
// Since rows are sorted in non-decreasing order.
// We do binary search to find the target column.
// mid is the col index, search by row
// Time: O(MlogN), where M is the number of rows, N is the number of columns in Binary Matrix
// Explain: existOneInColumn costs O(M), binary search columns costs O(logN)
// Space: O(1)

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int row = binaryMatrix.dimensions().get(0);
        int col = binaryMatrix.dimensions().get(1);
        System.out.println(row + " " + col);

        int r = 0, c = col - 1, leftMost = -1;

        while (r < row && c >= 0) {
            if (binaryMatrix.get(r, c) == 1) {
                leftMost = c;
                c--;

            } else {
                r++;
            }
        }

        return leftMost;
    }
    // solution 2: linear time
    // Using the information that the rows are sorted, if we start searching from the right top corner(1st row, last column) and every time when we get a 1, as the row is sorted in non-decreasing order, there is a chance of getting 1 in the left column, so go to previous column in the same row. And if we get 0, there is no chance that in that row we can find a 1, so go to next row.
// Complexity
// Time: O(M + N), where M is the number of rows, N is the number of columns in Binary Matrix
// Space: O(1)
}
