package com.xiaohei.auser.wenliapp.basedata;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.SelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/17.
 */

public class SetData {

    public static List<SelectEntity> getSelectList(){
        List<SelectEntity> list = new ArrayList<>();
        SelectEntity mail = new SelectEntity("意见反馈", R.drawable.mail);
        SelectEntity homepage = new SelectEntity("关于我们",R.drawable.homepage);
        list.add(mail);
        list.add(homepage);
        return list;
    }
}
