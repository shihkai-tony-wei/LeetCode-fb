/*
98. Validate Binary Search Tree

Idea: Don't just simply check if every subtree is a BST.
Must keep track of the max/min value the subtree could take.
*/

public class ValidateBST_98 {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);	// might overflow -- use long
    }

    private boolean helper(TreeNode x, long lb, long ub) {
    	if (x == null) return true;
    	if (x.val <= lb || x.val >= ub) return false;
    	return helper(x.left, lb, x.val) && helper(x.right, x.val, ub);
    }
}