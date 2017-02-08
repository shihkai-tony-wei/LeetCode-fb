/*
Given two pre-order traversal arrays of two binary search tree respectively, 
find first pair of non-matching leaves. 
*/

import java.util.*;

class TreeNode {
	int val;
	TreeNode left, right;
	TreeNode(int val) {
		this.val = val;
	}
}

public class NonMatchLeavesOfBST {

	public static int[] firstNonMatchingLeaves(int[] nums1, int[] nums2) {
		int[] res = new int[2];
		List<Integer> leaf1 = leaves(nums1);
		List<Integer> leaf2 = leaves(nums2);
		for (int i = 0, j = 0; i < leaf1.size() && j < leaf2.size(); ++i, ++j) {
			if (leaf1.get(i) != leaf2.get(j)) {
				res[0] = leaf1.get(i);
				res[1] = leaf2.get(j);
				break;
			}
		}
		return res;
	}

	public static List<Integer> leaves(int[] nums) {
		// from preorder, find all leaves
		List<Integer> ans = new ArrayList<>();
		buildTree(ans, nums, 0, nums.length - 1);
		return ans;
	}

	private static TreeNode buildTree(List<Integer> ans, int[] nums, int lo, int hi) {
		if (lo > hi) return null;

		if (lo == hi) {		// it is a leaf if only one element considered
			ans.add(nums[lo]);
		}

		TreeNode x = new TreeNode(nums[lo]);
		int i = lo + 1;
		while (i <= hi && nums[i] < nums[lo]) {
			++i;
		}
		x.left = buildTree(ans, nums, lo + 1, i - 1);
		x.right = buildTree(ans, nums, i, hi);

		return x;
	}

	public static void main(String[] args) {
		int[] nums1 = {3,1,2,6,5};
		int[] nums2 = {3,2,7,6};
		int[] res = NonMatchLeavesOfBST.firstNonMatchingLeaves(nums1, nums2);
		System.out.println(Arrays.toString(res));
	}

}