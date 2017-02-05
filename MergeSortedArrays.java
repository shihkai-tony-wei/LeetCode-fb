/*88. Merge Sorted Array
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
*/
import java.util.*;
public class MergeSortedArrays {
	// time O(m + n)
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
        	if (nums1[i] < nums2[j]) nums1[k--] = nums2[j--];
        	else 					 nums1[k--] = nums1[i--];
        }
        // if j is exhausted, we are done
        while (j >= 0) {
        	nums1[k--] = nums2[j--];
        }
    }

    // ***************************************************
    // follow-up: k arrays
    // use priority queue
    private static class Node implements Comparable<Node> {
    	int row, col;
    	int val;
    	Node(int r, int c, int v) {
    		row = r;
    		col = c;
    		val = v;
    	}

    	public int compareTo(Node that) {
			return this.val - that.val;
		}
    }

    public static List<Integer> mergeKArrays(int[][] arr) {
    	int k = arr.length;
    	List<Integer> ans = new ArrayList<>();
    	PriorityQueue<Node> minpq = new PriorityQueue<Node>();
    	for (int i = 0; i < k; ++i) {
    		if (arr[i].length > 0) {
    			minpq.offer(new Node(i, 0, arr[i][0]));
    		}
    	}

    	while (!minpq.isEmpty()) {
    		Node curr = minpq.poll();
    		ans.add(curr.val);
    		if (curr.col != arr[curr.row].length - 1)
    			minpq.offer(new Node(curr.row, curr.col + 1, arr[curr.row][curr.col + 1]));
    	}
    	return ans;
    }

    public static void main(String[] args) {
    	int[][] arr = {{1,5,10}, {3,6}, {}, {4,8}, {}};
    	List<Integer> ans = MergeSortedArrays.mergeKArrays(arr);
    	System.out.println(Arrays.toString(ans.toArray()));
    }
}