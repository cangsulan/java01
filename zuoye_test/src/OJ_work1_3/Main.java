package OJ_work1_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        int T=cin.nextInt();
        if(T<1||T>10){
            System.out.println("Input data error");
            return;
        }
        for (int i = 0; i < T; i++) {
            int A= 0;
            try {
                A = cin.nextInt();
            } catch (Exception e) {
                System.out.println("Input data error");
                return;
            }
            int B= 0;
            try {
                B = cin.nextInt();
            } catch (Exception e) {
                System.out.println("Input data error");
                return;
            }
            if (A < 0 || A > 1018 || B < 0 || B > 1018) {
                System.out.println("Input data error");
                return;
            }
            if (Math.abs(A - B) >= 2) {
                System.out.println("GG");
            } else if (A==0&&B==0){
                System.out.println("GG");
            } else{
                System.out.println("MM");
            }
        }
    }
}
