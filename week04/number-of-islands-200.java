class Solution {
    int m, n = 0;
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};

    /**
     * 方案一：dfs: 深度优先搜索容易理解
     *  1.方向数组
     *  2.遍历的方向 4 个 （考虑完所有情况）
     *  3.单次dfs 以某个 为1的点开始
     *  4.岛屿的判断条件(递归终止条件) 1.行i 列j 的边界 2.遇到 0
     * 时间复杂度：O(n*n*n*) 2层循环，格子行列 n*n ,单次dfs需要遍历格子上的 所有点 n*n
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        m = grid.length;
        n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                } 
            }
        }
        return count;
    }
    /**
     * 深度优先
     */
    public void dfs(char[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') return;
        grid[i][j] = '0';
        //四个方向扩展
        for(int a = 0; a < 4; a++) {
            dfs(grid, i+dx[a], j+dy[a]);
        }
    }
}