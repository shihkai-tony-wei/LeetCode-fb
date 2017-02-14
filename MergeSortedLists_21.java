/*21. Merge Two Sorted Lists
*/
public class MergeSortedLists_21 {
	// recursive: time O(m + n), space O(m + n)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	if (l1.val <= l2.val) {
    		l1.next = merge(l1.next, l2);
    		return l1;
    	} else {
    		l2.next = merge(l1, l2.next);
    		return l2;
    	}
    }

    // iterative: with dummy node
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode x = dummy;

        while (l1 != null && l2 != null) {
        	if (l1.val <= l2.val) {
        		x.next = l1;
        		l1 = l1.next;
        	} else {
        		x.next = l2;
        		l2 = l2.next;
        	}
        	// update x!!!
        	x = x.next;
        }
        // when one of the list is exhausted, set x.next to the other (otherwise we lose them)
        x.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

}