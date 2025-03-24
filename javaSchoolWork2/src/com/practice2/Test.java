package com.practice2;

import java.io.IOException;

//Java作业二，第 2 题
//2.设计一个异常处理，如果程序出错（或者说捕获到任何异常）就重新启动整个程序。
public class Test {
    public static void main(String[] args) throws IOException {
        for(;true;){
            try{
                for(int i=10;i>=-5;i--){
                    System.out.println(1/i);
                }
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
