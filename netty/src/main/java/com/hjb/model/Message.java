package com.hjb.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    //版本号
    private int versionId;

    //拓展字段固定001
    private int extField;

    //消息体长度
    private int length;

    //消息类型，枚举
    private int messageType;

    //uuid
    private String uuId;

    //消息体
    private String content;
}
