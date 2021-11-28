class Solution {
    /**
     * 时间复杂度：2层循环 cpdomains 和 域名的长度量级基本一直，考虑到substring在java7以后不再是O(1)而是O(n)，所以是O(n*n*n) + hashmap遍历O(n) = O(n*n*n)
     * 算是暴力写法，但由于 cpdomains 在100 长度量级也在 100，所以总体还可以处理
     * 空间复杂度：开辟hashmap O(n)
     * 
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> ans = new ArrayList<>();
        // 统计单个域名：比如com，总的次数
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String item : cpdomains) {
            // 使用字符串分割得到每个子域名数组
            String[] ss = item.split(" ");
            // count为地址出现次数
            int count = Integer.parseInt(ss[0]);
            // 初始长域名 记录map
            map.put(ss[1], map.getOrDefault(ss[1], 0) + count);
            // 遍历 ss[1] 子域名
            while (ss[1].indexOf(".") != -1) {
                // 按"."取子串
                String sub = ss[1].substring(ss[1].indexOf(".") + 1);
                // 累加子串访问次数
                map.put(sub, map.getOrDefault(sub, 0) + count);
                // 下轮子串
                ss[1] = sub;
            }
        }

        for (String key : map.keySet()) {
            // 组装结果：次数 域名
            ans.add(map.get(key) + " " + key);
        }
        return ans;
    }
}