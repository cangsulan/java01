package OJ_work2_6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //模拟堆栈
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        MyStack stack = new MyStack();
        for (int i = 0; i < n; i++) {
            stack.push(sc.next());
        }
        MyStack stack2 = new MyStack();
        //接下来有2次操作：
        sc.nextLine();//读掉上一行最后的回车
        String[] allLines = new String[2];
        allLines[0]=sc.nextLine();
        allLines[1]=sc.nextLine();
        for (int i = 0; i < 2; i++) {
            String[] str = allLines[i].split(" ");
            if(str[0].equals("push")){
                for (int j = 1; j < str.length; j++) {
                    stack.push(str[j]);
                }
            }else if(str[0].equals("pop")){
                for (int j = 0; j < Integer.parseInt(str[1]); j++) {
                    String temp = stack.pop();
                    if(temp==null){
                    }else{
                        stack2.push(temp);
                    }

                }
            }
        }
        System.out.print("len = "+stack.list.size());
        if(stack.list.size() == 0){
        }else{
            System.out.print(", data =");
            for(int i=0;i<stack.list.size();i++){
                System.out.print(" "+stack.list.get(i));
            }
        }
        System.out.println();

        System.out.print("len = "+stack2.list.size());
        if(stack2.list.size() == 0){
        }else{
            System.out.print(", data =");
            for(int i=0;i<stack2.list.size();i++){
                System.out.print(" "+stack2.list.get(i));
            }
        }
        System.out.println();
    }
}
