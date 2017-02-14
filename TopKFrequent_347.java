/*347. Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size

Idea: 
1. using heap: O(n*logn)
2. bucket sort: O(n) on average
*/

public class TopKFrequent_347 {
	private class Pair {
		int val, freq;
		Pair(int val, int freq) {
			this.val = val;
			this.freq = freq;
		}
	}

	// method 1: max-heap -- O(n*logn) 
    public List<Integer> topKFrequent(int[] nums, int k) {
  		Map<Integer, Integer> map = new HashMap<>();
  		for (int num : nums) {
  			map.put(num, map.getOrDefault(num, 0) + 1);
  		}
  		PriorityQueue<Pair> maxpq = new PriorityQueue<Pair>((a,b)->b.freq - a.freq);
  		for (int key : map.keySet()) {
  			maxpq.offer(new Pair(key, map.get(key)));
  		}

  		List<Integer> ans = new ArrayList<>();
  		while (k-- > 0) {
  			ans.add(maxpq.poll().val);
  		}
  		return ans;
    }

    // method 1: max-heap, with some optimization
    public List<Integer> topKFrequent(int[] nums, int k) {
  		Map<Integer, Integer> map = new HashMap<>();
  		for (int num : nums) {
  			map.put(num, map.getOrDefault(num, 0) + 1);
  		}
  		PriorityQueue<Pair> maxpq = new PriorityQueue<Pair>((a,b)->b.freq-a.freq);
  		List<Integer> ans = new ArrayList<>();
  		for (int key : map.keySet()) {
  			maxpq.offer(new Pair(key, map.get(key)));
  			if (maxpq.size() > map.size() - k) {
  				ans.add(maxpq.poll().val);
  			}
  		}
  		return ans;
    }

    // method 2: bucket sort -- O(n) on average
    // the i-th position in the bucket stores the elements that appear i times
    public List<Integer> topKFrequent(int[] nums, int k) {
    	List<Integer>[] buckets = new List[nums.length + 1]; 	// up to nums.length frequency
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int num : nums) {
  			map.put(num, map.getOrDefault(num, 0) + 1);
  		}
  		for (int key : map.keySet()) {
  			int freq = map.get(key);
  			if (buckets[freq] == null) {
  				buckets[freq] = new ArrayList<>();
  			}
  			buckets[freq].add(key);
  		}

  		List<Integer> ans = new ArrayList<>();
  		for (int i = nums.length; i > 0 && ans.size() < k; --i) {
  			if (buckets[i] != null) {
  				ans.addAll(buckets[i]);
  			}
  		}
  		return ans;
    }
}