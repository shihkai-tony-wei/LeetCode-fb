/*133. Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

Idea: DFS + HashMap
for a Node in original graph
	- in the map: already cloned, just return the cloned node
	- not in the map: create a new clone node, and add ALL its neighbors as in original graph (no matter visited or not).
*/

/*
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

public class CloneGraph_133 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return dfs(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode x, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
    	if (x == null) return null;

    	if (map.containsKey(x))
    		return map.get(x);

    	// create cloned node and put into map
    	UndirectedGraphNode clone = new UndirectedGraphNode(x.label);
    	map.put(x, clone);

    	for (UndirectedGraphNode v : x.neighbors) {
    		// Do not check if visited
    		clone.neighbors.add(dfs(v, map));		// add new cloned nodes to x's neighbors
    	}
    	return clone;
    }
}