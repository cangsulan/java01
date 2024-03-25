package sf001;

import java.util.Arrays;

public class two_find1 {
    public static int search1(int[] a, int target) {
        //二分查找的基础写法：
        int left = 0;
        int right = a.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) >>> 1;//使用无符号右移，代替/2，防止负数出现！！
            if (target < a[mid]) {
                right = mid - 1;
            } else if (target > a[mid]) {
                left = mid + 1;
            } else if (target == a[mid]) {
                return mid;
            }
        }
        return -1;
    }

    public static int search2(int[] a, int target) {
        //二分查找的改动版：
        //更加推荐这种版本，因为原来的版本可能会出现死循环。。
        int left = 0;
        int right = a.length; //第1处改动
        int mid;
        while (left < right) {//第2处改动
            mid = (left + right) >>> 1;//使用无符号右移，代替/2，防止负数出现！！
            if (target < a[mid]) {
                right = mid;//第3处改动
            } else if (target > a[mid]) {
                left = mid + 1;
            } else if (target == a[mid]) {
                return mid;
            }
        }
        return -1;
    }

    public static int search3(int[] a, int target) {
        //二分查找的平衡版：
        int left = 0;
        int right = a.length;
        int mid;
        while (1 < right - left) {
            mid = (left + right) >>> 1;//使用无符号右移，代替/2，防止负数出现！！
            if (target < a[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (a[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 4, 2, 3, 5, 7, 6, 9, 8};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(search3(a, 6));
    }
}
