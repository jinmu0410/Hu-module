package com.hjb;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class Main {
    public static void main(String[] args) {
        ExtensionLoader<TestService> loader = ExtensionLoader.getExtensionLoader(TestService.class);

        TestService z = loader.getExtension("Z");
        System.out.println(z.hello());
    }
}
