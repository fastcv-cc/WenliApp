package com.xiaohei.auser.wenliapp.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.dao.StudentDao;
import com.xiaohei.auser.wenliapp.dao.TeacherDao;
import com.xiaohei.auser.wenliapp.dialog.ExistDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.dialog.SnackBarToast;
import com.xiaohei.auser.wenliapp.entity.vo.StudentsVo;
import com.xiaohei.auser.wenliapp.entity.vo.TeachersVo;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.net.TeacherUrl;
import com.xiaohei.auser.wenliapp.sp.NormalSpUtils;
import com.xiaohei.auser.wenliapp.sp.SetSpUtils;
import com.xiaohei.auser.wenliapp.studentActivity.StudentMainActivity;
import com.xiaohei.auser.wenliapp.studentActivity.StudentRegisterActivity;
import com.xiaohei.auser.wenliapp.teacherActivity.TeacherMainActivity;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;
import com.xiaohei.auser.wenliapp.utils.NetUtils;
import com.xiaohei.auser.wenliapp.utils.StringVerify;
import com.xiaohei.auser.wenliapp.widget.ClearEditText;

import java.io.IOException;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Auser on 2018/4/11.
 * 登陆界面的布局和实现
 */

public class LoginActivity extends Activity implements TextWatcher {

    @BindView(R.id.bt_snackbar)
    Button button;
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


    private Dialog mdialog;
    private int type = 1;
    protected RadioGroup type_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(LoginActivity.this,"正在登录...");
        et_username.addTextChangedListener(this);
        et_password.addTextChangedListener(this);
        button.setEnabled(false);
        et_username.setText(NormalSpUtils.getLoginNumber(LoginActivity.this));
        type = NormalSpUtils.getLoginType(LoginActivity.this);
        if(type == 0){
            type = 1;
        }
        JudgeLoginType();
        type_select = findViewById(R.id.type_select);
        type_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.type_tea:{
                        showResult("选择了教师");
                        type = 2;
                    }
                    break;
                    case R.id.type_stu:{
                        showResult("选择了学生");
                        type = 1;
                    }
                    break;
                }
            }
        });
    }

    private void JudgeLoginType() {
        if(type == 1){
            //学生
            type_stu.setChecked(true);
            showResult("当前登录类型为学生");
        }else{
            //教师
            type_tea.setChecked(true);
            showResult("当前登录类型为教师");
        }
    }


    @OnClick(R.id.bt_snackbar)
    public void Login(){
        String musername = tl_username.getEditText().getText().toString();
        String mpassword = tl_password.getEditText().getText().toString();
        if(NetUtils.isNetworkConnected(LoginActivity.this)){
                mdialog.show();
                SetSpUtils.setSharedPreferences(LoginActivity.this,"login_number",musername);
                if(type == 1){
                    sendStuLoginInfo(StudentUrl.getLoginUrl(),musername,mpassword);
                }else if(type == 2){
                    sendTeaLoginInfo(TeacherUrl.getLoginUrl(),musername,mpassword);
                }
        }else{
                SnackBarToast.showResultWithAction(layout, "当前无网络连接，请检查!", new SnackBarToast.Action() {
                    @Override
                    public void actionMethod() {
                        NetUtils.startToSettings(LoginActivity.this);
                    }
                });
            }
    }

    private void sendTeaLoginInfo(@NonNull String url,@NonNull String teacherId,@NonNull String teacherPsw){
        RequestBody requestBody = new FormBody.Builder().add("teacherCardId",teacherId)
                .add("teacherPassword",teacherPsw).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mdialog.dismiss();
                        showResult("服务器连接异常!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mdialog.dismiss();
                String str= null;
                try {
                    str = response.body().string();
                    Gson gson = new Gson();
                    Log.d("test", "onResponse: ");
                    Type mtype = new TypeToken<JsonReturnData<TeachersVo>>() {
                    }.getType();
                    Log.d("test", "onResponse: ");
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    Log.d("test", "onResponse: ");
                    int code = jsonReturnData.getCode();
                    if(code == 300){
                        showResult("请求数据失败!");
                    }else if(code == 201){
                        showResult("账号或者密码错误!");
                    }else if(code == 200){
                        TeachersVo teachers = (TeachersVo) jsonReturnData.getData();
                        TeacherDao.delTeacher(LoginActivity.this,teachers.getTeacherId());
                        TeacherDao.addTeacher(LoginActivity.this,teachers);
                        SetSpUtils.setSharedPreferences(LoginActivity.this,"logintype",type);
                        SetSpUtils.setSharedPreferences(LoginActivity.this,"teacherid",teachers.getTeacherId());
                        IntentUtil.startActivtyWithFinish(LoginActivity.this, TeacherMainActivity.class);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(LoginActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void sendStuLoginInfo(@NonNull String url,@NonNull String studentId,@NonNull String studentPsw){
        RequestBody requestBody = new FormBody.Builder().add("studentCardId",studentId)
                .add("studentPassword",studentPsw).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mdialog.dismiss();
                        showResult("服务器连接异常!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mdialog.dismiss();
                String str= null;
                try {
                    str = response.body().string();
                    Gson gson = new Gson();
                    Type mtype = new TypeToken<JsonReturnData<StudentsVo>>() {
                    }.getType();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    int code = jsonReturnData.getCode();
                    if(code == 300){
                        showResult("请求数据失败!");
                    }else if(code == 201){
                        showResult("账号或者密码错误!");
                    }else if(code == 200){
                        StudentsVo studentsVo = (StudentsVo) jsonReturnData.getData();
                        StudentDao.delStuden(LoginActivity.this,studentsVo.getStudentId());
                        StudentDao.addStuden(LoginActivity.this,studentsVo);
                        SetSpUtils.setSharedPreferences(LoginActivity.this,"logintype",type);
                        SetSpUtils.setSharedPreferences(LoginActivity.this,"studentid",studentsVo.getStudentId());
                        if(studentsVo.getDepartmentId() == 0 || studentsVo.getClassId() == 0 || studentsVo.getRoomId() ==0){
                            IntentUtil.startActivtyWithFinish(LoginActivity.this, StudentRegisterActivity.class);
                        }
                        else{
                            IntentUtil.startActivtyWithFinish(LoginActivity.this, StudentMainActivity.class);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(LoginActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String musername = tl_username.getEditText().getText().toString();
        String mpassword = tl_password.getEditText().getText().toString();
        if (StringVerify.validateUserName(musername) && StringVerify.validatePassword(mpassword)) {
            button.setEnabled(true);
            button.setAlpha(1f);
        }else{
            button.setEnabled(false);
            button.setAlpha(0.5f);
        }
    }

    @Override
    public void onBackPressed() {
        ExistDialog.showExist(LoginActivity.this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
    }
}