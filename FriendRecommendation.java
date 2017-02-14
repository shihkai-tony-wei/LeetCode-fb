/*
1. Find mutual friend of A and B.
2. Recommend to A some new friends, ordered by the number of mutual friends they have.
*/


import java.util.*;
public class FriendRecommendation {
	public static List<Integer> mutualFriend(int[] a, int[] b) {
		// input: a's friends, b's friends
		List<Integer> mutual = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (int i : a) {
			set.add(i);
		}
		for (int i : b) {
			if (set.contains(i))
				mutual.add(i);
		}
		return mutual;
	}

	public static List<Integer> recommend(int[] a, int n) {
		// people are numbered 1 ... n
		
	}

	public static void main(String[] args) {
		int[] a = {3,4,8,9,10};
		int[] b = {4,7,8,10,13};
		System.out.println(FriendRecommendation.mutualFriend(a, b));
	}
}