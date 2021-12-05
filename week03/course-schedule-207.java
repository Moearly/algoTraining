class Solution {
    /**
     * 拓扑排序
     * 核心：入度代表该点被修的先决条件
     * 时间复杂度：最后2层循环 O(n*n)
     * 空间复杂度：额外开辟 O(n) 的容器
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 存储课程入度
        int[] input = new int[numCourses];
        // 入度为0的顶点个数，入度为0才可修(也就是没有了前置课程)
        int count = 0;
        for (int[] p : prerequisites) {
            // 统计入度
            input[p[0]]++;
        }
        // 1.
        LinkedList<Integer> queue = new LinkedList<>();
        // 寻找入度为0，就是可以先修的课程
        for (int i = 0; i < numCourses; i++) {
            if (input[i] == 0) {
                queue.addLast(i);
            }
        }
        // 2.bsf(广度优先搜索)
        while (!queue.isEmpty()) {
            // 先出的都是先修课
            int node = queue.removeFirst();
            count++;
            // 把node关联点的入度减一，要被修了
            for (int[] p : prerequisites) {
                if (p[1] == node) {
                    input[p[0]]--;
                    if (input[p[0]] == 0) {
                        queue.addLast(p[0]);
                    }
                }
            }
        }
        // 统计的入度为0的点个数，就是统计最终修的点
        return count == numCourses;

    }
}