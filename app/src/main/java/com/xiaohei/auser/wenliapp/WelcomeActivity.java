package com.xiaohei.auser.wenliapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.updateapp.UpdateApp;
import com.example.updateapp.minterface.EndInterface;
import com.xiaohei.auser.wenliapp.basedata.WenliConstants;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.utils.VersionUtil;
import com.xiaohei.auser.wenliapp.utils.XhDynaicRequestPermissionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/16.
 * 欢迎界面界面
 * 功能要求：
 * 1、有两个画面，一个是自己APP的欢迎页面（2s 后跳转到下个页面），一个是心理中心的联系方式页面
 * 2、心理中心页面需要两个功能：
 * 一、进入登录界面的入口（这里用个按钮实现）
 * 二、拨打电话的快捷方式（这里用个按钮实现）
 * 3、检查更新的功能（强制更新）（这里调用的是自己写的强制更新Library（高适配7.0,、8.0））
 */


public class WelcomeActivity extends SuperActivity implements View.OnClickListener {

    @BindView(R.id.welcome_tv_version)
    TextView tv_version;
    @BindView(R.id.welcome_layout)
    RelativeLayout activity_layout;
    @BindView(R.id.call_xl)
    TextView call_xl;
    @BindView(R.id.gohome)
    Button bt_gohome;

    @BindView(R.id.img_welcome)
    ImageView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        //调用自己写的更新的Library并在回调方法中实现相应的操作
        UpdateApp updateApp = new UpdateApp(WelcomeActivity.this, new EndInterface() {
            @Override
            public void Failed(int code) {
                if(code == 5){
                    ChangeBg();
                }else {
                    finish();
                }
            }
        });
        updateApp.initUpdateApp(WenLiURL.UPDATE_APP);
        init();
    }

    //按要求更换背景图
    private void ChangeBg() {
        activity_layout.setBackgroundResource(R.drawable.welcome2);
        welcome.setVisibility(View.GONE);
        bt_gohome.setVisibility(View.VISIBLE);
        call_xl.setVisibility(View.VISIBLE);
        bt_gohome.setEnabled(true);
        call_xl.setEnabled(true);
    }

    //初始化布局中的组件
    private void init() {
        tv_version.setText("v "+VersionUtil.getVerName(WelcomeActivity.this));
        call_xl.setOnClickListener(this);
        bt_gohome.setOnClickListener(this);
    }

    //实现两个按钮的点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.call_xl:{
                //拨打电话 其中 0 代表心理中心西校区电话； 1 代表心理中心城中校区电话
                if(XhDynaicRequestPermissionUtil.callPhonePermissions(WelcomeActivity.this)){
                    XhDialog.CallPhoneDialogWithListener(WelcomeActivity.this,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                IntentUtil.Call(WenliConstants.WENLI_XINLIAN_XI_PHONENUMBER,WelcomeActivity.this);
                            } else if(which == 1){
                                IntentUtil.Call(WenliConstants.WENLI_XINLIAN_ZHONG_PHONENUMBER,WelcomeActivity.this);
                            }
                        }
                    });
                }
            }
            break;
            case R.id.gohome:{
                IntentUtil.startActivtyWithFinish(WelcomeActivity.this, LoginActivity.class);
            }
            break;
        }
    }
}
