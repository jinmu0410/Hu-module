package com.hjb.template;

public class MysqlTemplate extends dbTemplate{
    @Override
    public void connect() {
        System.out.println("mysql 连接");
    }

    @Override
    public void destroy() {
        System.out.println("mysql 关闭");
    }
}
