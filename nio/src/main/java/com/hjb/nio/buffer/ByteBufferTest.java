package com.hjb.nio.buffer;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{1,2,4};

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.limit(2);
        int capacity = byteBuffer.capacity();
        System.out.println(capacity);
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
    }
}
