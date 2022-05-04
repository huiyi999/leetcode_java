import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : chy
 * @date: 2022-05-04 4:37 p.m.
 */

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * <p>
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 * <p>
 * You may assume that the borders of the maze are all walls (see examples).
 */
public class TheMaze {

    // bfs
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return true;
            }

            for (int[] d : dirs) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += d[0];
                    y += d[1];
                }
                x -= d[0];
                y -= d[1];
                if (!visited[x][y]) {
                    q.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }
    // bfs  & dfs
    // time & space O(m*n)

    // dfs
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;

        // right
        while (r < maze[0].length && maze[start[0]][r] == 0) {
            r++;
        }
        if (dfs(maze, new int[]{start[0], r - 1}, destination, visited)) {
            return true;
        }

        //left
        while (l >= 0 && maze[start[0]][l] == 0) {
            l--;
        }
        if (dfs(maze, new int[]{start[0], l + 1}, destination, visited)) {
            return true;
        }

        //up
        while (u >= 0 && maze[u][start[1]] == 0) {
            u--;
        }
        if (dfs(maze, new int[]{u + 1, start[1]}, destination, visited)) {
            return true;
        }

        //down
        while (d < maze.length && maze[d][start[1]] == 0) {
            d++;
        }
        if (dfs(maze, new int[]{d - 1, start[1]}, destination, visited)) {
            return true;
        }
        return false;
    }
}
