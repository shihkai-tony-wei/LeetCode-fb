/*
1. Find mutual friend of A and B.
2. Recommend to A some new friends (who A doesn't know already), ordered by the number of mutual friends they have.

e.g. 7 people labeled 1 .. 7
*/


import java.util.*;
public class FriendRecommendation {
	private static Map<Integer, List<Integer>> map = new HashMap<>();
	private static final int N = 7;

	private static class Person implements Comparable<Person> {
		int label;
		int mutual; 	// # of mutual friends with someone	
		Person(int l, int m) {
			label = l;
			mutual = m;
		}

		public int compareTo(Person that) {
			// reverse sort
			if (this.mutual != that.mutual)
				return that.mutual - this.mutual;
			else
				return this.label - that.label;
		}
	}

	public static List<Integer> mutualFriend(int a, int b) {
		// input: a's friends, b's friends
		List<Integer> mutual = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (int p : get_friend(a)) {
			set.add(p);
		}
		for (int i : get_friend(b)) {
			if (set.contains(i))
				mutual.add(i);
		}
		return mutual;
	}

	public static List<Integer> recommend(int a) {
		Set<Integer> set = new HashSet<>();
		for (int p : get_friend(a)) {
			set.add(p);
		}

		PriorityQueue<Person> candidates = new PriorityQueue<>();	// recommendation list (unordered)
		for (int i = 1; i <= N; ++i) {
			if (!set.contains(i) && i != a) {	// not recommending himself!!!
				int mutual = mutualFriend(a, i).size();
				candidates.offer(new Person(i, mutual));
			}
		}

		// sort the output
		List<Integer> res = new ArrayList<>();
		while (!candidates.isEmpty()) {
			res.add(candidates.poll().label);
		}
		return res;
	}

	public static void setup() {
		map.put(1, new ArrayList<>(Arrays.asList(2, 5)));
		map.put(2, new ArrayList<>(Arrays.asList(1, 3, 4, 6)));
		map.put(3, new ArrayList<>(Arrays.asList(2, 4, 5, 6, 7)));
		map.put(4, new ArrayList<>(Arrays.asList(2, 3)));
		map.put(5, new ArrayList<>(Arrays.asList(1, 3, 6)));
		map.put(6, new ArrayList<>(Arrays.asList(2, 3, 5)));
		map.put(7, new ArrayList<>(Arrays.asList(3)));	
	}

	public static List<Integer> get_friend(int a) {
		return map.get(a);
	}

	public static void main(String[] args) {
		FriendRecommendation.setup();
		// System.out.println(FriendRecommendation.mutualFriend(1, 3));
		System.out.println("Recommendation for person 1: " + FriendRecommendation.recommend(1));
	}
}