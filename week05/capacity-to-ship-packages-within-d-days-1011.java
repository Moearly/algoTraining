class Solution {
    int n;

    public int shipWithinDays(int[] weights, int days) {
        n = weights.length;
        // 利用二分找到恰好(核心)满足days天内运完包裹的分割点 ans
        int max = 0, sum = 0;
        for (int weight : weights) {
            // 包裹最大值
            max = Math.max(max, weight);
            // 包裹的总和
            sum += weight;
        }
        // 二分模板
        int left = max, right = sum;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 检查是否在 days 内装下target
     */
    public boolean check(int[] weights, int target, int days) {
        int sum = 0;
        for (int val : weights) {
            if (sum + val > target) {
                sum = 0;
                days--;
            }
            sum += val;
        }
        return days > 0;
    }
}