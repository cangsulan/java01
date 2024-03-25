package sf001;

import java.util.Arrays;

public class two_find2 {
    public static int search4(int[] a,int target,int left,int right){
        //二分查找的递归实现：
        if(right<left){
            return -1;
        }
        int mid=(left+right)>>>1;
        if(a[mid]<target){
            return search4(a,target,mid+1,right);
        }else if(a[mid]>target){
            return search4(a,target,left,mid-1);
        }else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 4, 2, 3, 5, 7, 6, 9, 8};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(search4(a, 10,0,a.length-1));
    }
}
