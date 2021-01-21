package com.hjb.factory;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(new HighSchool());
        student.work();
    }
}
