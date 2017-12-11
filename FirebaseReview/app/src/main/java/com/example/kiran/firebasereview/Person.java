package com.example.kiran.firebasereview;

/**
 * Created by kiran on 12/10/17.
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
