package com.hjb.nio.channel;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/usr/local/self/Hu-module/nio/src/main/java/com/hjb/nio/channel/test");
        boolean exists = file.exists();
        System.out.println(exists);
        Path path = file.toPath();
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE);
        fileChannel.position(5l);//写入的位置
        fileChannel.write(ByteBuffer.wrap("g".getBytes()));
//        byte[] bytes = new byte[(int) file.length()];
//        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
//        fileChannel.read(byteBuffer);
//        byte[] array = byteBuffer.array();
//
//        for (byte b : array) {
//            System.out.println((char)b);
//        }

        //fileChannel.write(ByteBuffer.wrap(new byte[]{1,5}));
        fileChannel.close();
    }
}
