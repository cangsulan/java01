package leetcode;

public class leetcode_69 {
    public int mySqrt(int x) {
        return binarySearch(x);
    }

    // 使用二分查找来一个一个找出来，
    public int binarySearch(int x) {
        long mid;
        long left = 0;
        long right = x;
        while (left <= right) {
            mid = left + ((right - left) >>> 1);
            if (mid * mid < x) {
                left = mid + 1;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else {
                return (int) mid;
            }
        }
        return (int) right;
    }
}
