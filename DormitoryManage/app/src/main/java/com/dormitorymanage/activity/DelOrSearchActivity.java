package com.dormitorymanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.dormitorymanage.R;
import com.dormitorymanage.adapter.DoryStudentAdapter;
import com.dormitorymanage.bean.Student;
import com.dormitorymanage.dao.StudentDao;
import com.dormitorymanage.adapter.StudentListAdapter;
import com.dormitorymanage.dao.UserDao;

import java.util.List;

public class DelOrSearchActivity extends AppCompatActivity {
    private Intent intent;
    List<Student> data;

    private String selectInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_or_search);

//        数据
        SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        String query = userInfo.getString("query", null);
        String role = userInfo.getString("role", null);
        String suguan = userInfo.getString("suguan", null);
        System.out.println(role);
        Log.e("TAG", "onCreate: "+suguan);
        StudentDao studentDao = new StudentDao();
        if (role.equals("超管") || role == null) {
            data = studentDao.studentList();
        }else {
            data = studentDao.selectStudentByRole(role, query);
        }

        System.out.println(data);

        ListView listView = findViewById(R.id.stuInfo);
        listView.setAdapter(new StudentListAdapter(data, this));

//        下拉框
        UserDao userDao = new UserDao();
        String[] info;
        if (role.equals("宿管")){
            info = userDao.splitClass(suguan, role);
        }else {
            info = userDao.splitClass(query, role);
        }

        System.out.println(info);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, info);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        //获取选中列的值。
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectInfo = info[i];
                System.out.println(selectInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(selectInfo);
                List<Student> students;
                if(role.equals("辅导员")) {
                    students = studentDao.selectStudentByClass(query, selectInfo, role);
                }else {
                    students = studentDao.selectStudentByClass(query, selectInfo,role);
//                    students = studentDao.selectStudentByRole(role, query);
                }
                listView.setAdapter(new DoryStudentAdapter(students, DelOrSearchActivity.this));
                System.out.println(students);
            }
        });

//        返回主页面
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setClass(DelOrSearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}