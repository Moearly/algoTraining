class Solution {
    public int lengthOfLastWord(String s) {
        // 最容易想到的解法一
        int res = 0;
        // 剔除尾部
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                res++;
            } else {
                res = 0;
            }

        }
        return res;

        // 优化的字符串遍历
        int end = s.length() - 1;
        // 跳过尾部空格
        while (end >= 0 && s.charAt(end) == ' ')
            end--;
        if (end < 0) return 0;
        int start = end;
        // 跳过非空
        while (start >= 0 && s.charAt(start) != ' ')
            start--;
        return end - start;
    }
}