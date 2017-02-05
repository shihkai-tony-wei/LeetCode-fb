/*
297. Serialize and Deserialize Binary Tree
*/

/* Idea: pre-order traversal
Keep null nodes in the string.
*/


public class SerializeBinaryTree_297 {
	private final String delimiter = ",";
	private final String nullStr = "#";

	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode x, StringBuilder sb) {
    	if (x == null) {
    		sb.append(nullStr).append(delimiter);
    	} else {
    		sb.append(x.val).append(delimiter);
    		buildString(x.left, sb);
    		buildString(x.right, sb);
    	}
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(delimiter)));
        return buildTree(q);
    }

    private TreeNode buildTree(Deque<String> q) {
    	String val = q.poll();
    	if (val.equals(nullStr)) return null;

    	TreeNode x = new TreeNode(Integer.valueOf(val));
    	x.left = buildTree(q);
    	x.right = buildTree(q);
    	return x;
    }
}