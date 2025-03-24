package com.practice5;

public class Warehouse {
    private int currentCapacity;
    private final int maxCapacity;
    private int id;

    public Warehouse(int maxCapacity,int id) {
        this.currentCapacity = 0;
        this.maxCapacity = maxCapacity;
        this.id=id;
    }

    public synchronized void add(int amount) throws InterruptedException {
        while (currentCapacity + amount > maxCapacity) {
            wait();
        }
        currentCapacity += amount;
        System.out.println(this.id+" 号 仓库 增加了 " + amount + " 个货物，目前共有 " + currentCapacity+" 个货物");
        notifyAll();
    }

    public synchronized void remove(int amount) throws InterruptedException {
        while (currentCapacity - amount < 0) {
            wait();
        }
        currentCapacity -= amount;
        System.out.println(this.id+" 号 仓库 减少了 " + amount + " 个货物，目前共有 " + currentCapacity+" 个货物");
        notifyAll();
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
}
