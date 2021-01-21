package com.hjb.demo.thread;

import java.util.concurrent.Semaphore;

/**
 * 循环打印ABC
 * 信号量Semaphore
 */
public class Thread_4 {

    public static void main(String[] args) {
        Semaphore sa = new Semaphore(1);
        Semaphore sb = new Semaphore(0);
        Semaphore sc = new Semaphore(0);

        new Thread(()->{
            while (true){
                try {
                    sa.acquire();
                    System.out.println("A");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sb.release();
            }
        }).start();

        new Thread(()->{
            while (true){
                try {
                    sb.acquire();
                    System.out.println("B");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sc.release();
            }
        }).start();

        new Thread(()->{
            while (true){
                try {
                    sc.acquire();
                    System.out.println("C");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sa.release();
            }
        }).start();
    }
}
