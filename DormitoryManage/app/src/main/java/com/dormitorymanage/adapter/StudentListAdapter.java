package com.dormitorymanage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dormitorymanage.R;
import com.dormitorymanage.activity.ChangeStudentActivity;
import com.dormitorymanage.activity.DelOrSearchActivity;
import com.dormitorymanage.bean.Student;
import com.dormitorymanage.dao.StudentDao;

import java.util.List;

public class StudentListAdapter extends BaseAdapter {
    private List<Student> data;

    private Context context;

    public StudentListAdapter(List<Student> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item,null);
        }
        TextView name = view.findViewById(R.id.name);
        TextView room = view.findViewById(R.id.room);
        TextView dory = view.findViewById(R.id.dory);
        name.setText(data.get(i).getName());
        room.setText(String.valueOf(data.get(i).getRoom()));
        dory.setText(data.get(i).getBuilding());
//        删除学生
        Button delStudent = view.findViewById(R.id.delStudent);
        delStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("删除"+ i);
                StudentDao studentDao = new StudentDao();
                studentDao.delStudent(data.get(i).getId());
                System.out.println(data.get(i).getId());
                Intent intent = new Intent(context, DelOrSearchActivity.class);
                context.startActivity(intent);
            }
        });
//        修改学生
        Button changeStudent = view.findViewById(R.id.changeStudent);
        changeStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, ChangeStudentActivity.class);
                String name = data.get(i).getName();
                String classes = data.get(i).getClasses();
                Integer room = data.get(i).getRoom();
                String building = data.get(i).getBuilding();
                Integer sid = data.get(i).getId();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("classes", classes);
                bundle.putInt("room", room);
                bundle.putString("building", building);
                bundle.putInt("sid", sid);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        Log.e("TAG", "getView:" +i);
        return view;
    }

}
