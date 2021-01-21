package com.hjb.demo.thread;

/**
 * 循环打印ABAB.....
 * synchronized + wait+ notify
 */
public class Thread_2 {

    public static void main(String[] args) {
        Object object = new Object();
        new Thread(()->{
            synchronized (object){
                while (true){
                    object.notify();
                    System.out.println("A");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(()->{
            synchronized (object){
                while (true){
                    object.notify();
                    System.out.println("B");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
