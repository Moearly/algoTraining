class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if(n <= 0 || k <= 0) return res;
        // 记录走过的路径，空集开始
        List<Integer> track = new ArrayList<>();
        backtrack(n, k, 1, track);
        return res;
    }

    /**
     * 1.回溯的思路，套用模板
     * 核心：组合，其实就某一特点长度的 子集
     * 
     * 优化：存在裁剪分支的可能
     */
    public void backtrack(int n,int k, int start, List<Integer> track) {
        // 前序遍历 -> 1.满足条件添加
        if(track.size() == k) {
            res.add(new ArrayList<>(track)); // 主要java是引用传递
        }
        // start 开始防止重复子集
        for (int i = start; i <= n; i++) { // 2.遍历选择列表
            // 3.做选择
            track.add(i);
            // 4.递归
            backtrack(n, k, i + 1, track);
            // 5.撤销选择
            track.remove(track.size() - 1);
        }
    }
}