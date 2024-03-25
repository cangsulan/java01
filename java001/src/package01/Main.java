package package01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("请输入两个整数：");
//        Scanner sc = new Scanner(System.in);
//        int i=sc.nextInt();
//        int t=sc.nextInt();
//        System.out.println(i+t);
//        System.out.println("123"+123);
//        int[] a = new int[2];
//        a[0] = 1;
//        a[1] = 3;
//        System.out.println(a[0]);
//        System.out.println(a[1]);
        String ps = "adabbadada";
        getNext1(ps);
        getNext2(ps);
    }
    public static int[] getNext1(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                next[++j] = ++k;

            } else {

                k = next[k];

            }

        }
        for (int i = 0; i < next.length; i++) {
            System.out.printf("%d ",next[i]);
        }
        System.out.println();
        return next;

    }
    public static int[] getNext2(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过

                    next[j] = next[k];

                } else {

                    next[j] = k;

                }

            } else {

                k = next[k];

            }

        }
        for (int i = 0; i < next.length; i++) {
            System.out.printf("%d ",next[i]);
        }
        System.out.println();
        return next;

    }
}