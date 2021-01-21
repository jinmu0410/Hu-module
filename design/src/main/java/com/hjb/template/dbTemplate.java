package com.hjb.template;

/**
 * 模板方法
 * 通用的放到统一的父类中，抽象的方法让子类来继承实现。
 */
public abstract class dbTemplate {

    public abstract void connect();

    public void handler(){
        System.out.println("查询数据库");
    }

    public abstract void destroy();

}
