/*
Second most frequent element in array.

Be careful: we update the most frequent only when the number changed
*/

import java.util.*;
public class SecondMostFrequent {
	public static int secondMostFrequent(int[] nums) {
		int first = 0, second = 0;
		int count1 = 0, count2 = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			if (!map.containsKey(num))
				map.put(num, 1);
			else 
				map.put(num, map.get(num) + 1);

			if (map.get(num) > count1) {
				if (first != num) {		// update only when first element changes
					count2 = count1;
					second = first;
					first = num;
				}
				count1 = map.get(num);	// update count1 anyway!!!
			} else if (map.get(num) > count2) {
				second = num;
				count2 = map.get(num);
			}
		}
		return second;
	}

	public static void main(String[] args) {
		int[] nums = {1,1,2,1,2,3,3,3,1};
		int[] nums1 = {1,2,2};
		System.out.println(SecondMostFrequent.secondMostFrequent(nums));
	}
}