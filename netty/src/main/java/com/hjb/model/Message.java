package com.hjb.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private int versionId;

    private int length;

    private String sessionId;

    private int messageType;

    private String content;
}
