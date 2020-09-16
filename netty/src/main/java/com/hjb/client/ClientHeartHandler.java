package com.hjb.client;

import com.hjb.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.UUID;

public class ClientHeartHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;

            if(event.state() == IdleState.READER_IDLE) {
                System.out.println("read idle");

            } else if(event.state() == IdleState.WRITER_IDLE){
                System.out.println("write idle");
                //超过时间没有写操作，定时向服务端发送心跳包
                Message message = new Message();
                message.setVersionId(002);
                message.setExtField(002);
                message.setUuId(UUID.randomUUID().toString());
                message.setMessageType(4);
                message.setLength(0);
                message.setContent("");
                ctx.channel().writeAndFlush(message);
            } else if(event.state() == IdleState.ALL_IDLE) {
                System.out.println("all idle");
            }

        }
        super.userEventTriggered(ctx, evt);
    }
}
