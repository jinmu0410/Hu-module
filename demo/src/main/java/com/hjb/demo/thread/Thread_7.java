package com.hjb.demo.thread;

import java.util.concurrent.Semaphore;

/**
 * 循环打印AB
 */
public class Thread_7 {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);
        Semaphore semaphore1 = new Semaphore(1);
        while (true){
            new Thread(()->{

                try {
                    semaphore.acquire();
                    System.out.println("A");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }).start();

            new Thread(()->{
                try {
                    semaphore1.acquire();
                    System.out.println("B");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore1.release();
                }
            }).start();
        }

    }
}
