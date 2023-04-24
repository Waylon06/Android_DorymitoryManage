package com.dormitorymanage.bean;

public class Student {
    private Integer id;
    private String name;
    private String classes;
    private String building;
    private Integer room;
    private Integer back;
    private String manager;

    public Student(Integer id, String name, String classes, String building, Integer room, Integer back, String manager) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.building = building;
        this.room = room;
        this.back = back;
        this.manager = manager;
    }
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getBack() {
        return back;
    }

    public void setBack(Integer back) {
        this.back = back;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes='" + classes + '\'' +
                ", building='" + building + '\'' +
                ", room=" + room +
                ", back='" + back + '\'' +
                '}';
    }
}
