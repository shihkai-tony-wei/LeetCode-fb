23. Merge k Sorted Lists
Heaps

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeKSortedLists_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> minpq = new PriorityQueue<ListNode>((a,b)->a.val-b.val);
        for (ListNode node : lists) {
            if (node != null)
                minpq.offer(node);
        }
        ListNode dummy = new ListNode(0);
        ListNode x = dummy;
        while (!minpq.isEmpty()) {
            ListNode curr = minpq.poll();
            if (curr.next != null)
                minpq.offer(curr.next);
            x.next = curr;
            x = curr;
        }
        
        // Optimize: when only 1 node left in the minpq, just break the loop and let x point to it.
        /*while (minpq.size() > 1) {
            ListNode curr = minpq.poll();
            if (curr.next != null)
                minpq.offer(curr.next);
            x.next = curr;
            x = curr;
        }
        x.next = minpq.poll();*/

        return dummy.next;
    }
}