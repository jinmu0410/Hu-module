package com.hjb.server;


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

        System.out.println("服务端收到消息"+ message);

        Message message1 = new Message();
        User user = new User();
        user.setName("李四");
        user.setPassword("asdfgh123");
        message1.setVersionId(2);
        message1.setMessageType(2);
        message1.setLength(10);
        message1.setSessionId("bbbb");
        message1.setContent(JSON.toJSONString(user));

        ctx.writeAndFlush(message);
    }
}
