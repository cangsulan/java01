package com.practice5;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//Java作业二，第 5 题
//5.有三个货物容量为20的仓库，现有两个fetch线程和一个save线程，
// 每次存取1-5个单位的仓库货物。save线程会选择货物最少的仓库存放货物，
// 如果仓库会溢出进入wait状态。fetch线程会选择货物最多的仓库取出货物，
// 如果仓库取空还不够会进入wait状态。如果符合条件有2-3个仓库库存相等则任选一个操作。
// 注意，这里绝对不能在程序中连续写：
//warehouse1.wait();
//warehouse2.wait();
//warehouse3.wait();
//大家自己分析原因，并想出解决办法。
//测试本程序需要编制fetch线程和save线程读取每次存取信息。
// 一行输入代表一次操作。
// 例如输入 1 3表示fetch1线程取3个单位货物，
// 2 3表示fetch2线程取3个单位货物，3 3表示save线程存储3个单位货物。
// 不考虑错误输入情况。每次成功存取成功后打印仓库当前库存及本次操作内容。
public class Test {
    public static void main(String[] args) {
        Warehouse[] warehouses = {
                new Warehouse(20,1),
                new Warehouse(20,2),
                new Warehouse(20,3)
        };

        BlockingQueue<int[]> queue = new LinkedBlockingQueue<>();

        FetchThread fetch1 = new FetchThread(warehouses, queue, 1);
        FetchThread fetch2 = new FetchThread(warehouses, queue, 2);
        SaveThread saveThread = new SaveThread(warehouses, queue);

        fetch1.start();
        fetch2.start();
        saveThread.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int id = scanner.nextInt();
            int amount = scanner.nextInt();
            try {
                queue.put(new int[]{id, amount});
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        //中断这些线程
        fetch1.interrupt();
        fetch2.interrupt();
        saveThread.interrupt();
    }
}
