/*
function get_friends(int A) finds all A's friends.
Find A's friends' friends, who are NOT A's direct friends.

BFS: find depth of 2
*/

public class FindFriends {

	public LinkedList<Character> get_friends_of_friends(char A) {
		LinkedList<Character> q = new LinkedList<>();
		Set<Character> visited = new HashSet<>();
		int level = 0;
		q.offer(A);
		visited.add(A);

		while (!q.isEmpty() && level < 2) {
			int sz = q.size();
			while (sz-- > 0) {
				char curr = q.poll();
				for (char foa : get_friends(curr)) {
					if (!visited[foa]) {
						q.offer(foa);
						visited.add(foa);
					}
				}
			}
			++level;
		}
		return q;
	}

	// public static void main(String[] args) {
	// 	int[][] edges = {{'A', 'B'}, {'A', 'C'}, {'A', 'D'}, {'A', 'E'}, {'B', 'C'}, {'B', 'F'}, {'D', 'G'}};
	// }
}