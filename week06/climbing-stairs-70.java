class Solution {
    public int climbStairs(int n) {
        //动态规划
        int[] dp = new int[n + 1];
        //初始状态
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            //状态转移方程
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}