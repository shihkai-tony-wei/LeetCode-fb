/* 261. Graph Valid Tree
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
*/

/* Idea -- union find
for a graph to be a tree:
1. number of edges = number of vertices - 1
2. all nodes must be connected
*/


// Time for weighted UF with path compression is O(m log*n) -- almost linear time
class UnionFind {
	private int[] parent;
	private int cc;
	public UnionFind(int n) {
		parent = new int[n];
		cc = n;
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
		}
	}

	public int find(int x) {
		while (parent[x] != x) {
			parent[x] = parent[parent[x]];		// path compression
			x = parent[x];
		}
		return x;
	}

	public void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px != py) {
			parent[px] = py;
			--cc;
		}
	}

	public int query() {
		return cc;
	}
}

public class GraphValidTree_261 {
    public boolean validTree(int n, int[][] edges) {
    	if (edges.length != n - 1) return false;
    	
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
        	uf.union(e[0], e[1]);
        }
        return uf.query() == 1;
    }
}