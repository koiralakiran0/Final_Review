package com.example.mshehab.firebasedemo;

/**
 * Created by mshehab on 11/20/17.
 */

public class Person {
    String name, email, phone, dept;
    int picID;
    public String key;

    public Person() {
    }

    public Person(String name, String email, String phone, String dept) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDept() {
        return dept;
    }
}
