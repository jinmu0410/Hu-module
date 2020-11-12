package com.hjb.builder;

public class Test {
    public static void main(String[] args) {
        User user = User.builder()
                .address("杭州西湖")
                .age(10)
                .name("张三")
                .build();
        System.out.println(user);

        Person person = Person.builder()
                .age(1)
                .name("zhs")
                .build();
        System.out.println(person);
    }
}
