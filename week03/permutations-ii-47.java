class Solution {
    List<List<Integer>> res = new LinkedList<>();
    int[] nums;
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        // 核心1：排序，排序让重复元素前后具有相关性，又不影响全排列结果
        Arrays.sort(this.nums);
        LinkedList<Integer> track = new LinkedList<>();
        used = new boolean[nums.length];
        backtrack(track, used);
        return res;
    }

    /**
     * 1.回溯的思路，套用模板
     * 核心：全排列：基础全排列的思路，再加剪支
     */
    private void backtrack(LinkedList<Integer> track, boolean[] used) {
        // 1.找到目标
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
        }

        // 2.决策列表nums
        for (int i = 0; i < nums.length; i++) {
            // 基础全排列的剪支
            if(used[i] == true) {
                continue;
            }

            // 核心2：递归搜索的过程，只要遇到起点一样(第1个1回溯到根，第2个1开始搜索时，搜索结果一样)，就有可能产生重复（注要是因为有重复元素）
            if (i > 0 && used[i - 1] == false && nums[i] == nums[i - 1]) {
                // 比如：[1 1 2]，2个1是不同的1
                continue;
            }
            // 3.做选择
            track.add(nums[i]);
            used[i] = true;
            // 4.递归到下一层
            backtrack(track, used);
            // 5.取消选择
            track.removeLast();
            used[i] = false;

        }
    }
}