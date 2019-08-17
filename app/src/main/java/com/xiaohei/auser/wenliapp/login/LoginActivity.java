package com.xiaohei.auser.wenliapp.login;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.IdRes;
import com.google.android.material.textfield.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.StudentDao;
import com.xiaohei.auser.wenliapp.Dao.TeacherDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.teacherActivity.TeacherMainActivity;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.studentActivity.StudentMainActivity;
import com.xiaohei.auser.wenliapp.studentActivity.StudentRegisterActivity;
import com.xiaohei.auser.wenliapp.utils.NetStateUtils;
import com.xiaohei.auser.wenliapp.utils.StringVerify;
import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.NewStudent;
import com.xiaohei.auser.wenliapp.wenlientity.NewTeacher;
import com.xiaohei.auser.wenliapp.wenlientity.Result;
import com.xiaohei.auser.wenliapp.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Auser on 2018/4/11.
 * 登陆界面的布局和功能的实现
 * 功能要求：
 * 1、选择登录角色（RadioButton）
 * 2、验证账号正确性
 */

public class LoginActivity extends SuperActivity implements TextWatcher, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.activity_main)
    RelativeLayout layout;
    @BindView(R.id.tl_username)
    TextInputLayout tl_username;
    @BindView(R.id.tl_password)
    TextInputLayout tl_password;
    @BindView(R.id.et_username)
    ClearEditText et_username;
    @BindView(R.id.et_password)
    ClearEditText et_password;

    @BindView(R.id.type_stu)
    RadioButton type_stu;
    @BindView(R.id.type_tea)
    RadioButton type_tea;
    @BindView(R.id.type_select)
    RadioGroup type_select;

    private Dialog mdialog;
    private int type;
    private static final int RESULT_TYPE_STU = 1;
    private static final int RESULT_TYPE_TEA = 2;
    private static final int RESULT_TYPE_TOKEN = 3;
    private String username ;
    private String userpassword;
    private String loginType;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (type == 1)
                getInforStudent();
            else  if(type == 2)
                getInforTeacher();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    //初始化组件信息
    private void init() {
        mdialog = XhDialog.showLoadingDialog(LoginActivity.this,"正在登录...");
        bt_login.setEnabled(false);
        initData();
        type_select.setOnCheckedChangeListener(this);
        et_username.addTextChangedListener(this);
        et_password.addTextChangedListener(this);
        bt_login.setOnClickListener(this);
    }

    private void initData() {
        type = XhSp.getSharedPreferencesForInt(LoginActivity.this,SpConstants.LOGIN_TYPE);
        switch (type){
            case -1:
            case 1:{
                type_stu.setChecked(true);
                type = 1;
                XhSnackBar.showResult(layout,"当前登录类型为学生");
                loginType = "studentLogin";
            }
            break;
            case 2:{
                type_tea.setChecked(true);
                type = 2;
                XhSnackBar.showResult(layout,"当前登录类型为教师");
                loginType = "teacherLogin";
            }
            break;
        }

        username = XhSp.getSharedPreferencesForString(LoginActivity.this,SpConstants.LOGIN_NUMBER);
        et_username.setText(username);
        if(username != null && !(XhSp.getSharedPreferencesForString(LoginActivity.this,SpConstants.TOKEN) == null
                || XhSp.getSharedPreferencesForString(LoginActivity.this,SpConstants.TOKEN).equals(""))){
            mdialog.show();
            handler.sendEmptyMessageDelayed(1,1000);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    //用来控制登录按钮是否可以操作
    @Override
    public void afterTextChanged(Editable s) {
        if (StringVerify.validateUserName(tl_username.getEditText().getText().toString())
                && StringVerify.validatePassword(tl_password.getEditText().getText().toString())) {
            bt_login.setEnabled(true);
            bt_login.setAlpha(1f);
        }else{
            bt_login.setEnabled(false);
            bt_login.setAlpha(0.2f);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.type_tea:{
                XhSnackBar.showResult(layout,"选择了教师");
                type = 2;
                loginType = "teacherLogin";
            }
            break;
            case R.id.type_stu:{
                XhSnackBar.showResult(layout,"选择了学生");
                type = 1;
                loginType = "studentLogin";
            }
            break;
        }
    }

    @Override
    public void onClick(View view) {
        XhSp.setSharedPreferences(LoginActivity.this,SpConstants.LOGIN_NUMBER,tl_username.getEditText().getText().toString());
        username = tl_username.getEditText().getText().toString();
        userpassword = tl_password.getEditText().getText().toString();
        if(NetStateUtils.isNetworkConnected(LoginActivity.this)){
            mdialog.show();
            if(XhSp.getSharedPreferencesForString(LoginActivity.this,SpConstants.TOKEN) == null
                    || XhSp.getSharedPreferencesForString(LoginActivity.this,SpConstants.TOKEN).equals("")) {
                GetToken();
            }
            else{
                if (type == 1)
                    getInforStudent();
                else  if(type == 2)
                    getInforTeacher();
            }
        }else{
            XhSnackBar.showResultWithAction(layout, "当前无网络连接，请检查!", new XhSnackBar.Action() {
                @Override
                public void actionMethod() {
                    NetStateUtils.startToSettings(LoginActivity.this);
                }
            });
        }
    }

    private void GetToken() {
        RequestBody requestBody = new FormBody.Builder()
                .add(WenLiURL.LOGIN_USERNAME,username)
                .add(WenLiURL.LOGIN_PASSWORD,userpassword)
                .add(WenLiURL.LOGIN_LOGINTYPE,loginType)
                .build();
        XhOkHttps.PostRequest(WenLiURL.LOGIN_URL_FOR_TOKEN , requestBody, new TypeToken<Result<String>>() {}.getType(), new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                SwitchCode(RESULT_TYPE_TOKEN,result);
            }

            @Override
            public void fail() {
                SwitchCode(RESULT_TYPE_TOKEN,XhOkHttps.getErrorResult());
            }
        });
    }

    private void getInforTeacher() {
        XhOkHttps.GetRequest(WenLiURL.LOGIN_TEACHER_INFOR + username, XhSp.getSharedPreferencesForString(LoginActivity.this, SpConstants.TOKEN),
                new TypeToken<Result<NewTeacher>>() {}.getType(), new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                SwitchCode(RESULT_TYPE_TEA,result);
            }

            @Override
            public void fail() {
                SwitchCode(RESULT_TYPE_TEA,XhOkHttps.getErrorResult());
            }
        });
    }

    private void getInforStudent() {
        XhOkHttps.GetRequest(WenLiURL.LOGIN_URL_FOR_INFOR + username, XhSp.getSharedPreferencesForString(LoginActivity.this, SpConstants.TOKEN),
                new TypeToken<Result<NewStudent>>() {}.getType(), new XhHttpInterface() {
            @Override
            public void complied(Result result) {
               SwitchCode(RESULT_TYPE_STU,result);
            }

            @Override
            public void fail() {
                SwitchCode(RESULT_TYPE_STU,XhOkHttps.getErrorResult());
            }
        });
    }

    private void SwitchCode(int result_type , Result result) {
        int code = result.getCode();
        if(mdialog.isShowing()){
            mdialog.dismiss();
        }
        switch (code){
            case 200:{
                switch (result_type){
                    case RESULT_TYPE_STU:{
                        NewStudent student = (NewStudent) result.getResult();
                        StudentDao.saveStudent(student);
                        XhSp.setSharedPreferences(LoginActivity.this, SpConstants.LOGIN_TYPE,type);
                        XhSp.setSharedPreferences(LoginActivity.this,SpConstants.STUDENT_ID,student.getId());
                        if(student.getStatus() == 0){
                            IntentUtil.startActivtyWithFinish(LoginActivity.this, StudentRegisterActivity.class);
                        }
                        else{
                            IntentUtil.startActivtyWithFinish(LoginActivity.this, StudentMainActivity.class);
                        }
                    }
                    break;
                    case RESULT_TYPE_TEA:{
                        NewTeacher teacher = (NewTeacher) result.getResult();
                        TeacherDao.saveTeacher(teacher);
                        XhLogUtil.d(teacher.toString());
                        XhSp.setSharedPreferences(LoginActivity.this, SpConstants.LOGIN_TYPE, type);
                        XhSp.setSharedPreferences(LoginActivity.this, SpConstants.STUDENT_ID, teacher.getId());
                        IntentUtil.startActivtyWithFinish(LoginActivity.this, TeacherMainActivity.class);
                    }
                    break;
                    case RESULT_TYPE_TOKEN:{
                        XhSp.setSharedPreferences(LoginActivity.this,SpConstants.TOKEN,(String) result.getResult());
                        if (type == 1)
                            getInforStudent();
                        else  if(type == 2)
                            getInforTeacher();
                    }
                    break;
                }
            }
            break;
            case 500:{
                switch (result_type){
                    case RESULT_TYPE_STU:
                    case RESULT_TYPE_TEA:{
                        XhSnackBar.showResult(layout,"服务器数据异常");
                    }
                    break;
                    case RESULT_TYPE_TOKEN:{
                        XhSnackBar.showResult(layout,"账号或者密码错误");
                    }
                    break;
                }
            }
            break;
            case 888:{
                XhSnackBar.showResult(layout,"账号密码已失效，请重新登录");
                XhSp.setSharedPreferences(LoginActivity.this, SpConstants.TOKEN,"");
            }
            break;
            case -1:{
                switch (result_type){
                    case RESULT_TYPE_STU:
                    case RESULT_TYPE_TEA:{
                        XhSnackBar.showResultWithNetERROR(layout);
                    }
                    break;
                }
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        BackExit();
    }
}