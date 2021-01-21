package com.hjb.model;

import lombok.Data;

@Data
public class RpcRequest {

    private String className;

    private String methodName;

    private Class<?>[] paramsTypes;

    private Object[] params;
}
