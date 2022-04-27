import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistancefromAllBuildings {

    final int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * Step 1: start from every point 1 (building point), doing BFS traversal to fill out (or accumulate) distance array
     *  (MUST start over and doing every BFS traversal individually)
     *
     * Step 2: find minimum distance from dp array
     */
    public int shortestDistance(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // dp: store distance sum to all building for every point 0. Update values when we do BFS traversal

        int[][] reach = new int[m][n];
        // reach: store number of buildings that every point 0 can reach. Used for checking if current point is valid while we want to find final result
        int countBuilding = 0;

        // count the buildings
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    countBuilding++;
                }
            }
        }

        // bfs search travel distance
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    if (!bfs(grid, q, dp, reach, m, n, countBuilding))
                        // if dfs can't reach all buildings, return -1
                        return -1;
                    //countBuilding++;
                }
            }
        }

        // find the shortest travel distance
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println(reach[i][j]);
                if (grid[i][j] == 0 && reach[i][j] == countBuilding)
                    ans = Math.min(ans, dp[i][j]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    boolean bfs(int[][] grid, Queue<int[]> q, int[][] dp, int[][] reach, int m, int n, int countBuilding) {
        int count = 0;

        int level = 1;
        boolean[][] visited = new boolean[m][n];

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                int x = tmp[0];
                int y = tmp[1];

                for (int j = 0; j < 4; j++) {
                    int dx = x + dir[j][0];
                    int dy = y + dir[j][1];

                    if (dx >= 0 && dx < m && dy >= 0 && dy < n && !visited[dx][dy]) {
                        // count the buildings during bfs
                        if (grid[dx][dy] == 1) {
                            count++;
                            visited[dx][dy] = true;
                        }

                        if (grid[dx][dy] == 0) {
                            q.offer(new int[]{dx, dy});
                            dp[dx][dy] += level;
                            visited[dx][dy] = true;
                            reach[dx][dy]++;
                        }
                    }
                }
            }
            level++;
        }
        //System.out.println(count);
        return count == countBuilding;
    }
    // The total time complexity will be O(m^2*n^2)
    // The time complexity of starting from buildings O(B*M*N) (B: # of buildings)
    // and starting from empty places O(E*M*N) (E: # of empty places)

    // if building number is larger than empty land, can start from empty land
}
