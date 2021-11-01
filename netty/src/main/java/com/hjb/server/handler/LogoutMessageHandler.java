package com.hjb.server.handler;

import com.hjb.model.LogoutMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutMessageHandler extends SimpleChannelInboundHandler<LogoutMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutMessage logoutMessage) throws Exception {
        System.out.println("服务端收到客户端的下线请求");
    }
}
