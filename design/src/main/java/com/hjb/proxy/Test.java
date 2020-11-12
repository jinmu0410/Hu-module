package com.hjb.proxy;

public class Test {
    public static void main(String[] args) {

        JDKProxy jdkProxy = new JDKProxy();
        UserService userService = (UserService) jdkProxy.proxy(new UserServiceImpl());
        userService.work();

        CGLBProxy cglbProxy = new CGLBProxy();
        CgUser cgUser = (CgUser) cglbProxy.proxy(new CgUser());
        cgUser.work();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("zz");
        System.out.println(threadLocal.get());

    }
}
