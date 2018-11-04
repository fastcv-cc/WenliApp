package com.xiaohei.auser.wenliapp.normalActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.StudentSelectAdapter;
import com.xiaohei.auser.wenliapp.basedata.BaseData;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/17.
 * 用于设置界面
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.setlist)
    ListView listView;
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        type = XhSp.getSharedPreferencesForInt(SettingActivity.this, SpConstants.LOGIN_TYPE);
        if (type == 1){
            listView.setAdapter(new StudentSelectAdapter(SettingActivity.this, BaseData.getSelectListForSetting()));
        }else if(type == 2){
            listView.setAdapter(new StudentSelectAdapter(SettingActivity.this, BaseData.getSelectListForSettingForTeacher()));
        }
        listView.setOnItemClickListener(this);
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_return || v.getId() == R.id.img_return) {
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (type){
            case 1:{
                switch (position){
                    case 0:{
                        IntentUtil.MystartActivity(SettingActivity.this,IdealActivity.class);
                    }
                    break;
                    case 1:{
                        IntentUtil.MystartActivity(SettingActivity.this,AboutActivity.class);
                    }
                    break;
                    default:{

                    }
                }
            }
            break;
            case 2:{
                switch (position){
                    case 0:{
                        IntentUtil.MystartActivity(SettingActivity.this,AboutActivity.class);
                    }
                    break;
                    default:{

                    }
                }
            }
            break;

        }
    }
}
