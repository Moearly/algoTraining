class Solution {
    // 求差分
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 累计差分，原数组 0 到 n
        int[] sum = new int[n + 1];
        for (int[] booking : bookings) {
            // 航班编号是 1 开始，所以对应sum数组需要减一
            int l = booking[0] - 1;
            int r = booking[1] - 1;
            sum[l] += booking[2];
            sum[r + 1] -= booking[2];
        }
        // 求前缀和，前缀和 1 到 n
        int[] preSum = new int[n];
        // 注意preSum 0的位置，技巧
        preSum[0] = sum[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + sum[i];
        }
        return preSum;
    }
}