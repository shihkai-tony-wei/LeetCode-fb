/*221. Maximal Square
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.


Idea: DP.
dp[i][j] = max length of square with bottom right corner as (i - 1, j - 1).

*/

public class MaximalSquare_221 {
	// O(m*n) time and O(m*n) space
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) return 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;

        int[][] dp = new int[m + 1][n + 1];		// Note: create a left and top boundary for corner cases (m+1)*(n+1) dp matrix
        int maxLen = 0;

        for (int i = 1; i <= m; ++i) {
        	for (int j = 1; j <= n; ++j) {
        		if (matrix[i - 1][j - 1] == '1')	// (i, j) in dp <==> (i-1, j-1) in matrix
        			dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
        		maxLen = Math.max(maxLen, dp[i][j]);
        	}
        }
        return maxLen * maxLen;
    }

    // optimize space to O(n):
    // Only keep two rows at a time
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) return 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;

        int[][] dp = new int[2][n];
        int maxLen = 0;

        // start with initializing first row
        for (int j = 0; j < n; ++j) {
            dp[0][j] = matrix[0][j] - '0';
            maxLen = Math.max(maxLen, dp[0][j]);
        }

        for (int i = 1; i < m; ++i) {
            // *****************************************
            // don't forget the first element in each new row!!!
            dp[i % 2][0] = matrix[i][0] - '0';
            maxLen = Math.max(maxLen, dp[i % 2][0]);
            // *****************************************
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == '1')
                    dp[i % 2][j] = Math.min(dp[i % 2][j - 1], Math.min(dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - 1])) + 1;
                else
                    dp[i % 2][j] = 0;
                maxLen = Math.max(maxLen, dp[i % 2][j]);
            }
        }
        return maxLen * maxLen;
    }
}