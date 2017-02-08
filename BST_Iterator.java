/*
BST iterator:
next() return next
all() return all remaining
*/

import java.util.*;

class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int val) {
			this.val = val;
		}
	}

public class BST_Iterator {

	private TreeNode curr;
	private Deque<TreeNode> stack;

	public BST_Iterator(TreeNode root) {
		curr = root;
		stack = new LinkedList<TreeNode>();
	}
	
	public int next() {
		// just pop the stack
		TreeNode next = stack.pop();
		curr = next.right;
		return next.val;
	}

	public List<Integer> all() {
		List<Integer> list = new ArrayList<>();
		while (hasNext())
			list.add(next());
		return list;
	}

	public boolean hasNext() {
		// all the way to the left until null
		boolean flag = curr != null || !stack.isEmpty();
		while (curr != null) {
			stack.push(curr);
			curr = curr.left;
		}
		return flag;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.left.right = new TreeNode(2);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		BST_Iterator iter = new BST_Iterator(root);
		if (iter.hasNext())
			System.out.println(iter.next());
		System.out.println(iter.all());
	}
}