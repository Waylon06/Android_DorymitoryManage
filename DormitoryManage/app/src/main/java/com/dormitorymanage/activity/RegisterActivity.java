package com.dormitorymanage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dormitorymanage.R;
import com.dormitorymanage.dao.UserDao;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    String[] roleItems;
    String selectedRole = "辅导员";
    boolean isSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //        角色下拉框选项
        spinner = findViewById(R.id.role);
        roleItems = getResources().getStringArray(R.array.role);
//        获取下拉框选中项
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRole = roleItems[i];
                System.out.println(selectedRole);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        注册
        Button signOn = findViewById(R.id.signon);
        signOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText account = findViewById(R.id.account);
                EditText password = findViewById(R.id.password);
                EditText phone = findViewById(R.id.phone);
                EditText manage = findViewById(R.id.manage);
                String username_str = account.getText().toString();
                String password_str = password.getText().toString();
                String phone_str = phone.getText().toString();
                String manage_str = manage.getText().toString();
                UserDao userDao = new UserDao();
                isSuccess = userDao.register(username_str, password_str, selectedRole, manage_str, Integer.valueOf(phone_str));
                if (isSuccess != false) {
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    AlertDialog dialog = new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("错误")
                            .setMessage("注册失败")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(RegisterActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            }).create();
                    dialog.show();
                }
            }
        });
//        跳转登录
        Button toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}