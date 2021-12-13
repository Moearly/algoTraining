class Solution {
    // 存放 字符串 -> 深度（取第一次遍历到的层数）
    private HashMap<String, Integer> depth = new HashMap<>();
    private HashSet<String> hasBank = new HashSet<>();

    /**
     * 通过 bfs 核心构建 树的选择 路径 基因序列8位，3中变化
     * 时间复杂度 内部2层for循环，但循环次数已知 所以还是 O(n)的算法
     * 空间复杂度 额外开辟的 3个n的空间 O(n)
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        // 广搜去遍历 8位字符的每一位都有3种变化
        // 起始为0
        depth.put(start, 0);
        // bank变成set方便快速 查询字符串
        for (String s : bank)
            hasBank.add(s);
        // 特殊情况®
        System.out.println(hasBank);
        if (!hasBank.contains(end))
            return -1;
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        // 可选字符数组
        char[] gene = { 'A', 'C', 'G', 'T' };
        while (!q.isEmpty()) {
            // 做bfs
            String s = q.poll();// 取出栈顶
            char[] sc = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < 4; j++) {
                    // 1.相同的 字符不需要变换
                    if (sc[i] != gene[j]) {
                        char cache = sc[i];
                        // 开始做字符变换
                        sc[i] = gene[j];
                        // 不再 bank 基因库中，无需处理
                        String changeS = new String(sc);
                        // 还原sc
                        sc[i] = cache;
                        if (!hasBank.contains(changeS))
                            continue;
                        // 每个点只需要访问一次，第一次就是最少层数
                        if (depth.containsKey(changeS))
                            continue;
                        // 只需要在基于变化之前的 基因字符串的高度 +1(一次变化)
                        depth.put(changeS, depth.get(s) + 1);

                        // 2.bfs处理 新基因序列变化可能
                        q.offer(changeS);
                        if (changeS.equals(end)) {
                            // 找到了目标变化 基因序列
                            return depth.get(changeS);
                        }
                    }
                }
            }
        }
        return -1;
    }
}