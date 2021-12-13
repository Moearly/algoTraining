class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        // 出现过的最大的点就是n，节点值是 1-n
        n = 0;
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            n = Math.max(a, n);
            n = Math.max(b, n);
        }

        // 1.出边数组初始化
        outs = new ArrayList<>();
        visit = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            outs.add(new ArrayList<Integer>());
            visit[i] = false;
        }
        hasCycle = false;
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            // 无向图看作双向边的有向图
            addEdge(a, b);
            addEdge(b, a);

            for (int i = 0; i <= n; i++)
                visit[i] = false;
            dfs(a, -1);
            if (hasCycle)
                return e;
        }
        return null;
    }

    private void dfs(int x, int fa) {
        visit[x] = true;
        for (Integer y : outs.get(x)) {
            if (y == fa)
                continue;
            if (visit[y])
                hasCycle = true;
            else
                dfs(y, x);
        }
    }

    // 模板：加边   
    private void addEdge(int x, int y) {
        outs.get(x).add(y);
    }

    int n;
    // 出边数组   
    private List<List<Integer>> outs;
    private boolean[] visit;
    boolean hasCycle;
}