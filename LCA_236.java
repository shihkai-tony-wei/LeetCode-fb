/*236. Lowest Common Ancestor of a Binary Tree*/

public class LCA_236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    // iterative: dfs traverse and record parent node
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	Map<TreeNode, TreeNode> parent = new HashMap<>();
    	Deque<TreeNode> stack = new LinkedList<>();
    	parent.put(root, null);
    	stack.push(root);

    	while (!parent.containsKey(p) || !parent.containsKey(q)) {
    		TreeNode curr = stack.pop();
    		if (curr.right != null) {
    			parent.put(curr.right, curr);
    			stack.push(curr.right);
    		}
    		if (curr.left != null) {
    			parent.put(curr.left, curr);
    			stack.push(curr.left);
    		}
    	}
    	// now both p, q have parent links
    	Set<TreeNode> set = new HashSet<>();
    	while (p != null) {
    		set.add(p);
    		p = parent.get(p);
    	}

    	// look for the first parent of q that is in the p's ancestor list
    	while (!set.contains(q)) {
    		q = parent.get(q);
    	}
    	return q;	// could be null
    }
}