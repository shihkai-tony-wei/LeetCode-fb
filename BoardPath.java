/*
Given a char board, print all path from top left (0, 0) to bottom right.
Can move right or down.

Idea: DFS / Backtracking
*/
import java.util.*;
public class BoardPath {
	public static void printPath(char[][] board) {
		int m = board.length, n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		dfs(board, 0, 0, visited, new StringBuilder());
	}

	private static int[] dx = {0, 1};
	private static int[] dy = {1, 0};

	private static void dfs(char[][] board, int x, int y, boolean[][] visited, StringBuilder sb) {
		// process the current move
		visited[x][y] = true;
		sb.append(board[x][y]);

		if (x == board.length - 1 && y == board[0].length - 1) {
			System.out.println(sb.toString());
		}

		// try all following moves
		for (int k = 0; k < 2; ++k) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (isValid(board, nx, ny, visited)) {
				dfs(board, nx, ny, visited, sb);
			}
		}

		// undo current move
		visited[x][y] = false;
		sb.setLength(sb.length() - 1);
	}

	private static boolean isValid(char[][] board, int x, int y, boolean[][] visited) {
		if (0 <= x && x < board.length && 0 <= y && y < board[0].length && !visited[x][y])
			return true;
		return false;
	}

	public static void main(String[] args) {
		char[][] board = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
		BoardPath.printPath(board);
	}
}