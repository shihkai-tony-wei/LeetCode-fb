/*
Reversely print a linked list
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int val) { this.val = val; }
}

public class ReversePrintLinkedList {
	/* method 1: recursive
	 *   time: O(n)
	 *	 space: O(n) -- stack
	 */
	public void reversePrint1(ListNode head) {
		if (head == null) return;
		reversePrint1(head.next);
		System.out.print(head.val + " ");
	}

	/* method 2: iterative with O(1) space
	   reverse the list, print, and reverse back
	 *   time : O(n)
	 *   space : O(1) -- in place
	 */
	public void reversePrint2(ListNode head) {
		ListNode revHead = reverse(head);
		for (ListNode x = revHead; x != null; x = x.next) {
			System.out.print(x.val + " ");
		}
		reverse(revHead);
	}

	private ListNode reverse(ListNode head) {
		ListNode prev = null, curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		head.next = l2; l2.next = l3; l3.next = l4;
		ReversePrintLinkedList rp = new ReversePrintLinkedList();
		rp.reversePrint2(head);
	}
}