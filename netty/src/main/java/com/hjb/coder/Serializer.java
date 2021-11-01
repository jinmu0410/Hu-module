package com.hjb.coder;

public interface Serializer {


    byte[] encode(Object msg);

    <T> T decode(byte[] buf,Class<T> type);
}
