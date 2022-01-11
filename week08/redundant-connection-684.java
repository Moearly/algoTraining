class Solution {
    // 并查集
    private int[] fa;

    public int find(int x) {
        if (x == fa[x])
            return x;
        // 路径压缩
        return fa[x] = find(fa[x]);
    }

    public void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y)
            fa[x] = y;
    }

    public int[] findRedundantConnection(int[][] edges) {
        fa = new int[1005];
        // 并查集初始化
        for (int i = 0; i < fa.length; i++)
            fa[i] = i;

        for (int i = 0; i < edges.length; i++) {
            if (find(edges[i][0]) == find(edges[i][1])) {
                return edges[i];
            } else {
                //合并
                unionSet(edges[i][0], edges[i][1]);
            }
        }
        return null;
    }
}