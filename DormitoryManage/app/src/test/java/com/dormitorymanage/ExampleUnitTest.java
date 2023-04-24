package com.dormitorymanage;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.dormitorymanage.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        Connection conn = null;

//        String sql = "SELECT * FROM token";
//        try {
//            conn = MySQLConnection.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
////                    ps.setString(1, "waylon");
////                    ps.setString(2, "123456");
//            ResultSet res = ps.executeQuery();
//            if (res.next()){
//                String name = res.getString("id");
//                String pwd = res.getString("value");
//                System.out.println(name + pwd);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";
        try {
            conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "waylon");
            ps.setString(2, "123456");
            ResultSet res = ps.executeQuery();
//            isRight = res.next();
            if (res.next()){
                String name = res.getString("username");
                String pwd = res.getString("password");
//                Log.e("测试", "name"+name+",password"+pwd );
                System.out.println(name + pwd);
            }else{
                Log.e("测试", "没有yonghu" );
            }

        } catch (SQLException e) {

        }
    }
}