package com.hjb.demo.thread;


/**
 * 1.自定义中断标识位
 * 2.isInterrupted()判断线程是否中断，interrupt()把线程标识位设置中断
 */
public class Thread_1 extends Thread{

    public static void main(String[] args) throws InterruptedException {
         Thread_1 thread1 = new Thread_1();
         thread1.start();
         Thread.sleep(5000);
         thread1.interrupt();
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            System.out.println("线程一直在运行------");
        }
        System.out.println("线程中断停止-------");
    }
}
