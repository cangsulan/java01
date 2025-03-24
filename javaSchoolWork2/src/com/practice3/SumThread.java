package com.practice3;

public class SumThread extends Thread{
    int begin;
    int end;
    int sum=0;
    public SumThread(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    public void run(){
        //计算这个线程负责的相加和
        for(int i=begin;i<=end;i++){
            sum+=i;
        }
    }
}
