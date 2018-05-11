package com.xiaohei.auser.wenliapp.basedata;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.SelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/17.
 */

public class StuSelectData {

    public static List<SelectEntity> getSelectList(){
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

}
