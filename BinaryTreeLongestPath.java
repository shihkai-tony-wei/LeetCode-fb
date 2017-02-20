/*
Longest path in a binary tree (from leaf to another leaf).
*/

public class BinaryTreeLongestPath {
	private int maxLen = 0;
	public int longestPath(TreeNode root) {
		findDepth(root);
		return maxLen;
	}

	private int findDepth(TreeNode x) {
		// this findDepth computes the maximun depth staring at node x
		// ***ONLY ONE SIDED***
		if (x == null) return 0;
		int left = findDepth(x.left);
		int right = findDepth(x.right);

		// update global variable, using max left depth + right depth + 1 (x)
		maxLen = Math.max(maxLen, left + right + 1);
		return Math.max(left, right) + 1;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.left.right.left = new TreeNode(6);
		root.left.right.left.right = new TreeNode(7);

		BinaryTreeLongestPath bt = new BinaryTreeLongestPath();
		System.out.println(bt.longestPath(root));
	}
}