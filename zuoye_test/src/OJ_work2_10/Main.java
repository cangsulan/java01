package OJ_work2_10;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int countNeed=(n+1)/2;
        for (int i = 0; i < n; i++) {
            arr[i]=(arr[i]+1)/2;
        }
        Arrays.sort(arr);
        int result=0;
        for (int i = 0; i < countNeed; i++) {
            result+=arr[i];
        }
        System.out.println(result);
    }
}
