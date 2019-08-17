package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.TeacherDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

/**
 * Created by Auser on 2018/4/17.
 * 用于学生修改密码的界面
 */

public class TeacherChangePasswordActivity extends SuperActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;

    @BindView(R.id.old_psw)
    EditText old_psw;
    @BindView(R.id.new_psw)
    EditText new_psw;
    @BindView(R.id.new_psw2)
    EditText new_psw2;
    @BindView(R.id.bt_sumbit)
    Button bt_sumbit;
    @BindView(R.id.psw_check)
    CheckBox checkBox;
    @BindView(R.id.rl_layout)
    RelativeLayout layout;

    private Dialog mdialog;

    private String moldpsw;
    private String mnewpsw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_changepass);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mdialog = XhDialog.showLoadingDialog(TeacherChangePasswordActivity.this,"正在加载...");
        checkBox.setOnCheckedChangeListener(this);
        bt_sumbit.setOnClickListener(this);
        ly_return.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            //选择状态 显示明文--设置为可见的密码
            old_psw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            new_psw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            new_psw2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else {
            //默认状态显示密码--设置文本 要一起写才能起作用  InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
            old_psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            new_psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            new_psw2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_return:
                finish();
                break;
            case R.id.bt_sumbit:{
                moldpsw = old_psw.getText().toString();
                mnewpsw = new_psw.getText().toString();
                String mnewpsw2 = new_psw2.getText().toString();
                if (mnewpsw.equals(mnewpsw2)) {
                    SendMes(WenLiURL.TEACHER_CHANGE_PASSWORD);
                } else {
                    XhSnackBar.showResult(layout,"两次输入的密码不一样!");
                }
            }
            break;
        }

    }

    private void SendMes(String url) {
        mdialog.show();
        FormBody requestBody = new FormBody
                .Builder()
                .add("id", TeacherDao.getTeacher().getTeacherid()+"")
                .add("password",moldpsw)
                .add("newPassword",mnewpsw)
                .build();
        Type mtype = new TypeToken<Result<String>>() {}.getType();
        XhOkHttps.PutRequest(url, XhSp.getSharedPreferencesForString(TeacherChangePasswordActivity.this, SpConstants.TOKEN), mtype, requestBody, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            XhDialog.DialogNoCancleWithListener(TeacherChangePasswordActivity.this,"提示", "修改成功！", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    exit();
                                    XhSp.setSharedPreferences(TeacherChangePasswordActivity.this, SpConstants.TOKEN,"");
                                    IntentUtil.startActivtyWithFinish(TeacherChangePasswordActivity.this, LoginActivity.class);
                                }
                            });
                        }
                    });
                }else if(code == 888){
                    XhDialog.DialogNoCancleWithListener(TeacherChangePasswordActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            exit();
                            XhSp.setSharedPreferences(TeacherChangePasswordActivity.this, SpConstants.TOKEN,"");
                            IntentUtil.startActivtyWithFinish(TeacherChangePasswordActivity.this, LoginActivity.class);
                        }
                    });
                }else if(code == 500){
                    XhSnackBar.showResult(layout, "修改失败，密码错误");
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResultWithAction(layout, "服务器跑丢了", "点我追回", new XhSnackBar.Action() {
                    @Override
                    public void actionMethod() {
                        SendMes(WenLiURL.STUDENT_CHANGEPASSWORD);
                    }
                });
            }
        });
    }
}
