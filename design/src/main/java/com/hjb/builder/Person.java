package com.hjb.builder;

import lombok.Builder;
import lombok.Data;

/**
 * lombok中的建造者模式
 */
@Data
@Builder
public class Person {

    private String name;

    private String password;

    private String address;

    private int age;
}
