/**
 * On a 2-dimensional grid, there are 4 types of squares:
 * <p>
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square,
 * that walk over every non-obstacle square exactly once.
 */


/**
 * Algorithm
 * <p>
 * 1、Find out the coordinate to start [sx, sy] of where grid[i][j] = 1.
 * 2、Count the number zero / empty cells needs to visits.
 * 3、Start dfs from the start coordinate keep try out all for directions recursively.
 * 4、Instead of using separate visited boolean array just modify grid[x][y] = -1
 * which will help to return in case cell is visited or cell is blocker in grid.
 * 5、Decrease zero count
 * 6、Once you reach on finish cell (= 2) check the zero == -1 why? becuse we reaching on 2 by taking one more step as zero count.
 * 7、Back track and try other paths.
 */
public class UniquePathsIII {

    public static int uniquePathsIII(int[][] grid) {
        int count = 0, start_point1 = 0, start_point2 = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) ++count;
                else if (grid[i][j] == 1) {
                    start_point1 = i;
                    start_point2 = j;
                }
            }
        }
        return dfs(grid, start_point1, start_point2, count);   //count = number of walking points
    }

    private static int dfs(int[][] grid, int x, int y, int count) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == -1)
            return 0;
        if (grid[x][y] == 2)
            return count == -1 ? 1 : 0;
        grid[x][y] = -1;  // mark visited
        count--;        // decrease count
        int dir1 = dfs(grid, x + 1, y, count);
        int dir2 = dfs(grid, x, y + 1, count);
        int dir3 = dfs(grid, x - 1, y, count);
        int dir4 = dfs(grid, x, y - 1, count);

        grid[x][y] = 0;
        count++;
        return dir1 + dir2 + dir3 + dir4;
    }

    // =========== approach 2 Runtime: 0 ms===========
    int paths;
    int totalSteps;

    public int uniquePathsIII2(int[][] grid) {
        paths = 0;
        int[] locs = getStartLocAndEmptyLocs(grid);
        totalSteps = locs[2] + 1;
        exploreNext(grid, locs[0], locs[1], 0);
        return paths;
    }

    private int[] getStartLocAndEmptyLocs(int[][] grid) {
        int[] ans = new int[3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans[0] = i;
                    ans[1] = j;
                } else if (grid[i][j] == 0) {
                    ans[2]++;
                }
            }
        }
        return ans;
    }

    private static int[][] positionDelta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void exploreNext(int[][] grid, int i, int j, int steps) {
        if (grid[i][j] == 2) {
            if (totalSteps == steps) {
                paths++;
            }
            return;
        }
        int old = grid[i][j];
        grid[i][j] = 1; // mark as visited
        for (int[] delta : positionDelta) {
            int nbi = i + delta[0];
            int nbj = j + delta[1];
            if (notVisited(grid, nbi, nbj)) {
                exploreNext(grid, nbi, nbj, steps + 1);
            }
        }
        grid[i][j] = old;
    }

    private static boolean notVisited(int[][] grid, int i, int j) {
        // check in bound
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        // check state
        return grid[i][j] == 0 || grid[i][j] == 2;
    }

    // =========== approach 2 Runtime: 0 ms ===========

    public static void main(String[] args) {
        int[][] grid1 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        int[][] grid2 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        int[][] grid3 = {{0, 1}, {2, 0}};

        System.out.println(uniquePathsIII(grid1));   //Output: 2
        System.out.println(uniquePathsIII(grid2));   //Output: 4
        System.out.println(uniquePathsIII(grid3));   //Output: 0

    }
}
