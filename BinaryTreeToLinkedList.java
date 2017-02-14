/*
Given a Binary Tree, convert it to a (Circular) Doubly Linked List (In-Place) -- CDLL

-The left and right pointers in nodes are to be used as leftious and right pointers respectively in converted Circular Linked List.
-The order of nodes in List must be same as Inorder of the given Binary Tree.
-The first node of Inorder traversal must be head node of the Circular List.
*/

/*
Idea: recursion
1. convert left and right subtrees into two CDLL
2. connect left + root, connect that with right
3. need a function to merge two CDLL's
*/
import java.util.*;
class Node {	// Circular doubly linked list - CDLL
	int val;
	Node left, right;
	Node(int val) {
		this.val = val;
	}
}

public class BinaryTreeToLinkedList {
	// ******************************************************************************
	// Method 1: in-order traversal of BST
	private static Node curr;		// use a global variable to track current node

	public static Node bstToList(Node root) {
		Node dummy = new Node(0);
		curr = dummy;
		buildList(root);
		// if circular:
		// dummy.right.left = curr;
		// curr.right = dummy.right;
		
		return dummy.right;
	}

	private static void buildList(Node x) {
		if (x == null) return;

		buildList(x.left);

		// connect curr to x and update curr
		x.left = curr;
		curr.right = x;		
		curr = x; 

		buildList(x.right);
	}

	// Iterative version
	public static Node bstToListIterative(Node root) {
		if (root == null) return null;
		Node dummy = new Node(0);
		Node curr = dummy, x = root;
		Deque<Node> stack = new LinkedList<>();
		while (x != null || !stack.isEmpty()) {
			while (x != null) {
				stack.push(x);
				x = x.left;
			}
			// now x is null
			x = stack.pop();
			// connect curr to x
			curr.right = x;
			x.left = curr;
			curr = x;

			x = x.right;
		}
		return dummy.right;
	}


	// ******************************************************************************
	// Method 2:
	public static Node concatenate(Node l1, Node l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		// get the tail of list 1, and head of l2
		l1.left.right = l2;
		Node tail2 = l2.left;
		l2.left = l1.left;
		l1.left = tail2;
		tail2.right = l1;
		return l1;
	}

	public static Node treeToList(Node x) {
		if (x == null) return null;

		// recursive calls to left and right subtrees
		// Note: create two NEW nodes for the left and right subtree's CDLLs
		Node left = treeToList(x.left);
		Node right = treeToList(x.right);

		// make a Circular doubly linked list of the root node x
		x.left = x;
		x.right = x;

		// concatenate three parts
		return concatenate(concatenate(left, x), right);
	}

	public static void displayCircular(Node head) {
		Node x = head;
		do {
			System.out.print(x.val + " ");
			x = x.right;
		} while (x != head);
	}

	public static void display(Node head) {
		Node x = head;
		while (x != null) {
			System.out.print(x.val + " ");
			x = x.right;
		}
	}


	// ******************************************************************************
	// test
	public static void main(String[] args) {
		Node[] nodes = new Node[6];
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < nodes.length / 2; ++i) {
			nodes[i].left = nodes[2*i + 1];
			if (2*i + 2 < nodes.length)
				nodes[i].right = nodes[2*i + 2];
		}

		Node head = BinaryTreeToLinkedList.bstToListIterative(nodes[0]);
		BinaryTreeToLinkedList.display(head);
	}

}