public class Solution {

    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;

        // 空地的数量
        int spaces = 0;
        UnionFind unionFind = new UnionFind(m * n);
        // 只需要2个方向
        int[][] dirs = { { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    spaces++;
                } else {
                    // 此时 grid[i][j] == '1'
                    for (int[] dir : dirs) {
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        // 先判断坐标合法，再检查右边一格和下边一格是否是陆地
                        if (newX < m && newY < n && grid[newX][newY] == '1') {
                            unionFind.unionSet(getIndex(i, j), getIndex(newX, newY));
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
    }

    private int getIndex(int i, int j) {
        return i * n + j;
    }

    private class UnionFind {
        /**
         * 连通分量的个数
         */
        private int count;
        private int[] fa;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (x == fa[x])
                return x;
            // 路径压缩
            return fa[x] = find(fa[x]);
        }

        public void unionSet(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                fa[x] = y;
                // 唯一区别
                count--;
            }

        }
    }
}