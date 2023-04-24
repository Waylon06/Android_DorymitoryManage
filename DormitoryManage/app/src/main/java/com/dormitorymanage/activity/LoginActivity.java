package com.dormitorymanage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dormitorymanage.R;
import com.dormitorymanage.dao.UserDao;
import com.google.android.material.navigation.NavigationBarView;

public class LoginActivity extends AppCompatActivity {
    private boolean isRight;
    private Spinner spinner;
    String[] roleItems;
    String selectedRole = "辅导员";
    SharedPreferences userInfo;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        角色下拉框选项
        spinner = findViewById(R.id.role);
        roleItems = getResources().getStringArray(R.array.role);
//        获取下拉框选中项
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRole = roleItems[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        实现登录验证
        Button signIn = findViewById(R.id.login);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText account = findViewById(R.id.account);
                EditText password = findViewById(R.id.password);
//                将数据转换为String类型
                String account_str = account.getText().toString();
                String password_str = password.getText().toString();

                UserDao userDao = new UserDao();
                isRight = userDao.verifyUser(account_str, password_str, selectedRole);
                if (isRight != false) {
                    userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userInfo.edit();
                        if (selectedRole.equals("辅导员")) {
                            editor.putString("query", account_str);
                        }else if(selectedRole.equals("宿管")) {
                            editor.putString("query", "博慧一");
                            editor.putString("suguan", account_str);
                        }
                    editor.putString("role",selectedRole);
                    editor.commit();

                    intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("错误")
                            .setMessage("账号或者密码错误")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(LoginActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                                }
                            }).create();
                    dialog.show();
                }
            }
        });


//        去注册按钮
        Button goToRegister = findViewById(R.id.toRegister);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}