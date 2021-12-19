class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        //Pair的 key 代表位置
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(k, (p1, p2) -> p2.getKey().compareTo(p1.getKey()));
        for (int i = 0; i < n; i++) {
            q.offer(new Pair<>(nums[i], i));
            //开始形成窗口
            if(i >= k - 1) { 
                //维持[i - k + 1, i] 这个区间的max，小于 i - k + 1 删除
                while(q.peek().getValue() <= i - k) q.poll();
                //答案
                ans[i - k + 1] = q.peek().getKey();
            }
        }
        return ans;
    }
}