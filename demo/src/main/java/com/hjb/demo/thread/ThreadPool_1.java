package com.hjb.demo.thread;

import java.util.concurrent.*;

public class ThreadPool_1 {
    public class Pool extends ThreadPoolExecutor{

        public Pool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(20, 50, 3000, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(100)
                , new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread("test_thread" + Thread.currentThread().getName());
            }
        },new ThreadPoolExecutor.CallerRunsPolicy());




        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                Executors.defaultThreadFactory(),
                (r, executors) -> {

                    System.out.println("无法处理的任务：" + r.toString());
                }) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(System.currentTimeMillis() + "," +
                        t.getName() + ",开始执行任务:" + r.toString());
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(System.currentTimeMillis() + "," +
                        Thread.currentThread().getName() + ",任务:" + r.toString() + "，执行完毕!");
            }
            @Override
            protected void terminated() {
                System.out.println(System.currentTimeMillis() + "," +
                        Thread.currentThread().getName() + "，关闭线程池!");
            }
        };

    }
}
