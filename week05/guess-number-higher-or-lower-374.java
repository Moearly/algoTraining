/**
 * Forward declaration of guess API.
 * 
 * @param num your guess
 * @return -1 if num is lower than the guess number
 *         1 if num is higher than the guess number
 *         otherwise return 0
 *         int guess(int num);
 */

public class Solution extends GuessGame {
    /**
     * 典型二分，核心注意越界
     */
    public int guessNumber(int n) {
        // 二分模板
        int left = 1, right = n;
        while (left < right) {
            //注意 越界
            int mid = left + ((right - left) >> 1);
            if (guess(mid) <= 0) {
                // -1和0: 大于等于要猜的数字 -> 向左
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}