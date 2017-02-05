/*
Build BST from preorder traversal.
*/

import java.util.*;

class TreeNode {
	int val;
	TreeNode left, right;
	TreeNode(int val) {
		this.val = val;
	}
}

public class BuildBST_PreOrder {
	public static TreeNode buildTree(int[] nodes) {
		return buildTreeHelper(nodes, 0, nodes.length - 1);
	}

	private static TreeNode buildTreeHelper(int[] nodes, int lo, int hi) {
		if (lo > hi) return null;
		TreeNode x = new TreeNode(nodes[lo]);
		int i = lo + 1;
		while (i < hi) {
			if (nodes[i] > nodes[lo])
				break;
			i++;
		}
		x.left = buildTreeHelper(nodes, lo + 1, i - 1);
		x.right = buildTreeHelper(nodes, i, hi);
		return x;
	}

	public static List<Integer> preorder_traversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		helper(root, ans);
		return ans;
	}

	private static void helper(TreeNode x, List<Integer> ans) {
		if (x == null) return;
		ans.add(x.val);
		helper(x.left, ans);
		helper(x.right, ans);
	}

	public static void main(String[] args) {
		int[] nodes = {6,3,1,4,5,9,7};
		// int[] nodes = new int[0];
		TreeNode root = BuildBST_PreOrder.buildTree(nodes);
		List<Integer> list = BuildBST_PreOrder.preorder_traversal(root);
		System.out.println(Arrays.toString(list.toArray()));
	}
}