package com.hjb.template;

public class Main {
    public static void main(String[] args) {
        MysqlTemplate mysqlTemplate = new MysqlTemplate();
        mysqlTemplate.connect();
        mysqlTemplate.handler();
        mysqlTemplate.destroy();

        System.out.println("------------------");

        OracleTemplate oracleTemplate = new OracleTemplate();
        oracleTemplate.connect();
        oracleTemplate.handler();
        oracleTemplate.destroy();
    }
}
