package OJ_work1_5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        int n=cin.nextInt();
        int m=cin.nextInt();
        //字母表
        int[] arr=new int[m];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=0;
        }
        //出现文章数
        int[] c=new int[m];
        for (int i : c) {
            i=0;
        }
        for (int i = 0; i < n; i++) {
            boolean[] is=new boolean[m];
            for (boolean b : is) {
                b=false;
            }
            int count=cin.nextInt();
            for (int j = 0; j < count; j++) {
                int t=cin.nextInt();
                arr[t-1]++;
                is[t-1]=true;
            }
            for (int i1 = 0; i1 < is.length; i1++) {
                if(is[i1]){
                    c[i1]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            System.out.println(c[i]+" "+arr[i]);
        }

    }
}
