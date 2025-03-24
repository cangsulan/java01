package com.practice5;

import java.util.concurrent.BlockingQueue;

public class SaveThread extends Thread{
    private final Warehouse[] warehouses;
    private final BlockingQueue<int[]> queue;

    public SaveThread(Warehouse[] warehouses, BlockingQueue<int[]> queue) {
        this.warehouses = warehouses;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int[] task = queue.take();
                if (task[0] == 3) {
                    int amount = task[1];
                    this.getMin(this.warehouses).add(amount);
                    System.out.println("Save线程 "+ "存入了 " + amount + " 个货物");
                } else {
                    queue.put(task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    public Warehouse getMin(Warehouse[] warehouses){
        int index=0;
        int min=999;
        for (int i = 0; i < warehouses.length; i++) {
            if(warehouses[i].getCurrentCapacity()<min){
                min=warehouses[i].getCurrentCapacity();
                index=i;
            }
        }
        return warehouses[index];
    }
}
