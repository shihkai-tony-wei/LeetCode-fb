/*
230. Kth Smallest Element in a BST
*/

public class KthSmallestElementBST_230 {
	// recursion
	// time O(n)--traverse all nodes to compute size
    public int kthSmallest(TreeNode root, int k) {
        if (k <= size(root.left))
        	return kthSmallest(root.left, k);
        else if (k == size(root.left) + 1)	// that's exactly the root
        	return root.val;
        else
        	return kthSmallest(root.right, k - size(root.left) - 1);
    }

    private int size(TreeNode x) {
    	if (x == null) return 0;
    	return size(x.left) + size(x.right) + 1;
    }


    // iterative: in-order traversal upto the k-th one
    public int kthSmallest(TreeNode root, int k) {
    	Deque<TreeNode> stack = new LinkedList<>();
    	TreeNode x = root;
    	int cnt = 0;
    	while (x != null || !stack.isEmpty()) {
    		while (x != null) {
    			stack.push(x);
    			x = x.left;
    		}
    		// it is the next node now
    		++cnt;
    		if (cnt == k) return stack.pop().val;
    		x = stack.pop().right;
    	}
    }

    // recursive in-order
    private static int count = 0;
    private static int res = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        count = k;
    	helper(root);
    	return res;
    }

    private void helper(TreeNode x) {
    	if (x == null) return;

    	helper(x.left);
    	
    	if (--count == 0) { // current node
    	    res = x.val;
    	    return;
    	}
    	
    	helper(x.right);
    }
}