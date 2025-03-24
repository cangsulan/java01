package com.practice5;

import java.util.concurrent.BlockingQueue;

public class FetchThread extends Thread{
    private final Warehouse[] warehouses;
    private final BlockingQueue<int[]> queue;
    private final int threadId;

    public FetchThread(Warehouse[] warehouses, BlockingQueue<int[]> queue, int threadId) {
        this.warehouses = warehouses;
        this.queue = queue;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int[] task = queue.take();
                if (task[0] == threadId) {
                    int amount = task[1];
                    this.getMax(this.warehouses).remove(amount);
                    System.out.println("Fetch线程" + threadId + " 拿走了 " + amount + " 个货物");
                } else {
                    queue.put(task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    public Warehouse getMax(Warehouse[] warehouses){
        int index=0;
        int max=0;
        for (int i = 0; i < warehouses.length; i++) {
            if(warehouses[i].getCurrentCapacity()>max){
                max=warehouses[i].getCurrentCapacity();
                index=i;
            }
        }
        return warehouses[index];
    }
}
