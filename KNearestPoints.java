/*
Find the nearest k points to the origin (or some given points).
*/

import java.util.*;

class Point implements Comparable<Point> {
	int x, y, dist;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.dist = squaredDist();
	}

	private int squaredDist() {
		// to the origin
		return this.x * this.x + this.y * this.y;
	}

	@Override
	public int compareTo(Point that) {
		return this.dist - that.dist;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

public class KNearestPoints {


	public static List<Point> kNearest(List<Point> input, int k) {
		if (input.size() < k) return input;
		PriorityQueue<Point> minpq = new PriorityQueue<>();
		List<Point> ans = new ArrayList<>();
		for (Point p : input) {
			minpq.offer(p);
			// keep input.size() - k elements in the heap, so the answer has k elements
			if (minpq.size() > input.size() - k) {
				ans.add(minpq.poll());
			}
		}
		return ans;
	}

	// Also:Based on Quick-select
	// shuffle -> j = partition(input, 0, input.size()) -> search until j == k
	// O(n) on average 


	public static void main(String[] args) {
		Point a = new Point(-2, 1);
		Point b = new Point(-2, 0);
		Point c = new Point(0, -1);
		Point d = new Point(0, -8);
		Point e = new Point(1, 0);
		Point f = new Point(1, 1);
		Point g = new Point(2, 2);

		List<Point> input = new ArrayList<>(Arrays.asList(a, b, c, d, e, f, g));
		System.out.println(input);
		System.out.println(KNearestPoints.kNearest(input, 3));
	}
}