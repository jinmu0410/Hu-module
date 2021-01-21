package com.hjb.server;

import com.hjb.coder.MessageDecode;
import com.hjb.coder.MessageEncode;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class NettyServer {

    @PostConstruct
    private void init(){
        start();
    }

    private void start() {
        System.out.println("服务端启动。。。。。");
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline cp = ch.pipeline();
                        cp.addLast(new MessageEncode());
                        cp.addLast(new MessageDecode());
                        cp.addLast(new MessageHandler());

                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.hjb.service.UserServiceImpl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {

            System.out.println("a="+anInterface.getName());
        }
    }
}
