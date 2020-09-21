package com.hjb.client;

import com.alibaba.fastjson.JSON;
import com.hjb.client.handler.ClientHeartHandler;
import com.hjb.client.handler.MessageClientHandler;
import com.hjb.coder.MessageDecode;
import com.hjb.coder.MessageEncode;
import com.hjb.handler.IMIdleStateHandler;
import com.hjb.model.Message;
import com.hjb.model.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static String HOST="localhost";
    private static int PORT = 8888;

    public void start(Bootstrap bootstrap, NioEventLoopGroup work){
        bootstrap = new Bootstrap();
        bootstrap.group(work)
                 .channel(NioSocketChannel.class)
                 //设置TCP协议的属性
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .option(ChannelOption.TCP_NODELAY, true)
                 .option(ChannelOption.SO_TIMEOUT, 5000)
                 .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline cp = ch.pipeline();
                        //空闲检测
                        cp.addLast(new IMIdleStateHandler());
                        cp.addLast(new MessageEncode());
                        cp.addLast(new MessageDecode());
                        cp.addLast(new MessageClientHandler());
                        cp.addLast(new ClientHeartHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(NettyClient.HOST,NettyClient.PORT).sync();
            if(future.isSuccess()){
                System.out.println("客户端连接成功");
                User user = new User();
                user.setName("张三");
                user.setPassword("abc123");
                Message message = new Message();
                message.setVersionId(001);
                message.setExtField(002);
                message.setUuId(UUID.randomUUID().toString());
                message.setMessageType(1);
                message.setLength(JSON.toJSONString(user).length());
                message.setContent(JSON.toJSONString(user));
                Channel channel = future.channel();

                channel.writeAndFlush(message);
                future.channel().closeFuture().sync();
            }else{
                System.out.println("客户端连接失败，尝试重连!");
                future.channel().eventLoop().schedule(new Runnable() {
                    @Override
                    public void run() {
                        start(new Bootstrap(),work);
                    }
                },3,TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            work.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup work = new NioEventLoopGroup();
        client.start(bootstrap,work);
    }
}
