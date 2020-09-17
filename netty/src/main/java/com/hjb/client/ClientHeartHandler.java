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
        System.out.println("心跳消息"+ msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        IdleStateEvent event = (IdleStateEvent) evt;

        if(event.state() == IdleState.READER_IDLE){
            System.out.println("长时间没有收到客户端发送的消息");
        }else if(event.state() == IdleState.WRITER_IDLE){
            System.out.println("长时间没有向服务端发送的消息");
            //超过时间没有写操作，定时向服务端发送心跳包
            Message message = new Message();
            message.setVersionId(002);
            message.setExtField(002);
            message.setUuId(UUID.randomUUID().toString());
            message.setMessageType(4);
            message.setLength("客户端向服务端发送心跳检测".getBytes().length);
            message.setContent("客户端向服务端发送心跳检测");
            ctx.channel().writeAndFlush(message);
        }else if(event.state() == IdleState.ALL_IDLE){
            System.out.println("all idel");
        }
        super.userEventTriggered(ctx, evt);
    }
}
