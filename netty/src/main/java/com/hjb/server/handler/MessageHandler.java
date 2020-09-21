package com.hjb.server.handler;


import com.alibaba.fastjson.JSON;
import com.hjb.model.Message;
import com.hjb.model.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.UUID;

public class MessageHandler extends SimpleChannelInboundHandler<Message>{

    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {

            switch (message.getMessageType()){
                case 4:
                    System.out.println("服务端收到心跳消息" + message);
                    Message heart_message = new Message();
                    heart_message.setVersionId(002);
                    heart_message.setExtField(002);
                    heart_message.setUuId(UUID.randomUUID().toString());
                    heart_message.setMessageType(4);
                    heart_message.setLength("服务端向客户端发送心跳检测".getBytes().length);
                    heart_message.setContent("服务端向客户端发送心跳检测");
                    ctx.writeAndFlush(heart_message);
                    break;
                case 1:
                    System.out.println("服务端收到消息"+ message);
                    Message message1 = new Message();
                    User user = new User();
                    user.setName("李四");
                    user.setPassword("asdfgh123");
                    message1.setVersionId(001);
                    message1.setExtField(001);
                    message1.setUuId(UUID.randomUUID().toString());
                    message1.setMessageType(1);
                    message1.setLength(JSON.toJSONString(user).length());
                    message1.setContent(JSON.toJSONString(user));
                    ctx.writeAndFlush(message1);
                    break;
            }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        if(!group.contains(ctx.channel())){
            group.add(ctx.channel());
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(group.contains(ctx)){
            group.remove(ctx.channel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端发生异常" + ctx.channel() + "异常信息：" + cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
