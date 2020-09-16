package com.hjb.coder;

import com.hjb.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncode extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {

        out.writeInt(msg.getVersionId());
        out.writeInt(msg.getMessageType());
        out.writeInt(msg.getContent().getBytes().length);
        out.writeInt(msg.getExtField());
        out.writeBytes(msg.getUuId().getBytes());

        out.writeBytes(msg.getContent().getBytes());
    }
}
