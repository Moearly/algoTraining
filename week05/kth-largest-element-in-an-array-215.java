class Solution {
    /**
     * 如果原本用 快排对 nums排序 需要 nlogn
     * 但优化 分支后，只是取一半的 路径在会取快排，时间复杂度优化到 O(2n)
     * 
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    // 快排模板，增加index判断，缩减快排规模，求在index位置的数
    public int quickSort(int[] nums, int l, int r, int index) {
        if (l >= r)
            return nums[l];
        int pivot = partition(nums, l, r);
        if (index <= pivot) {
            // index在左边
            return quickSort(nums, l, pivot, index);
        } else {
            return quickSort(nums, pivot + 1, r, index);
        }
    }
    /**
     * 快排核心：随机区级内一个位置，以该位置作为分区点pivot，判断 l r数据区间内的值和 pivotVal做比较
     * 1.l < r :
     * @return
     */
    public int partition(int[] nums, int l, int r) {
        int pivot = l + (int) (Math.random() * (r - l));
        int pivotVal = nums[pivot];
        while (l <= r) {
            // l和r就像指针，缩小范围，向中间靠拢
            while (nums[l] < pivotVal)
                l++;
            while (nums[r] > pivotVal)
                r--;
            if (l == r)
                break;
            if (l < r) {
                // 核心交换 l 和 r
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
        return r;
    }
}