/*
Deep copy of a linked list, where only API is random pop(), random peek() and isEmpty().
*/
import java.util.*;

class Node {
	int val;
	Node next;
	Node(int val) {
		this.val = val;
	}
}

class HiddenList {
	// a circular doubly linked list
	private Random rnd;
	private int[] nums;
	private int lo, hi;
	public HiddenList(int[] nums) {
		rnd = new Random();
		this.nums = nums;
		this.lo = 0;
		this.hi = nums.length - 1;
	}

	public int pop() {
		if (rnd.nextInt(2) == 0) {
			return nums[lo++];
		} else {
			return nums[hi--];
		}
	}

	public int peek() {
		if (rnd.nextInt(2) == 0) {
			return nums[lo];
		} else {
			return nums[hi];
		}
	}

	public boolean isEmpty() {
		return lo > hi;
	}

	public void display() {
		for (int i = lo; i <= hi; ++i) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}
}

public class RandomLinkedList {
	// Method 1: using a heap to sort -- O(n*logn)
	public Node restoreList(HiddenList hl) {
		PriorityQueue<Integer> minpq = new PriorityQueue<>();
		while (!hl.isEmpty()) {
			minpq.offer(hl.pop());
		}

		Node dummy = new Node(0);
		Node curr = dummy;
		while (!minpq.isEmpty()) {
			curr.next = new Node(minpq.poll());
			curr = curr.next;
		}
		return dummy.next;
	}

	/* Method 2:  suppose no duplicates
		if we can peek(): we know if the current popped element is max or min
		put it either at head or tail of two lists
	*/
	public Node restoreList2(HiddenList hl) {
		Node dummy = new Node(0);
		Node small = dummy, large = null;
		while (!hl.isEmpty()) {
			int num = hl.pop();
			int peeking = hl.peek();
			if (peeking > num)	{ // num is min -- append at tail of small
				small.next = new Node(num);
				small = small.next;
			} else {	// max -- put at head of large
				Node oldLarge = large;
				large = new Node(num);
				large.next = oldLarge;
			}
		}
		small.next = large;
		return dummy.next;
	}

	/* Method 3: using 2 linked lists + hashmap (if duplicates allowed) -- O(n)
	only pop()  -- append to small list's end if the new value is larger than the old tail
				-- else copy the old tail to large list's head, and replace the old tail
	corner case: pop() number == old tail, also need to process!!!
	*/
	public Node restoreList3(HiddenList hl) {
		Map<Integer, Integer> map = new HashMap<>();		// record how many time repeated
		Node dummy = new Node(Integer.MIN_VALUE);
		Node curr = dummy;
		Node large = null;
		while (!hl.isEmpty()) {
			int num = hl.pop();
			System.out.println(num);
			if (!map.containsKey(num)) {	// never seen before -- that means not equal
				map.put(num, 1);
				if (num > curr.val) {
					curr.next = new Node(num);
					curr = curr.next;
				} else {	// num < curr.val
					Node oldLarge = large;
					large = new Node(curr.val);
					large.next = oldLarge;
					// update curr
					curr.val = num;
				}
			} else {
				map.put(num, map.get(num) + 1);
				// continue;
			}	
		}
		// connect two lists
		curr.next = large;

		// add duplicates
		curr = dummy.next;
		while (curr != null) {
			int freq = map.get(curr.val);
			if (freq == 1) {
				curr = curr.next;
				continue;
			}
			Node next = curr.next;
			while (freq-- > 1) {
				curr.next = new Node(curr.val);
				curr = curr.next;
			}
			curr.next = next;
			curr = next;
		}
		return dummy.next;
	}

	public void printList(Node x) {
		while (x.next != null) {
			System.out.print(x.val + "->");
			x = x.next;
		}
		System.out.println(x.val);
	}

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5};
		HiddenList hl = new HiddenList(nums);
		RandomLinkedList rl = new RandomLinkedList();
		Node head = rl.restoreList2(hl);
		rl.printList(head);
	}
}