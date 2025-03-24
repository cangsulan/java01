package com.practice3;
//Java作业二 第 3 题
//3.编写10个线程，第一个线程从1加到10，第二个线程从11加20…第十个线程从91加到100，最后再把10个线程结果相加。
public class Test {
    public static void main(String[] args) throws InterruptedException {
        SumThread[] threads = new SumThread[10];
        int result=0;
        for(int i=0;i<10;i++){
            threads[i]=new SumThread(i*10+1,(i+1)*10);
            threads[i].start();
        }
        for(int i=0;i<10;i++){
            threads[i].join();//等待这个线程结束
            result+=threads[i].sum;
        }
        System.out.println(result);
    }
}
