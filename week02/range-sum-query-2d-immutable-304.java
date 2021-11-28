class NumMatrix {
    // 前缀和
    int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        preSum = new int[n + 1][m + 1];
        if (matrix.length > 0) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    // matrix[i-1][j-1] 注意 matrix是0开始
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

    }

    public int sumRegion(int x1, int y1, int x2, int y2) {
        // 但由于我们源数组下标从 0 开始，因此要在模板的基础上进行 + 1
        x1++;
        y1++;
        x2++;
        y2++;
        return preSum[x2][y2] - preSum[x1 - 1][y2] - preSum[x2][y1 - 1] + preSum[x1 - 1][y1 - 1];
    }

}