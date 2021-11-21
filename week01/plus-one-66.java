class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n - 1; i >= 0; i--) {
            //直接对当前位加一 后对10取模
            digits[i] = (digits[i] + 1) % 10;
            if(digits[i] != 0) {
                //表示相加后没有进位，直接返回
                return digits;
            }
            //存在进位，累加高进位
        }
        //到这里表示地位都累加完，存在更高进位，表示n个低位 都为 0
        digits = new int[n+1];
        //追加最高位 1
        digits[0] = 1;
        return digits;
    }
}