package com.dormitorymanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dormitorymanage.R;
import com.dormitorymanage.dao.StudentDao;

public class ChangeStudentActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_student);

        intent = this.getIntent();
        Bundle data = intent.getExtras();
        String name_str = data.getString("name");
        String classes_str = data.getString("classes");
        Integer room_int = data.getInt("room");
        Integer sid = data.getInt("sid");
        String building_str = data.getString("building");
//        获取输入框
        TextView name = findViewById(R.id.name);
        TextView classes = findViewById(R.id.classes);
        TextView room = findViewById(R.id.room);
        TextView building = findViewById(R.id.building);
//        给输入框赋值
        name.setText(name_str);
        classes.setText(classes_str);
        room.setText(String.valueOf(room_int));
        building.setText(building_str);
//        提交修改
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = findViewById(R.id.name);
                TextView classes = findViewById(R.id.classes);
                TextView room = findViewById(R.id.room);
                TextView building = findViewById(R.id.building);

                String name_str = name.getText().toString();
                String classes_str = classes.getText().toString();
                String room_int = room.getText().toString();
                String building_str = building.getText().toString();
                StudentDao studentDao = new StudentDao();
                boolean isSuccess = studentDao.updateStudent(name_str, sid, classes_str, Integer.valueOf(room_int), building_str);
                if (isSuccess) {
                    intent = new Intent(ChangeStudentActivity.this, DelOrSearchActivity.class);
                    startActivity(intent);
                }else {
                    AlertDialog dialog = new AlertDialog.Builder(ChangeStudentActivity.this)
                            .setTitle("错误")
                            .setMessage("修改失败")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(ChangeStudentActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            }).create();
                    dialog.show();
                }
            }
        });

    }
}