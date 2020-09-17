package com.hjb.coder;

import com.hjb.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义解码
 */
public class MessageDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int versionId = in.readInt();
        int messageType = in.readInt();
        int length = in.readInt();
        int extField = in.readInt();

        //uuid占36个字节
        byte[] uuid = new byte[36];
        in.readBytes(uuid);

        byte[] data = new byte[length];
        in.readBytes(data);

        Message message = new Message();
        message.setVersionId(versionId);
        message.setMessageType(messageType);
        message.setContent(new String(data));
        message.setExtField(extField);
        message.setUuId(new String(uuid));
        message.setLength(length);

        out.add(message);
    }
}
