package com.hjb.client;

import com.alibaba.fastjson.JSON;
import com.hjb.model.Message;
import com.hjb.model.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

public class RpcProxyFactory {

    public static <T> T create(Class<?> clazz){
        //clazz传进来本身就是interface
        MethodProxy proxy = new MethodProxy(clazz);
        Class<?> [] interfaces = clazz.isInterface() ?
                new Class[]{clazz} :
                clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(),interfaces,proxy);
        return result;
    }

    private static class MethodProxy implements InvocationHandler {
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果传进来是一个已实现的具体类（本次演示略过此逻辑)
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                //如果传进来的是一个接口（核心)
            } else {
                return rpcInvoke(proxy, method, args);
            }
            return null;
        }

        public Object rpcInvoke(Object proxy, Method method, Object[] args) {
            System.out.println("开始代理处理");

            RpcRequest request = new RpcRequest();
            request.setClassName(this.clazz.getName());
            request.setMethodName(method.getName());
            request.setParams(args);
            request.setParamsTypes(method.getParameterTypes());

            Message message = new Message();
            message.setVersionId(001);
            message.setExtField(002);
            message.setUuId(UUID.randomUUID().toString());
            message.setMessageType(1);
            message.setLength(JSON.toJSONString(request).length());
            message.setContent(JSON.toJSONString(request));


            NettyClient nettyClient = new NettyClient();
            Object result = nettyClient.start(message);
            System.out.println("hhhhh=" + result);

            return result;
        }

    }
}
