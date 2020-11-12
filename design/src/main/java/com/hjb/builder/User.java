package com.hjb.builder;

public class User {

    private String name;

    private String address;

    private int age;

    User(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    public static class UserBuilder{
        private String name;

        private String address;

        private int age;

        public User.UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public User.UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public  User.UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        public User build() {
            return new User(this.name, this.address, this.age);
        }
    }
}
