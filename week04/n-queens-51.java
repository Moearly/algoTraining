class Solution {
    List<List<String>> res = new ArrayList<>();
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        // 初始化棋盘，java这边直接处理字符更方便
        char[][] board = new char[n][n];
        for (char[] c : board) {
            // 初始化 .
            Arrays.fill(c, '.');
        }
        bfs(board, 0);
        return res;
    }

    public void bfs(char[][] board, int row) {
        // 每一行都能放，算一组成功
        if (row == n) {
            res.add(charToList(board));
            return;
        }
        // 在当前行的每一列都可能放置皇后 col->每一行
        for (int col = 0; col < n; col++) {
            // 排除可以相互攻击的格子
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = 'Q';
            // 进入下一行放皇后
            bfs(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    /* 
     * 判断是否可以在 board[row][col] 放置皇后
     * 核心：检查 左上 上 右上是否有 Q 之前有了就不能放
     */
    public boolean isValid(char[][] board, int row, int col) {
        // 1.检查列是否有皇后冲突（当前这一行 col是否有Q）
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 2.检查右上方是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 3.检查左上方是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将棋盘转为最后的结果
     */
    public List charToList(char[][] board) {
        List<String> list = new ArrayList<>();

        for (char[] c : board) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
}