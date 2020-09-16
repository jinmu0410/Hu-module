package com.hjb.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


public class ServerHeartHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        IdleStateEvent event = (IdleStateEvent) evt;

        if(event.state() == IdleState.READER_IDLE){
            System.out.println("read idel");
            //超时没有读取到客服端发来的消息，因为客服端也做了心跳检查，可以认为客户端掉线，关闭通道
            ctx.channel().close();
        }else if(event.state() == IdleState.WRITER_IDLE){
            System.out.println("writer idel");
        }else if(event.state() == IdleState.ALL_IDLE){
            System.out.println("all idel");
        }
        super.userEventTriggered(ctx, evt);
    }
}
