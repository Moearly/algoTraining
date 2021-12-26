class Solution {
    public int jump(int[] nums) {
        //贪心
        int n = nums.length;
        //站在索引i做多能跳到索引end;
        int end = 0;
        //从索引[i..end]起，最远能到的距离
        int far = 0;
        int res = 0;
        for(int i = 0; i < n - 1; i++) {
            far = Math.max(nums[i] + i, far);
            if(end == i) {
                res++;
                end = far;
            }
        }
        return res;
    }
}