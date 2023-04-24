package com.dormitorymanage.dao;

import android.content.SharedPreferences;
import android.util.Log;

import com.dormitorymanage.utils.DBUtil;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private boolean isRight;
    private String roleTable;
    private String tableItem;
    private String []array;

    //    登录
    public boolean verifyUser(String account, String password, String role) {
        if (role.equals("辅导员")) {
            roleTable = "teacher";
        }else if(role.equals("宿管")) {
            roleTable = "dorymanage";
        }else if(role.equals("超管")) {
            roleTable = "admin";
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    isRight = false;
                } else {
                    // 查询 users 表中的所有数据
                    String sql = "SELECT * FROM "+roleTable+" WHERE account = ? and password = ?";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, account);
                        ps.setString(2, password);
                        ResultSet res = ps.executeQuery();
                        if (res.next()) {
                            String name = res.getString("account");
                            String pwd = res.getString("password");
                            isRight = true;
                            System.out.println(name + pwd);
                        } else {
                            Log.e("测试", "没有用户");
                        }
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        isRight = false;
                    }
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(isRight);
        return isRight;
    }

    //    注册
    public boolean register(String account, String password, String role, String manage, Integer phone) {
        if (role.equals("辅导员")) {
            roleTable = "teacher";
            tableItem = "classes";
        }else if(role.equals("宿管")) {
            roleTable = "dorymanage";
            tableItem = "building";
        }else if(role.equals("超管")) {
            roleTable = "admin";
            tableItem = "manage";
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    isRight = false;
                } else {
                    // 查询 users 表中的所有数据
                    String sql = "INSERT INTO "+roleTable+"(account, password, "+tableItem+", phone) VALUES(? , ?, ?, ?)";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, account);
                        ps.setString(2, password);
                        ps.setString(3,manage);
                        ps.setInt(4, phone);
                        int res = ps.executeUpdate();
                        System.out.println( "所影响的记录行数：" + res );
                        isRight = true;
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        isRight = false;
                    }
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(isRight);
        return isRight;
    }

    public String[] splitClass(String query , String role) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    array = null;
                }else {
                    if (role.equals("辅导员")) {
                        tableItem = "classes";
                        roleTable = "teacher";
                    }else if (role.equals("宿管")){
                        tableItem = "building";
                        roleTable = "dorymanage";
                    }
                    String sql = "SELECT "+tableItem+" FROM "+roleTable+" WHERE account = ?";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, query);
                        ResultSet res = ps.executeQuery();
                        System.out.println(ps.toString());
                        String classes = null;
                        while (res.next()) {
                            classes = res.getString(1);
                        }
                            array = classes.split(",");
                        System.out.println(array);
                    } catch (SQLException e) {
                        array = null;
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return array;
    }

    public String[] managerInfo(String role , String query) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    array = null;
                }else {
                    if (role.equals("辅导员")) {
                        roleTable = "teacher";
                    }else if(role.equals("宿管")) {
                        roleTable = "dorymanage";
                    }else if(role.equals("超管")) {
                        roleTable = "admin";
                    }
                    String sql = "SELECT * FROM "+roleTable+" WHERE account = ?";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, query);
                        ResultSet res = ps.executeQuery();
                        while (res.next()) {

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return array;
    }
}
