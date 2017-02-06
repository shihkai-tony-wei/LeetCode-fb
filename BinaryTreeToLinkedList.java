/*
Given a Binary Tree, convert it to a Circular Doubly Linked List (In-Place) -- CDLL

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

	public static void display(Node head) {
		Node x = head;
		do {
			System.out.print(x.val + " ");
			x = x.right;
		} while (x != head);
	}


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

		Node head = BinaryTreeToLinkedList.treeToList(nodes[0]);
		BinaryTreeToLinkedList.display(head);
	}

}