package com.dormitorymanage.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dormitorymanage.R;
import com.dormitorymanage.adapter.NoticeAdapter;
import com.dormitorymanage.bean.Notice;
import com.dormitorymanage.dao.NoticeDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        NoticeDao noticeDao = new NoticeDao();
        List<Notice> notices = noticeDao.noticeList();

        ListView notice = findViewById(R.id.notice);
        notice.setAdapter(new NoticeAdapter(notices, this));


//        导航栏
        bottomNavigationView = findViewById(R.id.home);

        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(1).getItemId());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_message://消息页
                        startActivity(new Intent(getApplicationContext(), NoticeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_home:   //主页面
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_mine://用户页
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
}