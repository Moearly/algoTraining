class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 开始位置：第一个 >=target 结束位置：最后一个 <=target
        int[] ans = new int[2];
        // 二分模板
        // 第一次找：开始位置
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                // 第一个 >=target 结束位置
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 结果在 right
        ans[0] = right;
        // 第二次找：结束位置
        left = -1;
        right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (nums[mid] <= target) {
                // 最后一个 <= target
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        ans[1] = right;

        if (ans[0] > ans[1])
            return new int[] { -1, -1 };
        return ans;
    }
}