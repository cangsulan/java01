package OJ_work1_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Scanner cin=new Scanner(System.in);
        try {
            n=cin.nextInt();
        } catch (Exception e) {
            System.out.println("Input data error");
            return;
        }
        if(n<1||n>50){
            System.out.println("Input data error");
            return;
        }
        int[][] array=new int[n][n];
        int num=n-1;
        int mod=1;
        int i=0;
        int j=n-1;
        int count=1;
        int m=3;
        while(num>0){
            if(mod==1){
                for (int k = 0; k < num; k++) {
                    array[i][j]=count;
                    i++;
                    count++;
                }
                mod=2;
            } else if (mod==2) {
                for (int k = 0; k < num; k++) {
                    array[i][j]=count;
                    j--;
                    count++;
                }

                mod=3;
            } else if (mod==3) {
                for (int k = 0; k < num; k++) {
                    array[i][j]=count;
                    i--;
                    count++;
                }

                mod=4;
            }else if (mod==4){
                for (int k = 0; k < num-1; k++) {
                    array[i][j]=count;
                    j++;
                    count++;
                }

                mod=1;
                array[i][j]=count;
                count++;
                i++;
            }
            m--;
            if(mod==1){
                num=num-2;
            }
        }
        if(n%2!=0){
            int t=(n+1)/2-1;
            array[t][t]=n*n;
        }
        for (int i1 = 0; i1 < array.length; i1++) {
            System.out.print(array[i1][0]);
            for (int i2 = 1; i2 < array[i1].length; i2++) {
                System.out.print(" "+array[i1][i2]);
            }
            System.out.println();
        }
    }
}
