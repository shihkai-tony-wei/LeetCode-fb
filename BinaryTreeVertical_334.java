/*
314. Binary Tree Vertical Order Traversal

Idea: 2 hash tables to store the horizontal level, and list results
left: -1
right: +1
*/

public class BinaryTreeVertical_334 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        Map<Integer, List<Integer>> listMap = new HashMap<>();
        Deque<TreeNode> q = new LinkedList<>();

        // keep a minimum level for later sorted output
        int startLevel = 0;

        // process the root
        q.offer(root);
        levelMap.put(root, 0);

        while (!q.isEmpty()) {
        	int sz = q.size();
        	while (sz-- > 0) {
        		TreeNode curr = q.poll();
        		int lv = levelMap.get(curr);
        		// add to list after polling
        		if (!listMap.containsKey(lv))
        			listMap.put(lv, new ArrayList<Integer>());
        		listMap.get(lv).add(curr.val);

        		// subtrees
        		if (curr.left != null) {
        			q.offer(curr.left);
        			levelMap.put(curr.left, lv - 1);
        			startLevel = Math.min(startLevel, lv - 1);
        		}
        		if (curr.right != null) {
        			q.offer(curr.right);
        			levelMap.put(curr.right, lv + 1);
        		}
        	}
        }
        while (listMap.containsKey(startLevel)) {
        	ans.add(listMap.get(startLevel++));
        }
        return ans;
    }
}