package ru.sibsutis;

class Shop {
    private int product = 0;
    public synchronized void get(Integer numBought) {
        long startTime = 0L;
        long finishTime;

        while (product < numBought) {
            startTime = System.nanoTime();
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        finishTime = System.nanoTime();
        product -= numBought;
        if (startTime != 0L) {
            System.out.println("Consumer has been waiting for:" + ((double)finishTime - startTime) / 1000000000);
        }
        System.out.println("Consumer bought:" + numBought);
        System.out.println("Balance of goods:" + product);
        notify();
    }

    public synchronized void put() {
        while (product >= 5000) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        product += 1000;
        System.out.println("Factory has made goods");
        System.out.println("Balance of goods:" + product);
        notify();
    }
}

class Consumer implements Runnable {
    Shop shop;
    Integer numToBuy;
    Consumer(Shop shop, Integer numToBuy) {
        this.shop = shop;
        this.numToBuy = numToBuy;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            shop.get(numToBuy);
        }
    }
}

class Factory implements Runnable {
    Shop shop;
    Factory(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            shop.put();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int numToBuy = 25;
        int numOfConsumers = 100;
        Shop shop = new Shop();
        new Thread(new Factory(shop)).start();
        for (int i = 0; i < numOfConsumers; i++) {
            new Thread(new Consumer(shop, numToBuy)).start();
        }

    }
}