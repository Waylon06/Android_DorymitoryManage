package com.dormitorymanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dormitorymanage.R;
import com.dormitorymanage.bean.Notice;

import java.util.List;

public class NoticeAdapter extends BaseAdapter {
    private List<Notice> data;
    private Context context;

    public NoticeAdapter(List<Notice> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public NoticeAdapter() {
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
            view = LayoutInflater.from(context).inflate(R.layout.notice_item,null);
        }
        TextView title = view.findViewById(R.id.title);
        TextView content = view.findViewById(R.id.content);
        title.setText(data.get(i).getTitle());
        content.setText(data.get(i).getContent());
        return view;
    }
}
