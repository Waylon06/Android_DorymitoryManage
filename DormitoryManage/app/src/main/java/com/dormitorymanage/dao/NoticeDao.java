package com.dormitorymanage.dao;

import com.dormitorymanage.bean.Notice;
import com.dormitorymanage.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {
    private List<Notice> data = new ArrayList<>();


    public List<Notice> noticeList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    data = null;
                }else {
                    String sql = "SELECT * FROM notice";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ResultSet res = ps.executeQuery();

                        while (res.next()){
                            Notice notice = new Notice();
                            notice.setId(res.getInt(1));
                            notice.setTitle(res.getString(2));
                            notice.setContent(res.getString(3));
                            data.add(notice);
                        }
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        data = null;
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
        return data;
    }
}
