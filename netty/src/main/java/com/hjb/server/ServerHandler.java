package com.hjb.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends SimpleChannelInboundHandler{

    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收到消息>>>>>>" + msg);
        group.forEach(e->{
            if(ctx.channel() != e){
                e.writeAndFlush("[" + ctx.channel().remoteAddress() + "]::" +msg +"\n");
            }else {
                e.writeAndFlush("[自己]::" + msg +"\n");
            }
        });

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        group.forEach(e->{
            if(ctx.channel() != e){
                group.writeAndFlush( ctx.channel().remoteAddress() + "加入群聊" +"\n");
            }
        });
        group.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        group.remove(ctx.channel());
        group.forEach(e->{
            if(ctx.channel() != e){
                group.writeAndFlush( ctx.channel().remoteAddress() + "离开群聊" +"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
