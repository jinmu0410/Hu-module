package com.hjb.server;

import com.alibaba.fastjson.JSONObject;
import com.hjb.model.Message;
import com.hjb.model.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHandler extends SimpleChannelInboundHandler<Message>{

    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    private HashMap<String,Class<?>> hashMap = new HashMap<>();

    private static ConcurrentHashMap<String,Object> registerMap = new ConcurrentHashMap<>();

    private static List<String> serviceList = new ArrayList<>();

    public MessageHandler(){

        findService("com.hjb.service");

        doRegister();
    }

    private void doRegister() {

        if(serviceList ==null || serviceList.size() == 0){
            System.out.println("no service register");
            return;
        }
        serviceList.forEach(e->{
            try {
                Class<?> aClass = Class.forName(e);
                Class<?> t = aClass.getInterfaces()[0];
                registerMap.put(t.getName(),aClass.newInstance());
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            }
        });
    }

    private void findService(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            //如果是一个文件夹，继续递归
            if(file.isDirectory()){
                findService(packageName + "." + file.getName());
            }else{
                serviceList.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {

        System.out.println("服务端收到消息" + message);

        RpcRequest request = JSONObject.parseObject(message.getContent(), RpcRequest.class);

        Object object = registerMap.get(request.getClassName());
        if(object == null){
            System.out.println("找不到服务");
            return;
        }
        Class<?> aClass = object.getClass();
        Method method = aClass.getMethod(request.getMethodName(), request.getParamsTypes());
        Object result = null;
        try {
            result = method.invoke(aClass.newInstance(),request.getParams());
            System.out.println("result = " + result);
            Message heart_message = new Message();
            heart_message.setVersionId(001);
            heart_message.setExtField(001);
            heart_message.setMessageType(1);
            heart_message.setUuId(UUID.randomUUID().toString());
            heart_message.setLength(result.toString().length());
            heart_message.setContent(result.toString());
            ctx.writeAndFlush(heart_message);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        if(!group.contains(ctx.channel())){
            group.add(ctx.channel());
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(group.contains(ctx)){
            group.remove(ctx.channel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端发生异常" + ctx.channel() + "异常信息：" + cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
