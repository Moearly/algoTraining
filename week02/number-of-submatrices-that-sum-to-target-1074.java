class Solution {
    /**
     * 时间复杂度：1.暴力 二维前缀和 O(n*m) + 枚举 O(n*n*m*m)
     * 空间复杂度：开辟二维前缀和数组 O(m*n)
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 方法1：二维前缀和+暴力枚举
        int n = matrix.length, m = matrix[0].length;
        int[][] preSum = new int[n + 1][m + 1];
        // 求二维前缀和
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int ans = 0;
        // 枚举所有的子矩阵 4层 因为4个角都在变化 从 pq 到 ij
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int p = 1; p <= i; p++) {
                    for (int q = 1; q <= j; q++) {
                        // pq 到 ij 组成的矩形和 == traget
                        // 实际就是求之前的 sumRegion(i j p q) 会多减 preSum[p - 1][q - 1] : 核心就是要记住是 p q -1 算起，而不是 p q
                        if (preSum[i][j] - preSum[p - 1][j] - preSum[i][q - 1] + preSum[p - 1][q - 1] == target)
                            ans++;
                    }
                }
            }
        }
        return ans;

    }
}