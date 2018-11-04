package com.xiaohei.auser.wenliapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.wenlientity.NewStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/15.
 * 学生信息展示界面的适配器
 */

public class StudentInfoAdapter extends BaseAdapter {

    private List<NewStudent> students = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private TextView tv_name;
    private TextView tv_cardid;
    private TextView tv_ishead;
    private String headid;

    public StudentInfoAdapter(Context context, List<NewStudent> list, String headid) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.students = list;
        this.headid = headid;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_students, null);
            ViewCache viewCache = new ViewCache();
            tv_name = convertView.findViewById(R.id.item_name);
            tv_cardid = convertView.findViewById(R.id.item_cardid);
            tv_ishead = convertView.findViewById(R.id.item_ishead);
            viewCache.tv_name = tv_name;
            viewCache.tv_cardid = tv_cardid;
            viewCache.tv_ishead = tv_ishead;
            convertView.setTag(viewCache);
        }else
        {
            ViewCache viewCache = (ViewCache) convertView.getTag();
            viewCache.tv_name = tv_name;
            viewCache.tv_cardid = tv_cardid;
            viewCache.tv_ishead = tv_ishead;
        }
        tv_name.setText(students.get(position).getName());
        tv_cardid.setText(students.get(position).getCardId());
        if(!students.get(position).getId().equals(headid)){
            tv_ishead.setText("否");
        }else{
            tv_ishead.setText("是");
        }
        return convertView;
    }

    public class ViewCache{
        public TextView tv_name;
        public TextView tv_cardid;
        public TextView tv_ishead;
    }
}
