package com.hjb.template;

public class OracleTemplate extends dbTemplate{
    @Override
    public void connect() {
        System.out.println("oracle 连接");
    }

    @Override
    public void destroy() {
        System.out.println("oracle 关闭");
    }
}
