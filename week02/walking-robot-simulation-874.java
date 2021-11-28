class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // 方向数组     北  东  南  西
        int[] dirX = { 0, 1, 0, -1 };
        int[] dirY = { 1, 0, -1, 0 };
        // 起始位置
        int x = 0;
        int y = 0;
        // 0,1,2,3 代表 北  东  南  西，初始正北
        int status = 0;
        // 最终结果，最远距离
        int res = 0;
        // 存放 障碍物，判断：将x和y坐标组合成字符串
        HashSet<String> blockSet = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            blockSet.add(obstacles[i][0] + "," + obstacles[i][1]);
        }
        // 取出命令移动
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -1) {
                // 向右转 90 度
                status = (status + 1 + 4) % 4; // 循环
            }
            if (commands[i] == -2) {
                // 向左转 90 度
                status = (status - 1 + 4) % 4; // 循环
            }
            if (commands[i] > 0) {
                // 往某个方向走 n 步
                for (int n = 0; n < commands[i]; n++) {
                    int newX = x + dirX[status];
                    int newY = y + dirY[status];
                    if (blockSet.contains(newX + "," + newY)) {
                        // 跳过障碍物
                        continue;
                    } else {
                        x = newX;
                        y = newY;
                    }
                }
                res = Math.max(res, x * x + y * y);
            }
        }
        return res;
    }
}