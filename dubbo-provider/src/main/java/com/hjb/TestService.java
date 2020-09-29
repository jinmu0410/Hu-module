package com.hjb;

import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface TestService {

    String hello();
}
