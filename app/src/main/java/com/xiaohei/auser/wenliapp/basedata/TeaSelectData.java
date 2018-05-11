package com.xiaohei.auser.wenliapp.basedata;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.SelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/17.
 */

public class TeaSelectData {

    public static List<SelectEntity> getSelectList(){
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
