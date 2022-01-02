class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] ct = s.toCharArray();
        // dp数组全部初始化为0
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反向遍历保证正确的状态转移
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (ct[i] == ct[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个s的最长回文子序列长度
        return dp[0][n - 1];
    }
}