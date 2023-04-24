package com.dormitorymanage.bean;

public class User {
    private String username;
    private String password;
    private Integer id;
    private Integer power;

    public User(String username, String password, Integer id, Integer power) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.power = power;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
