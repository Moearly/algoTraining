class Solution {
    public int findPeakElement(int[] nums) {
        // 峰值，就是单调递增，到单调递减，
        // 二分模板
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // 2个相邻点确定趋势
            int lmid = (left + right) >> 1;
            int rmid = lmid + 1;
            if (nums[lmid] <= nums[rmid]) {
                // 上升：峰值肯定会在右边
                left = lmid + 1;
            } else {
                // 下降：
                right = rmid - 1;
            }
        }
        return right;
    }
}