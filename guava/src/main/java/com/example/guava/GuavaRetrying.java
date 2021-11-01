package com.example.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaRetrying {

    public void send(){
        throw new RuntimeException("测试异常");
        //System.out.println("send success");
    }


    @Slf4j
    static class MyRetryListener<Boolean> implements RetryListener{

        @Override public <V> void onRetry(Attempt<V> attempt) {
            log.info("重试次数:{}",attempt.getAttemptNumber());
            log.info("距离第一次重试的延迟:{}",attempt.getDelaySinceFirstAttempt());
           if(attempt.hasException()){
              log.error("异常原因:",attempt.getExceptionCause());
           }else{
              System.out.println("正常处理结果:{}"+ attempt.getResult());
           }

        }
    }

    public static void main(String[] args) {
        GuavaRetrying guavaRetrying = new GuavaRetrying();

        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                guavaRetrying.send();
                return true;
            }
        };

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfResult(Predicates.isNull())//callable 返回null时重试
            .retryIfExceptionOfType(IOException.class)
            .retryIfRuntimeException()
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))//重试3次
            .withRetryListener(new MyRetryListener<Boolean>())
            .build();
        try {
            retryer.call(callable);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }
}
