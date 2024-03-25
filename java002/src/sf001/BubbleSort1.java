package sf001;

import java.util.Arrays;

public class BubbleSort1 {
    public static void bubblesort(int[] a, int end) {
        //递归实现冒泡排序：
        if (end == 0) {
            return;
        }
        for (int i = 0; i < end; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        bubblesort(a, end - 1);
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 4, 2, 3, 5, 7, 6, 9, 8};
        System.out.println(Arrays.toString(a));
        bubblesort(a, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
