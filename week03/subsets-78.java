/**
 * 时间复杂度 递归 n 次， 
 * 空集复杂度 递归 n 次 
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    int[] nums; // 保存在全局避免递归拷贝

    public List<List<Integer>> subsets(int[] nums) {
        // 递归回溯的方式解题
        this.nums = nums;
        // 记录走过的路径，空集开始
        List<Integer> track = new ArrayList<>();
        backtrack(0, track);
        return res;
    }

    /**
     * 回溯模板（track->路径，nums->选择列表）
     * 思路：如何子集穷举
     * nums = [1,2,3]
     * 首先：空集[]
     * 然后：以1开头 [1] [12] [13] [123] 分支
     * 以2开头 [2] [23] 分支
     * 以3开头 [3] 分支
     * 子集是无序的，开头只是方便列举，而且还可以过滤 [12] [21]这种重复
     * 所以以start控制递归，就可以形成一颗树 以[] 为根
     */
    public void backtrack(int start, List<Integer> track) {
        // 前序遍历 -> 1.满足条件添加
        res.add(new ArrayList<>(track)); // 主要java是引用传递
        // start 开始防止重复子集
        for (int i = start; i < nums.length; i++) { // 2.遍历选择列表
            // 3.做选择
            track.add(nums[i]);
            // 4.递归
            backtrack(i + 1, track);
            // 5.撤销选择
            track.remove(track.size() - 1);
        }
    }
}