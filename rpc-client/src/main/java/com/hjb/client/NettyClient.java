package com.hjb.client;

import com.hjb.coder.MessageDecode;
import com.hjb.coder.MessageEncode;
import com.hjb.model.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public Object start(Message message){

        MessageClientHandler messageClientHandler = new MessageClientHandler();
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline cp = ch.pipeline();
                        cp.addLast(new MessageEncode());
                        cp.addLast(new MessageDecode());
                        cp.addLast(messageClientHandler);
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
            future.channel().writeAndFlush(message).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
        }
        return messageClientHandler.getResponse();
    }
}
