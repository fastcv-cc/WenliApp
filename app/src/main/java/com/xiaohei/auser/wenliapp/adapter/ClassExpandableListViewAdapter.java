package com.xiaohei.auser.wenliapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.vo.ClassRooms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/23.
 * 用于分组组件的适配器
 */

public class ClassExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<ClassRooms> class_dr_list = new ArrayList<>();

    public ClassExpandableListViewAdapter(Context context,List<ClassRooms> class_dr_list) {
        this.mContext = context;
        this.class_dr_list = class_dr_list;
    }

    @Override
    public int getGroupCount() {
        return class_dr_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return class_dr_list.get(groupPosition).getRoomsVoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return class_dr_list.get(groupPosition).getClassName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return class_dr_list.get(groupPosition).getRoomsVoList().get(childPosition)
                .getRoomName();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.group_item, null);
            groupHolder = new GroupHolder();
            groupHolder.txt = convertView.findViewById(R.id.txt);
            groupHolder.img = convertView.findViewById(R.id.img);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.txt.setText(class_dr_list.get(groupPosition).getClassName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.child_item, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = convertView.findViewById(R.id.txt);
            itemHolder.img = convertView.findViewById(R.id.img);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
            itemHolder.txt.setText(class_dr_list.get(groupPosition).getRoomsVoList()
                    .get(childPosition).getBuildName() +
                    class_dr_list.get(groupPosition).getRoomsVoList()
                            .get(childPosition).getRoomName());
        itemHolder.img.setBackgroundResource(R.drawable.img_depart);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupHolder {
        TextView txt;
        ImageView img;
    }

    private class ItemHolder {
        TextView txt;
        ImageView img;
    }
}
