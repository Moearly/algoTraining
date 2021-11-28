class Solution {
    /**
     * 前缀和 解法
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // 想要求某个区间子数组最大，preSum[j] - preSum[i]，i<j，preSum[i]最小
        // 求前缀和
        int n = nums.length + 1;
        int[] preSum = new int[n];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        // 维护 前缀最小值：想要求某个区间子数组最大
        int[] preMin = new int[n];
        for (int i = 1; i < n; i++) {
            preMin[i] = Math.min(preMin[i - 1], preSum[i]);
        }
        // 
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, preSum[i] - preMin[i - 1]);
        }
        return res;

    }
}