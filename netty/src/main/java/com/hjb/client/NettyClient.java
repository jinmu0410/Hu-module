package com.hjb.client;

import com.alibaba.fastjson.JSON;
import com.hjb.coder.MessageDecode;
import com.hjb.coder.MessageEncode;
import com.hjb.model.Message;
import com.hjb.model.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

public class NettyClient {

    public void start(){
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline cp = ch.pipeline();
                       /*cp.addLast(new StringDecoder());
                         cp.addLast(new StringEncoder());
                         cp.addLast(new CilentHandler());*/
                        cp.addLast(new MessageEncode());
                        cp.addLast(new MessageDecode());

                        cp.addLast(new MeeageClientHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect("localhost",8888).sync();
           /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Channel channel = future.channel();
            while(true){
                channel.writeAndFlush(br.readLine()+"\n");
            }*/
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.start();
    }
}
