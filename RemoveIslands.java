/*
Remove islands which has size less than k.

Two subroutines:
1. dfs/bfs to traverse the graph and count the size of each island. 
	- need visited and count
2. remove by dfs/bfs.
	- don't need any auxillary variable, just change all 1's to 0's in the island.
*/

import java.util.*;
public class RemoveIslands {

	private static int count = 0;
	
	public static void removeIslands(int[][] grid, int k) {
		if (grid.length == 0 || grid[0].length == 0) return;
		int m = grid.length, n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					bfs(grid, i, j, visited);
					if (count < k) {
						removeBfs(grid, i, j);
					}
					count = 0;
				}
			}
		}
	}

	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0};

	// ******************************************************************************
	// DFS version -- may result in stack overflow
	private static void dfs(int[][] grid, int x, int y, boolean[][] visited) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == 0)
			return;

		visited[x][y] = true;
		++count;
		for (int j = 0; j < 4; ++j) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			dfs(grid, nx, ny, visited);
		}
	}

	// if area less than k, remove whole island
	// visited matrix is not used here
	private static void removeDfs(int[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
			return;

		grid[x][y] = 0;
		for (int j = 0; j < 4; ++j) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			removeDfs(grid, nx, ny);
		}
	}

	// ******************************************************************************
	// BFS version
	private static void bfs(int[][] grid, int x, int y, boolean[][] visited) {
		Deque<int[]> q = new LinkedList<>();
		q.offer(new int[]{x, y});
		visited[x][y] = true;
		++count;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int j = 0; j < 4; ++j) {
				int nx = curr[0] + dx[j];
				int ny = curr[1] + dy[j];
				if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length
					 && grid[nx][ny] == 1 && !visited[nx][ny]) {
					q.offer(new int[]{nx, ny});
					visited[nx][ny] = true;
					++count;
				}
			}
		}
	}

	private static void removeBfs(int[][] grid, int x, int y) {
		Deque<int[]> q = new LinkedList<>();
		q.offer(new int[]{x, y});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			grid[curr[0]][curr[1]] = 0;
			for (int j = 0; j < 4; ++j) {
				int nx = curr[0] + dx[j];
				int ny = curr[1] + dy[j];
				if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length
					 && grid[nx][ny] == 1) {
					q.offer(new int[]{nx, ny});
				}
			}
		}
	}
	// ******************************************************************************


	public static void main(String[] args) {
		int[][] grid = {{1,1,1,0,1}, {1,1,0,0,1}, {0,0,1,0,0}, {1,0,1,1,0}, {1,0,1,1,1}};
		RemoveIslands.removeIslands(grid, 5);
		for (int[] row : grid)
			System.out.println(Arrays.toString(row));
	}
}