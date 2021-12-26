import java.util.Arrays;

class Solution {
    /**
     * 1.先按 left 的相对顺序排序
     * 2.基于 left 去查找最远的 right （形成记录）
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 先排序 
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        // 找left开头最远的
        int farthest = -1;
        int start = -1;

        ArrayList<int[]> path = new ArrayList<>();
        int count = 0;
        for(int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if(left <= farthest) {
                //核心找更远的 right
                farthest = Math.max(farthest, right);
            }else {
                //较小的 left找到了 最大的 right
                if(farthest != -1) {
                    //不是 -1，添加结果
                    path.add(new int[]{start, farthest});
                    count++;
                }
                //更新 新的开始
                start = left;
                farthest = right;
            }
        }
        //最后还有一对结果
        path.add(new int[]{start, farthest});

        int[][] ans = new int[path.size()][2];
        for(int i = 0; i < path.size(); i++){
            ans[i][0] = path.get(i)[0];
            ans[i][1] = path.get(i)[1];
        }
        return ans;
    }
}