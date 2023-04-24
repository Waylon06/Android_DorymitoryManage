package com.dormitorymanage.utils;

import android.util.Log;

import com.dormitorymanage.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DBUtil {
    private static final String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
    private static final String url = "jdbc:mysql://localhost:3306/android_dory_sys?useSSL=false"; //数据库地址
    private static final String user = "root";//用户名
    private static final String password = "123456";//密码

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver); //
            conn = DriverManager.getConnection(url,user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        if (Objects.isNull(conn)){
            Log.e("测试","数据库连接错误");
        }
        return conn;
    }

}
