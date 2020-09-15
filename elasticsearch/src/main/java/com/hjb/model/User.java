package com.hjb.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String password;

    private String address;

    private LocalDateTime time;

    private String phoneNumber;
}
