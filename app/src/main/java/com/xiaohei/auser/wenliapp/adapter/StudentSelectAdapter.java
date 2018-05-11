package com.xiaohei.auser.wenliapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.SelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/15.
 */

public class StudentSelectAdapter extends BaseAdapter {

    private List<SelectEntity> list;
    private LayoutInflater layoutInflater;
    private TextView tv;
    private ImageView img;

    public StudentSelectAdapter(Context context,List<SelectEntity> list) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(list == null){
            this.list = new ArrayList<>();
        }else{
            this.list = list;
        }

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
            convertView = layoutInflater.inflate(R.layout.item_select, null);
            ViewCache viewCache = new ViewCache();
            tv = (TextView) convertView.findViewById(R.id.item_tv);
            img = (ImageView) convertView.findViewById(R.id.cb);
            viewCache.tv = tv;
            viewCache.img = img;
            convertView.setTag(viewCache);
        }else
        {
            ViewCache viewCache = (ViewCache) convertView.getTag();
            tv = viewCache.tv;
            img = viewCache.img;
        }
        tv.setText(list.get(position).getName());
        img.setImageResource(list.get(position).getImg());
        return convertView;
    }

    public class ViewCache{
        public TextView tv;
        public ImageView img;
    }
}
