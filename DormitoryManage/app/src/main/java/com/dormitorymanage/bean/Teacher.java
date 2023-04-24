package com.dormitorymanage.bean;

public class Teacher {
    private Integer id;
    private String account;
    private String password;
    private String classes;
    private Integer phone;

    public Teacher() {
    }

    public Teacher(Integer id, String account, String password, String classes, Integer phone) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.classes = classes;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", classes='" + classes + '\'' +
                ", phone=" + phone +
                '}';
    }
}
