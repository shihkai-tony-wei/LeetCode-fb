/*
200. Number of islands
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Idea: BFS O(m + n) -- iterater once: count the number of connected components
1. use an array to record is a node is visited
2. for each Node with value '1' who is NOT visited yet, do bfs and add 1 to number of islands
3. In BFS:
	1) create a Queue and offer the current node
	2) while queue is not empty:
		a) poll from queue
		b) try 4 directions from that node
			- if it is a valid node, i.e. in-bound + '1' + not visited
			- add to queue and mark as visited
*/

public class NumberOfIslands_200 {

	private class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

    public int numIslands(char[][] grid) {
    	int m = grid.length;
    	if (m == 0) return 0;
    	int n = grid[0].length;
    	if (n == 0) return 0;

        int cc = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
        	for (int j = 0; j < n; ++j) {
        		if (grid[i][j] == '1' && !visited[i][j]) {
        			bfs(grid, i, j, visited);
        			++cc;
        		}
        	}
        }
        return cc;
    }

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};

    private void bfs(char[][] grid, int x, int y, boolean[][] visited) {
    	Deque<Node> q = new LinkedList<>();
    	q.offer(new Node(x, y));
    	visited[x][y] = true;

    	while (!q.isEmpty()) {
    		Node curr = q.poll();
	    	for (int k = 0; k < 4; ++k) {
	    		int nx = curr.x + dx[k];
	    		int ny = curr.y + dy[k];
	    		if (isValid(grid, nx, ny, visited)) {
	    			q.offer(new Node(nx, ny));
	    			visited[nx][ny] = true;
	    		}
    		}
    	}
    }

	private boolean isValid(char[][] grid, int x, int y, boolean[][] visited) {
		if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
		&& grid[x][y] == '1' && !visited[x][y])
			return true;
		return false;
	}
}

/*
Method 2 : union-find
-- useful when dynamically change or given the island data
*/