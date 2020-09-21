package com.hjb.client.handler;

import com.hjb.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class ClientHeartHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 10;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                Message message = new Message();
                message.setVersionId(002);
                message.setExtField(002);
                message.setUuId(UUID.randomUUID().toString());
                message.setMessageType(4);
                message.setLength("客户端向服务端发送心跳检测".getBytes().length);
                message.setContent("客户端向服务端发送心跳检测");
                ctx.channel().writeAndFlush(message);
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

}
