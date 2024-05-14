package OJ_work2_1;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String miao = sc.next();
            String wo = sc.next();
            map.put(wo, miao);
        }
        for(String str=sc.next();!str.equals("dog");str=sc.next()){
            if(map.containsKey(str)){
                System.out.println(map.get(str));
            }else{
                System.out.println("dog");
            }
        }
    }
}
