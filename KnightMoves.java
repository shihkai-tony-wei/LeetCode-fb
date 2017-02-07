/*
Given a chess board 8*8, find the minimum moves a knight could take starting at A --> ending at B.

Idea: BFS -- minimum distance from A to B.
*/
import java.util.*;

public class KnightMoves {

	private class Node {
		int x;
		int y;
		Node prev;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private int N = 8;
	private boolean[][] visited = new boolean[N][N];

	private int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
	private int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

	public int minMoves(int[] start, int[] end) {
		if (start[0] == end[0] && start[1] == end[1]) return 0;
		// BFS
		Deque<Node> q = new LinkedList<Node>();
		q.offer(new Node(start[0], start[1]));
		visited[start[0]][start[1]] = true;
		int step = 1;

		while (!q.isEmpty()) {
			int sz = q.size();		// poll all the Nodes in same level!!!
			while (sz-- > 0) {
				Node curr = q.poll();
				for (int k = 0; k < 8; ++k) {
					int nx = curr.x + dx[k];
					int ny = curr.y + dy[k];
					if (nx == end[0] && ny == end[1]) {
						displayPath(curr);
						System.out.println("[" + nx + ", " + ny + "]");
						return step;
					}
					if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
						Node node = new Node(nx, ny);
						node.prev = curr;
						q.offer(node);
						visited[nx][ny] = true;
					}
				}
			}
			++step;		// increase step after all nodes in previous level polled
		}
		return step;
	}

	private void displayPath(Node node) {
		if (node == null) return;
		displayPath(node.prev);
		System.out.print("[" + node.x + ", " + node.y + "] ");
	}

	public static void main(String[] args) {
		KnightMoves km = new KnightMoves();
		int[] A = {0, 0}, B = {3, 2};
		System.out.println("Minimum number of moves = " + km.minMoves(A, B));
	}
}