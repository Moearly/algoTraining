class Solution {
    /*
     * 动态规划解法
     */
    public int findNumberOfLIS(int[] nums) {
        // 动态规划
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        // 状态转移
        int[] dp = new int[n];// 以nums[i]为结尾的最长递增子序列长度
        int[] count = new int[n];
        // 初始化
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int max = 1;
        for (int i = 1; i < n; i++) { // 从1开始
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { // 递增序列
                    if (dp[j] + 1 > dp[i]) {
                        // 表示dp[i]和count[i]都需要更新。
                        count[i] = count[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[j] + 1 == dp[i]) {
                        // count[i]需要更新，要加上count[j]
                        count[i] += count[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max) {
                res += count[i];
            }
        }
        return res;
    }
}