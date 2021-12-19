class Solution {
    /**
     * 时间复杂度 ：O(logn)
     * 空间复杂度 : O(1)
     * @return
     */
    public int search(int[] nums, int target) {
        // 普通二分
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 找到目标值
            if (nums[mid] == target)
                return mid;
            // 缩小边界
            if (nums[mid] < target) {
                // 值在右边，更新左边界
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 找不到
        return -1;
    }
}