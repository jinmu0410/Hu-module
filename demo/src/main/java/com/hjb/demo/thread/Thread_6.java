package com.hjb.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder与AtomicLong
 *
 * LongAdder在并发量比较大的情况下，操作数据的时候，相当于把这个数字分成了很多份数字，然后交给多个人去管
 * 控，每个管控者负责保证部分数字在多线程情况下操作的正确性。当多线程访问的时，通过hash算法映
 * 射到具体管控者去操作数据，最后再汇总所有的管控者的数据，得到最终结果。相当于降低了并发情况
 * 下锁的粒度，所以效率比较高
 *
 *
 */
public class Thread_6 {

   static LongAdder longAdder = new LongAdder();

   static AtomicLong atomicLong = new AtomicLong();

   static LongAccumulator accumulator = new LongAccumulator((x,y)->x+y,0l);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <10 ; i++) {
            //重置
            longAdder.reset();
            test();
        }
        System.out.println("-----------------------");
        for (int i = 0; i <10 ; i++) {
            //重置
            test1();
        }
        System.out.println("-----------------------");
        for (int i = 0; i < 10; i++) {

            accumulator.accumulate(2);
        }
        System.out.println(accumulator.get());
    }

    public static void test() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        CountDownLatch count = new CountDownLatch(50);
        for (int i = 0; i <50 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000000; j++) {
                    longAdder.increment();
                }
                count.countDown();
            }).start();
        }
        count.await();
        long t2 = System.currentTimeMillis();
        System.out.println("消耗时间：" + (t2-t1) +"----sum=" + longAdder.sum());
    }

    public static void test1() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        CountDownLatch count = new CountDownLatch(50);
        for (int i = 0; i <50 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000000; j++) {
                    atomicLong.incrementAndGet();
                }
                count.countDown();
            }).start();
        }
        count.await();
        long t2 = System.currentTimeMillis();
        System.out.println("消耗时间：" + (t2-t1) +"----sum=" + atomicLong.get());
    }
}
