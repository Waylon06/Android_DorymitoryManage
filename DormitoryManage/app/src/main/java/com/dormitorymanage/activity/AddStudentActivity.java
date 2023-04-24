package com.dormitorymanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dormitorymanage.R;
import com.dormitorymanage.dao.StudentDao;

public class AddStudentActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
//        返回主菜单按钮
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setClass(AddStudentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        添加学生按钮
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        获取输入框内容
                EditText name = findViewById(R.id.name);
                EditText manager = findViewById(R.id.manager);
                EditText classes = findViewById(R.id.classes);
                EditText room = findViewById(R.id.room);
                EditText building = findViewById(R.id.building);
//                        转换数据类型
                String name_str = name.getText().toString();
                String manager_str = manager.getText().toString();
                String classes_str = classes.getText().toString();
                String room_int = room.getText().toString();
                String building_str = building.getText().toString();
//                        连接接口
                StudentDao studentDao = new StudentDao();
                boolean isSuccess = studentDao.addStudent(name_str, manager_str, classes_str, Integer.valueOf(room_int), building_str);
                if (isSuccess != false) {
                    intent = new Intent();
                    intent.setClass(AddStudentActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    AlertDialog dialog = new AlertDialog.Builder(AddStudentActivity.this)
                            .setTitle("错误")
                            .setMessage("录入有误，请联系管理员")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(AddStudentActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            }).create();
                    dialog.show();
                }
            }
        });

    }
}