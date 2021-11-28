class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 把每个字符串的字母排序，然后作为hash key
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = key2(str);
            // String key = key(str);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 方法2：排序
     */
    private String key2(String str) {
        char[] w = str.toCharArray();
        Arrays.sort(w);
        return new String(w);
    }

    /**
     * 方法1：统计词频的hash
     */
    private String key(String str) {
        // 统计字母频次数
        int[] dirs = new int[26];
        for (char w : str.toCharArray()) {
            // 累加重复字符
            dirs[w - 'a']++;
        }
        // 拼接成字符串
        StringBuilder b = new StringBuilder();
        for (int c : dirs) {
            b.append(c).append(',');
        }
        return b.toString();
    }
}