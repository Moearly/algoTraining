class Solution {
    public double myPow(double x, int n) {
        // 递归终止条件
        if (n == 0)
            return 1; // 偶数出口
        if (n == 1)
            return x; // 奇数出口
        if (n == -1)
            return 1 / x; // 倒转

        // 递：分拆小问题（要么奇数，要么偶数，每次只会走一个，另外一个是1不影响）
        double d1 = myPow(x, n % 2); // 偶数，到底会返回 1
        double d2 = myPow(x, n / 2); // 奇数，到底会返回 x

        // 归并
        // 奇数 = x(奇数) * x*x(偶数)
        d2 *= d2 * d1;
        return d2;
    }
}