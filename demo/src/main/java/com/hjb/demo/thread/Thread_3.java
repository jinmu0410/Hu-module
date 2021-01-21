package com.hjb.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 循环打印AB
 */
public class Thread_3 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
         new Thread(()->{
           while (true){
               lock.lock();
               try{
                   System.out.println("A");
                   a.wait();
                   b.signal();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                   lock.unlock();
           }
        }).start();

        new Thread(()->{
            while (true) {
                lock.lock();
                try {
                    System.out.println("B");
                    a.signal();
                    b.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }).start();
    }
}
