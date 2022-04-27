import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. Walls and Gates
 *
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 */
public class WallsandGates {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int EMPTY = Integer.MAX_VALUE;
        int GATE = 0;

        Queue<int[]> q = new LinkedList<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE)
                    q.add(new int[]{row, col});  // Put gates in the queue
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] d : directions) {
                int newRow = row + d[0];
                int newCol = col + d[1];

                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || rooms[newRow][newCol] != EMPTY)
                    continue;

                rooms[newRow][newCol] = rooms[row][col] + 1;
                q.add(new int[]{newRow, newCol});
            }
        }

    }
    // BFS from EMPTY to GATE  Time Limit Exceeded
    // BFS from GATE to EMPTY
    // Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, the distance to an empty room must be the shortest.

    // Time complexity : O(mn).
    // Space complexity : O(mn)


    public void wallsAndGates1(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    fill(rooms, i, j, 0);
                }
            }
        }
    }

    private void fill(int[][] rooms, int i, int j, int d) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) return;
        d++;
        if (i > 0 && rooms[i - 1][j] > d) {
            rooms[i - 1][j] = d;
            fill(rooms, i - 1, j, d);
        }

        if (j > 0 && rooms[i][j - 1] > d) {
            rooms[i][j - 1] = d;
            fill(rooms, i, j - 1, d);
        }

        if (i < rooms.length - 1 && rooms[i + 1][j] > d) {
            rooms[i + 1][j] = d;
            fill(rooms, i + 1, j, d);
        }

        if (j < rooms[0].length - 1 && rooms[i][j + 1] > d) {
            rooms[i][j + 1] = d;
            fill(rooms, i, j + 1, d);
        }

    }
    // dfs faster
    // Time complexity : O(mn).
    // Space complexity : O(1)
}
