package com.hjb.coder;

import com.hjb.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int versionId = in.readInt();
        int messageType = in.readInt();
        int length = in.readInt();

        byte[] sessionByte = new byte[36];
        in.readBytes(sessionByte);
        String sessionId = new String(sessionByte);

        // 读取消息内容
        //byte[] content = in.readBytes(in.readableBytes()).array();
        byte[] content = new byte[in.readableBytes()];
        String data = new String(content);

        Message message = new Message();
        message.setVersionId(versionId);
        message.setMessageType(messageType);
        message.setContent(data);
        message.setLength(length);
        message.setSessionId(sessionId);

        out.add(message);
    }
}
