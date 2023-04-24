package com.dormitorymanage.dao;

import android.util.Log;

import com.dormitorymanage.bean.Student;
import com.dormitorymanage.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao {
    private boolean isSuccess;

    private List<Student> data = new ArrayList<>();

    private String tableItem;

    public boolean addStudent(String name, String manager, String classes, Integer room, String building) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    isSuccess = false;
                }else {
                    String sql = "INSERT INTO student(name, manager, classes, room, building) VALUES(?, ?, ?, ?, ?)";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2, manager);
                        ps.setString(3, classes);
                        ps.setInt(4, room);
                        ps.setString(5, building);
                        int res = ps.executeUpdate();
                        System.out.println( "所影响的记录行数：" + res );
                        if (res != 0) {
                            isSuccess = true;
                        }else {
                            isSuccess = false;
                        }
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        isSuccess = false;
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
        System.out.println(isSuccess);
        return isSuccess;
    }

    public List<Student> studentList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    data = null;
                }else {
                    String sql = "SELECT * FROM student";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ResultSet res = ps.executeQuery();

                        ResultSetMetaData metaData = res.getMetaData();
                        while (res.next()) {
//                            Map hashMap = new HashMap();
//                            for (int i = 1; i < metaData.getColumnCount(); i++) {
//                                hashMap.put(metaData.getColumnName(i),res.getObject(i));
//                            }
                            Student student = new Student();
                            student.setId(res.getInt(1));
                            student.setName(res.getString(2));
                            student.setClasses(res.getString(3));
                            student.setBuilding(res.getString(4));
                            student.setBack(res.getInt(5));
                            student.setRoom(res.getInt(6));
                            student.setManager(res.getString(7));
                            data.add(student);
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

    public boolean delStudent(Integer sid) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    isSuccess = false;
                }else {
                    String sql = "DELETE FROM STUDENT WHERE sid = ?";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, sid);
                        int i = ps.executeUpdate();
                        System.out.println(i);
                        if (i != 0) {
                            isSuccess = true;
                        }else {
                            isSuccess = false;
                        }
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        isSuccess = false;
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
        System.out.println(isSuccess);
        return isSuccess;
    }

    public boolean updateStudent(String name, Integer sid,String classes, Integer room, String building) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    isSuccess = false;
                }else {
                    String sql = "UPDATE student SET name = ?, classes = ?, room = ?, building = ? WHERE sid = ?";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2, classes);
                        ps.setInt(3, room);
                        ps.setString(4, building);
                        ps.setInt(5, sid);
                        int i = ps.executeUpdate();
                        if (i != 0) {
                            isSuccess = true;
                        }else {
                            isSuccess = false;
                        }
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        isSuccess = false;
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
        System.out.println(isSuccess);
        return isSuccess;
    }

    public boolean updateIsBack(Integer sid, Integer back) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    isSuccess = false;
                }else {
                    String sql = "UPDATE student SET back = ? WHERE sid = ?";
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, back);
                        ps.setInt(2, sid);
                        int i = ps.executeUpdate();
                        if (i != 0) {
                            isSuccess = true;
                        }else {
                            isSuccess = false;
                        }
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        isSuccess = false;
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
        return isSuccess;
    }

    public List<Student> selectStudentByRole(String role, String query) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    data = null;
                }else {
                    String sql;
                    if (role.equals("辅导员")) {
                        sql = "SELECT * FROM student WHERE manager = ?";
                    }else {
                        sql = "SELECT * FROM student WHERE building = ?";
                    }
                    try {
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, query);
                        ResultSet res = ps.executeQuery();
                        while (res.next()) {
                            Student student = new Student();
                            student.setId(res.getInt(1));
                            student.setName(res.getString(2));
                            student.setClasses(res.getString(3));
                            student.setBuilding(res.getString(4));
                            student.setBack(res.getInt(5));
                            student.setRoom(res.getInt(6));
                            student.setManager(res.getString(7));
                            data.add(student);
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

    public List<Student> selectStudentByClass(String manager, String classes,String role) {
        data.clear();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = DBUtil.getConnection();
                if (conn == null) {
                    data = null;
                }else {
                    try {
                    String sql = null;
                    PreparedStatement ps = null;
                    if (role.equals("辅导员")) {
                        tableItem = "classes";
                        sql = "SELECT * FROM student WHERE manager = ? AND "+tableItem+" = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, manager);
                        ps.setString(2, classes);
                    }else if (role.equals("宿管")) {
                        tableItem = "building";
                        sql = "SELECT * FROM student WHERE"+tableItem+" = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, manager);
                    }
                    Log.e("TAG", "run: "+classes );
                        ResultSet res = ps.executeQuery();
                        Log.e("TAG", "run: "+ ps.toString() );
                        while (res.next()) {
                            Student student = new Student();
                            student.setId(res.getInt(1));
                            student.setName(res.getString(2));
                            student.setClasses(res.getString(3));
                            student.setBuilding(res.getString(4));
                            student.setBack(res.getInt(5));
                            student.setRoom(res.getInt(6));
                            student.setManager(res.getString(7));
                            data.add(student);
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
