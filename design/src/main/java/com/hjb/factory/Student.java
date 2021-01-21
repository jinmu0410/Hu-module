package com.hjb.factory;

public class Student {

    private School school;

    public Student(School school){
        this.school = school;
    }

    public void work(){
        school.study();
    }

}
