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
import com.xiaohei.auser.wenliapp.basedata.SetData;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        listView.setAdapter(new StudentSelectAdapter(SettingActivity.this, SetData.getSelectList()));
        listView.setOnItemClickListener(this);
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_return || v.getId() == R.id.img_return) {
            finish();
        }
        else if(v.getId() == R.id.img_send){

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
}
