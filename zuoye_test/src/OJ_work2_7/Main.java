package OJ_work2_7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //模拟队列
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        MyQueue queue = new MyQueue();
        for (int i = 0; i < n; i++) {
            queue.push(sc.next());
        }
        MyQueue queue2 = new MyQueue();
        //接下来有2次操作：
        sc.nextLine();//读掉上一行最后的回车
        String[] allLines = new String[2];
        allLines[0]=sc.nextLine();
        allLines[1]=sc.nextLine();
        for (int i = 0; i < 2; i++) {
            String[] str = allLines[i].split(" ");
            if(str[0].equals("in")){
                for (int j = 1; j < str.length; j++) {
                    queue.push(str[j]);
                }
            }else if(str[0].equals("out")){
                for (int j = 0; j < Integer.parseInt(str[1]); j++) {
                    String temp = queue.pop();
                    if(temp==null){
                    }else{
                        queue2.push(temp);
                    }

                }
            }
        }
        System.out.print("len = "+queue.list.size());
        if(queue.list.size() == 0){
        }else{
            System.out.print(", data =");
            for(int i=0;i<queue.list.size();i++){
                System.out.print(" "+queue.list.get(i));
            }
        }
        System.out.println();

        System.out.print("len = "+queue2.list.size());
        if(queue2.list.size() == 0){
        }else{
            System.out.print(", data =");
            for(int i=0;i<queue2.list.size();i++){
                System.out.print(" "+queue2.list.get(i));
            }
        }
        System.out.println();
    }
}
