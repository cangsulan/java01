package com.practice1;

//Java作业二，第 1 题
//1.用户如何自定义异常类？编程实现一个用户自定义异常类并测试它。

public class Test {
    public static void main(String[] args) {
        try {
            throw new MyException("我的自定义异常类");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }
}
