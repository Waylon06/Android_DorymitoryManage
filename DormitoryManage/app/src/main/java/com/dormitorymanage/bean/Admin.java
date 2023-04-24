package com.dormitorymanage.bean;

public class Admin {
    private Integer id;
    private String account;
    private String password;
    private Long  phone;
    private Integer manage;

    public Admin(Integer id, String account, String password, Long phone, Integer manage) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.manage = manage;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", manage=" + manage +
                '}';
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getManage() {
        return manage;
    }

    public void setManage(Integer manage) {
        this.manage = manage;
    }
}
