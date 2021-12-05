class Solution {
    List<List<Integer>> res = new LinkedList<>();
    int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track);
        return res;
    }

    /**
     * 1.回溯的思路，套用模板
     * 核心：全排列，不用去除重复，就不需要start，nums中每个元素都要决策，但**选择track要 避免重复
     * 
     * 优化：存在裁剪分支的可能
     */
    private void backtrack(LinkedList<Integer> track) {
        // 1.找到目标
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
        }

        // 2.决策列表nums
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                // 全排列核心：已经选过的就不要重复->排除不合法选择
                continue;
            }
            // 3.做选择
            track.add(nums[i]);
            // 4.递归到下一层
            backtrack(track);
            // 5.取消选择
            track.removeLast();
        }
    }
}