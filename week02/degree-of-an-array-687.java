/**
 * 
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 输入：[1, 2, 2, 3, 1] 输出：2
 * 度是2:元素1和2的出现频数最大，均为2
 * 连续子数组相同度：[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 所以：最短连续子数组[2, 2]的长度为2，返回2
 * 
 * 链接：https://leetcode-cn.com/problems/degree-of-an-array
 */
class Solution {
    /**
     * 时间复杂度：一层遍历O(n)(内部hashmap取值添加都是O(1)) + 遍历hashMap O(n)  = O(n)
     * 空间复杂度：开辟hashmap = O(n)
     */
    public int findShortestSubArray(int[] nums) {
        // 核心：最短子数组的 起始 和 终止 位置，由出现次数最多的元素第一次和最后一次出现的位置确定
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        // Key：为数组中的值
        // Value：为一个数组，1：Key出现的次数，2：Key第一次出现的位置，3：Key最后一次出现的位置。
        int n = nums.length;
        // 遍历数组，将每一个元素出现的次数、第一次出现的位置、最后一次出现的位置记录下来
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                // 累加
                map.get(nums[i])[0]++;
                // 更新结尾
                map.get(nums[i])[2] = i;
            } else {
                // 添加初始
                map.put(nums[i], new int[] { 1, i, i });
            }
        }

        // 求与原数组相同度的最短子数组
        int maxNum = 0;// 最大度
        int minLen = 0;// 统计最小的长度
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            // 遍历
            int[] v = entry.getValue();
            // 找最短
            if (v[0] > maxNum) {
                // 有更大的度
                maxNum = v[0];
                // 肯定已最大的度 对应的 数组为准
                minLen = v[2] - v[1] + 1;
            } else if (v[0] == maxNum) {
                // 相同的度,比较数组的长度
                int len = v[2] - v[1] + 1;
                if (minLen > len) {
                    // 更新更小
                    minLen = len;
                }
            }
        }
        return minLen;
    }
}