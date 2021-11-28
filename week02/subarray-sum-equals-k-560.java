import java.util.HashMap;

class Solution {
    /**
     * 时间复杂度：1.穷举：求前缀和和 O(n) + 穷举遍历 2层嵌套循环 O(n*n) = O(n*n) -> 1516ms 
     *           2.hash：单层循环 = O(n) -> 21ms 
     * 空间复杂度：前缀和需要多开辟n+1空间 = O(n)
     * 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // 求前缀和：快速计算某个子数组的和（空间换时间）
        // 方法1：穷举所有子数组
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 快速得到某个子数组的和
                if (preSum[i] - preSum[j] == k) {
                    ans++;
                }
            }
        }
        // 方法2：hash快速查询 前缀和
        int n = nums.length;
        HashMap<Integer, Integer> preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);
        int ans = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            // 0 到 i 前缀和，用hashmap的key存储的前缀和，也用value统计可能出现 多个目标
            sum0_i += nums[i];
            // 想要找的 前缀和 0 到 j
            int sum0_j = sum0_i - k;
            // 判断之前是否存在这样的前缀和
            if (preSum.containsKey(sum0_j)) {
                // 更新答案
                ans += preSum.get(sum0_j);
            }
            // 把 sum0_i 加入preSum
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return ans;
    }
}