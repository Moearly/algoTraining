class Solution {
    public int numSquares(int n) {
        // 先遍历物品, 再遍历背包
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }
        // 当和为0，组合的个数为0 base case
        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i * i <= n; i++) {
            // 遍历背包
            for (int j = i * i; j <= n; j++) {
                if (dp[j - i * i] != max) {
                    // 状态转移方程
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n];
    }
}