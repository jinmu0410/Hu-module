package com.hjb.client;

import com.hjb.model.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MeeageClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
            if(message.getMessageType() == 4){
                System.out.println("客户端收到心跳消息" + message);
            }else {
                System.out.println("客户端收到消息" + message);
            }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
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
