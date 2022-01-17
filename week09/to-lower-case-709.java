class Solution {
    public String toLowerCase(String s) {
        // 核心就是转换成ASCII
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                // 大写与小写字母的差距
                sb.append((char) (c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}