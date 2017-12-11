package com.example.mshehab.inclass07;

/**
 * Created by mshehab on 12/8/17.
 */

public class Contact {
    String name, phone, email, dept;
    int imgId = -1;

    public Contact(String name, String phone, String email, String dept, int imgId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dept = dept;
        this.imgId = imgId;
    }

    public Contact() {
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}

