package com.hjb.handel;

public enum MessageEnum {
    LOGIN(1,"登录"),
    LOGUT(2,"退出"),
    MESSAGE(3,"聊天"),
    HEART(4,"心跳")
    ;

    private Integer code;

    private String name;

    MessageEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
