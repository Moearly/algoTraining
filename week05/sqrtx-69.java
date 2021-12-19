class Solution {
    public int mySqrt(int x) {
        //核心就是找 ans*ans <= x
        int left = 1, right = x;
        while(left < right) {
            //中间条件
            int mid = (left + right + 1) >> 1;
            //核心注意 越界
            if( mid <= x / mid) {
                //向右
                left = mid;
            }else {
                //向左
                right = mid - 1;
            }
        }
        return right;
    }
}