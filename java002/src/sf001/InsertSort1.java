package sf001;

import java.util.Arrays;

public class InsertSort1 {
    public static void sort(int[] a,int low){
        //递归实现插入排序：
        if(low==a.length){
            return;
        }
        int t=a[low];
        int i=low-1;
        while (i>=0&&a[i]>t){
            a[i+1]=a[i];
            i--;
        }
        a[i+1]=t;
        sort(a,low+1);
    }
    public static void main(String[] args) {
        int[] a = {0, 1, 4, 2, 3, 5, 7, 6, 9, 8};
        System.out.println(Arrays.toString(a));
        sort(a, 1);
        System.out.println(Arrays.toString(a));
    }
}
