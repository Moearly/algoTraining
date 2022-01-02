class Solution {
    public int coinChange(int[] coins, int amount) {
        // 动态规划解题:dp数组定义 当目标金额为i时，至少需要dp[i]枚硬币凑出
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                // 子问题无解
                if (i - coin < 0)
                    continue;
                // 状态转移
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}