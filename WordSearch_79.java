/*79. Word Search
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.
*/

public class WordSearch_79 {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0 
            || board == null || board.length == 0 || board[0].length == 0) return false;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }
    
    private int[] dx = {0, 1, 0,- 1};
    private int[] dy = {1, 0, -1, 0};
    private boolean dfs(char[][] board, String word, int index, int x, int y) {
        if (index == word.length())
            return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(index))
            return false;
        
        char original = board[x][y];
        board[x][y] = '#';
        boolean flag = false;
        for (int k = 0; k < 4; ++k) {
            flag = flag || dfs(board, word, index + 1, x + dx[k], y + dy[k]);
        }
        board[x][y] = original;
        return flag;
    }
}