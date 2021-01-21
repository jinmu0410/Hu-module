package com.hjb.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ComponentScan
@EnableAsync
public class AsycTest {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolExecutor  pool = new ThreadPoolExecutor(3,10,6000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        return pool;
    }
}
