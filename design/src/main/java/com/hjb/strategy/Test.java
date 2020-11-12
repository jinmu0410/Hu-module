package com.hjb.strategy;

/**
 * 策略模式
 */
public class Test {

    private Strategy strategy;

    public Test(Strategy strategy){
        this.strategy = strategy;
    }

    public void work(){
        strategy.handle();
    }

    public static void main(String[] args) {
        Test test = new Test(new AStrategy());
        test.work();
    }
}
