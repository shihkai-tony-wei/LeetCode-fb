/*
LC257. Binary Tree Paths
**************************************************
Check whether a node is a leaf:
node.left == null && node.right == null
**************************************************
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Method 1: recursive -- keeping a String of path and recursively append new values to it.
public class BinaryTreePath_257 {
	
    public List<String> binaryTreePaths(TreeNode root) {
     	List<String> ans = new ArrayList<>();
     	if (root != null) helper(root, ans, "");
     	return ans;
    }

    private void helper(TreeNode x, List<String> ans, String path) {
    	if (x.left == null && x.right == null) ans.add(path + x.val);
    	if (x.left != null) helper(x.left, ans, path + x.val + "->");
    	if (x.right != null) helper(x.right, ans, path + x.val + "->");
    }
}


// Method 2: iterative -- dfs pre-order
public class BinaryTreePath_257 {
	// recursive: keeping a String of path and recursively append new values to it.
    public List<String> binaryTreePaths(TreeNode root) {
     	List<String> ans = new ArrayList<>();
     	if (root == null) return ans;
     	Deque<TreeNode> stack = new LinkedList<>();
     	Deque<StringBuilder> paths = new LinkedList<>();
     	stack.push(root);
     	paths.push(new StringBuilder(root.val + ""));

     	while (!stack.isEmpty()) {
     		TreeNode curr = stack.pop();
     		StringBuilder path = paths.pop();

     		if (curr.left == null && curr.right == null) {
     			ans.add(path.toString());
     		}
     		if (curr.right != null) {
     			stack.push(curr.right);
     			StringBuilder sb = new StringBuilder(path);		
     			// make a copy of path, so that in the next step path stays the same!
     			paths.push(sb.append("->").append(curr.right.val));
     		}
     		if (curr.left != null) {
     			stack.push(curr.left);
     			StringBuilder sb = new StringBuilder(path);
     			paths.push(sb.append("->").append(curr.left.val));
     		}
     	}
     	return ans;
    }


    // Follo-up: if we see a target, print the path to it twice.
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root != null) helper(root, ans, "", 2, "");
        return ans;
    }

    // use two paths to in the helper
    // path: normal path, targetPath: stores repeated path
    private void helper(TreeNode x, List<String> ans, String path, int target, String targetPath) {
        
        if (x.left == null && x.right == null) {
            if (x.val == target) ans.add(targetPath + path + x.val);
            else ans.add(targetPath + x.val);
        }
        if (x.left != null) {
            if (x.val == target)
                // append the previous targetPath by path and current val
                helper(x.left, ans, path + x.val + "->", target, targetPath + path + x.val + "->");
            else
                helper(x.left, ans, path + x.val + "->", target, targetPath + x.val + "->");
        }
        if (x.right != null) {
            if (x.val == target)
                helper(x.right, ans, path + x.val + "->", target, targetPath + path + x.val + "->");
            else
                helper(x.right, ans, path + x.val + "->", target, targetPath + x.val + "->");
        }
    }
}