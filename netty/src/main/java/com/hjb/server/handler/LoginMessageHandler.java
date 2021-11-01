package com.hjb.server.handler;

import com.hjb.model.LoginMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginMessageHandler extends SimpleChannelInboundHandler<LoginMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginMessage loginMessage) throws Exception {
        System.out.println("服务端收到客户的登陆请求");

    }
}
