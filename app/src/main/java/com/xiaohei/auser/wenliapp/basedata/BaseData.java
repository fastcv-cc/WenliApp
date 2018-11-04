package com.xiaohei.auser.wenliapp.basedata;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.wenlientity.SelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenike on 2018/10/17.
 */

public class BaseData {

    public static List<SelectEntity> getSelectListForSetting(){
        List<SelectEntity> list = new ArrayList<>();
        SelectEntity mail = new SelectEntity("意见反馈", R.drawable.mail);
        SelectEntity homepage = new SelectEntity("关于我们",R.drawable.homepage);
        list.add(mail);
        list.add(homepage);
        return list;
    }

    public static List<SelectEntity> getSelectListForSettingForTeacher(){
        List<SelectEntity> list = new ArrayList<>();
        SelectEntity homepage = new SelectEntity("关于我们",R.drawable.homepage);
        list.add(homepage);
        return list;
    }

    public static List<SelectEntity> getSelectListForStudent(){
        List<SelectEntity> list = new ArrayList<>();
        SelectEntity userinfo = new SelectEntity("我的信息", R.drawable.userinfo);
        SelectEntity newpaper = new SelectEntity("新建周记",R.drawable.newpaper);
        SelectEntity lock = new SelectEntity("账户安全",R.drawable.lock);
        SelectEntity browse = new SelectEntity("成绩查询",R.drawable.browse);
        SelectEntity exist = new SelectEntity("退出登录",R.drawable.exist);
        SelectEntity set = new SelectEntity("设置",R.drawable.set);
        list.add(userinfo);
        list.add(newpaper);
        list.add(lock);
        list.add(browse);
        list.add(exist);
        list.add(set);
        return list;
    }

    public static List<SelectEntity> getSelectListForTeacher(){
        List<SelectEntity> list = new ArrayList<>();
        SelectEntity userinfo = new SelectEntity("我的信息", R.drawable.userinfo);
        SelectEntity lock = new SelectEntity("账户安全",R.drawable.lock);
        SelectEntity exist = new SelectEntity("退出登录",R.drawable.exist);
        SelectEntity set = new SelectEntity("设置",R.drawable.set);
        list.add(userinfo);
        list.add(lock);
        list.add(exist);
        list.add(set);
        return list;
    }

}
