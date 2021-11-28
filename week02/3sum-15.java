class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumTarget(nums, 0);
    }

    /**
     * 求3个数和目标是target
     * 时间复杂度 O(n*n) = O(n*n) + O(nlogn) 2层循环+排序 
     * 空间复杂度 O(n) 虽然是 2维数组 但内层数组数量最大3，
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> threeSumTarget(int[] nums, int target) {
        // 第一步排序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 枚举第一个数，固定nums[i]
        for (int i = 0; i < n; i++) {
            // 对 target - nums[i] 计算 twoSum
            List<List<Integer>> twos = twoSumTarget(nums, i + 1, target - nums[i]);
            // 存在，遍历组合
            for (List<Integer> two : twos) {
                // 满足条件的二元组，再加上nums[i]组成3元组
                two.add(nums[i]);
                res.add(two);
            }
            // 跳过重复的 nums[i]
            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
        }
        return res;
    }

    /**
     * 先计算2数之和目标的值
     * 时间复杂度 O(n) 一层循环 
     * 空间复杂度 O(n) 虽然是 2维数组 但内层数组数量最大2，
     * @param nums
     * @param start
     * @param target
     * @return
     */
    public List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        // 左指针start更具有通用性
        // nums已经排好序
        int n = nums.length;
        int lo = start;
        int hi = n - 1;
        // 双指针向中靠拢
        List<List<Integer>> res = new ArrayList<>();
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            int left = nums[lo];
            int right = nums[hi];
            if (sum > target) {
                while (lo < hi && nums[hi] == right)
                    hi--;
            } else if (sum < target) {
                while (lo < hi && nums[lo] == left)
                    lo++;
            } else {
                // 找到二元组
                List<Integer> two = new ArrayList<>();
                two.add(nums[lo]);
                two.add(nums[hi]);
                res.add(two);
                while (lo < hi && nums[lo] == left)
                    lo++;
                while (lo < hi && nums[hi] == right)
                    hi--;
            }
        }
        return res;
    }
}