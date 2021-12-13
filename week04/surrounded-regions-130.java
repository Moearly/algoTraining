class Solution {
    // m行 n列
    int m, n = 0;
    // 右下上左，方向数组
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, -1, 1, 0 };

    /**
     * 解法1.dfs
     * 思路，核心就是要找到 矩形内部的 O 替换成 X，内部定义，就是边沿的 O 不变
     * 1.如果对整个矩形 处理 O -> X很简单
     * 2.所以需要 方式去标记 4个边沿的 O 修改成其他的 字符 如：A，最后替换 A -> O
     * 3.核心难点，如何区分边沿的 O ,就只对 4个边做深搜。
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        m = board.length;
        n = board[0].length;
        // 1.遍历矩阵的外边一层的 O 相关关联的点，dfs修改为A
        for (int i = 0; i < m; i++) {
            // 对第一行和最后一行的所有 O 进行深度优先搜索
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            // 对第一列和最后一列的所有O进行深度优先搜索
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 2.将所有O都改为X
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                // 3.次数剩下的 A 都是边沿之前边沿的 O
                if (board[i][j] == 'A')
                    board[i][j] = 'O';
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        // 找到非 O 就返回
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;
        board[i][j] = 'A';
        for (int a = 0; a < 4; a++) {
            dfs(board, i + dx[a], j + dy[a]);
        }
        return;
    }
}