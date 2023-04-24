package com.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.dormitorymanage.R;
import com.dormitorymanage.bean.Student;
import com.dormitorymanage.dao.StudentDao;

import java.util.List;

public class DoryStudentAdapter extends BaseAdapter {

    private List<Student> data;

    private Context context;

    public DoryStudentAdapter(List<Student> data, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.dory_item,null);
        }
        TextView name = view.findViewById(R.id.name);
        TextView room = view.findViewById(R.id.room);
        TextView dory = view.findViewById(R.id.dory);
        name.setText(data.get(i).getName());
        room.setText(String.valueOf(data.get(i).getRoom()));
        dory.setText(data.get(i).getBuilding());
        Switch isBack = view.findViewById(R.id.isBack);
        if (data.get(i).getBack() == 1) {
            isBack.setChecked(true);
        }
        isBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                StudentDao studentDao = new StudentDao();
                boolean isSuccess = false;
                if (isChecked) {
                    isSuccess = studentDao.updateIsBack(data.get(i).getId(), 1);
                }else {
                    isSuccess = studentDao.updateIsBack(data.get(i).getId(), 0);

                }
                if (isSuccess) {
                    System.out.println("成功");
                }else {
                    System.out.println("失败");
                }
            }
        });

        return view;
    }
}
