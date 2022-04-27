import java.util.PriorityQueue;

/**
 * 1102. Path With Maximum Minimum Value
 * <p>
 * Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
 * <p>
 * The score of a path is the minimum value in that path.
 * <p>
 * For example, the score of the path 8 → 4 → 5 → 9 is 4.
 */
public class PathWithMaximumMinimumValue {

    public int maximumMinimumPath(int[][] grid) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int m = grid.length;
        int n = grid[0].length;

        // in dfs, for each step, get the maximum min that we have seen so far, thus we reverse the ordering in the pq
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[2] - a[2]));
        pq.offer(new int[]{0, 0, grid[0][0]});

        boolean[][] visited = new boolean[m][n];

        // int ans = grid[0][0];  // if not store the score in PriorityQueue

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            int score = cur[2];


            // ans = Math.min(ans, grid[row][col]);

            if (row == m - 1 && col == n - 1)
                return score;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0 || visited[newRow][newCol])
                    continue;

                pq.add(new int[]{newRow, newCol, Math.min(score, grid[newRow][newCol])});
                visited[newRow][newCol] = true;

            }
        }
        return -1;
    }
// Complexity Analysis
// Let n,m be the dimensions of the input matrix grid and k be the largest value in the matrix.

// Time complexity: O(n⋅m⋅log(n⋅m))
// Pushing to or popping from the priority queue takes logarithmic time. The size of the priority queue can approach n⋅m, so each add/remove operation will take O(log(n⋅m) time.
// In the worst-case, we will traverse every cell in the matrix, which takes O(n⋅m) add/remove operations.
// To sum up, the overall time complexity is O(n⋅m⋅log(n⋅m))

// Space complexity: O(n⋅m)
// We used an array of size O(n⋅m) to store the visited/unvisited status of each cell.
// In the worse-case scenario, we need to traverse the entire matrix, which takes O(n⋅m) space for a single traversal.
// Therefore, the overall space complexity is O(n⋅m).
}
