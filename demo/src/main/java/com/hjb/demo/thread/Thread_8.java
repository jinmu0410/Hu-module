package com.hjb.demo.thread;

import java.util.concurrent.Semaphore;

public class Thread_8 {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 0; i <10000 ; i++) {
                try{
                    semaphore.acquire();
                    new Thread(()->{
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread ---" + Thread.currentThread().getName());
                    }).start();
                }catch (Exception e){

                } finally {
                    semaphore.release();
                }

        }
    }

    /**
     * 同时只能有300并发请求接口
     */
    public void handle(){
        Semaphore semaphore = new Semaphore(300);
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println("处理业务逻辑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
