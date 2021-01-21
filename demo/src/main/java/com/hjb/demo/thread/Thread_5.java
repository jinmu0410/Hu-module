package com.hjb.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环打印AB
 * AtomicInteger
 */
public class Thread_5 {

    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(0);

        new Thread(()->{
            while (true) {
                if (num.get() % 2 == 0) {
                    System.out.println("A");
                    num.set(num.get() + 1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                if (num.get() % 2 == 1) {
                    System.out.println("B");
                    num.set(num.get() + 1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
