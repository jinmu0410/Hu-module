package com.hjb.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class CompletableFutureTest {

    @Test
    public void test(){
        System.out.println("开始执行");

        long start  = System.currentTimeMillis();
       CompletableFuture<String> completableFuture = CompletableFuture
               .supplyAsync(CompletableFutureTest::handle);

        long end = System.currentTimeMillis();
        System.out.println("花费时间=" + (end-start));
    }

    public static String handle(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
