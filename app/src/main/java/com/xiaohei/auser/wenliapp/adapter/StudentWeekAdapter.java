package com.xiaohei.auser.wenliapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.WeeksText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/15.
 */

public class StudentWeekAdapter extends BaseAdapter {

    private List<WeeksText> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private TextView tv_reg;
    private TextView tv_time;

    public StudentWeekAdapter(Context context,List<WeeksText> list) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_week, null);
            ViewCache viewCache = new ViewCache();
            tv_reg = convertView.findViewById(R.id.mw_reg);
            tv_time = convertView.findViewById(R.id.mw_time);
            viewCache.tv_reg = tv_reg;
            viewCache.tv_time = tv_time;
            convertView.setTag(viewCache);
        }else
        {
            ViewCache viewCache = (ViewCache) convertView.getTag();
            tv_reg = viewCache.tv_reg;
            tv_time = viewCache.tv_time;
        }
        if (list.get(position).getTeachersReturnText() != null) {
            tv_reg.setText("已审阅");
            tv_reg.setTextColor(Color.GREEN);
        } else {
            tv_reg.setText("未审阅");
            tv_reg.setTextColor(Color.RED);
        }
        tv_time.setText("提交时间：" + list.get(position).getCreateTime().substring(0,
                list.get(position).getCreateTime().length()-2));
        return convertView;
    }

    public class ViewCache{
        public TextView tv_reg;
        public TextView tv_time;
    }
}
