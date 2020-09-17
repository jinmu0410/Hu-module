package com.hjb.server;


import com.alibaba.fastjson.JSON;
import com.hjb.model.Message;
import com.hjb.model.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.UUID;

public class MessageHandler extends SimpleChannelInboundHandler<Message>{

    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {

        if(message.getMessageType() != 4) {
            System.out.println("服务端收到消息"+ message);
            Message message1 = new Message();
            User user = new User();
            user.setName("李四");
            user.setPassword("asdfgh123");
            message1.setVersionId(001);
            message1.setExtField(001);
            message1.setUuId(UUID.randomUUID().toString());
            message1.setMessageType(2);
            message1.setLength(JSON.toJSONString(user).length());
            message1.setContent(JSON.toJSONString(user));

            ctx.writeAndFlush(message1);
        }else {
            System.out.println("服务端收到心跳消息"+ message);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        if(event.state() == IdleState.READER_IDLE){
            System.out.println("长时间没有收到客户端发送的消息");
            //超时没有读取到客服端发来的消息，因为客服端也做了心跳检查，可以认为客户端掉线，关闭通道
            ctx.channel().close();
        }else if(event.state() == IdleState.WRITER_IDLE){
            System.out.println("长时间没有向客户端发送的消息");
            //超过时间没有写操作，定时向服务端发送心跳包
            Message message = new Message();
            message.setVersionId(002);
            message.setExtField(002);
            message.setUuId(UUID.randomUUID().toString());
            message.setMessageType(4);
            message.setLength("服务端向客户端发送心跳检测".getBytes().length);
            message.setContent("服务端向客户端发送心跳检测");
            ctx.channel().writeAndFlush(message);
        }else if(event.state() == IdleState.ALL_IDLE){
            System.out.println("all idel");
        }
        super.userEventTriggered(ctx, evt);
    }
}
