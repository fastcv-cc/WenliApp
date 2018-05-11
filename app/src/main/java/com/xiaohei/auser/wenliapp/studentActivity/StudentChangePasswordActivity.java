package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.dialog.NormalDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.sp.StudentSpUtils;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

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
 * Created by Auser on 2018/4/17.
 * 用于学生修改密码的界面
 */

public class StudentChangePasswordActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;

    @BindView(R.id.old_psw)
    EditText old_psw;
    @BindView(R.id.new_psw)
    EditText new_psw;
    @BindView(R.id.new_psw2)
    EditText new_psw2;
    @BindView(R.id.psw_sumbit)
    Button button;
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
        setContentView(R.layout.activity_student_changepass);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(StudentChangePasswordActivity.this,"正在加载...");
        checkBox.setOnCheckedChangeListener(this);
        button.setOnClickListener(this);
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
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
        moldpsw = old_psw.getText().toString();
        mnewpsw = new_psw.getText().toString();
        String mnewpsw2 = new_psw2.getText().toString();
        if (mnewpsw.equals(mnewpsw2)) {
            SendMes(StudentUrl.changePsw());
        } else {
            showResult("两次输入的密码不一样!");
        }
    }

    private void SendMes(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("studentId",StudentSpUtils.getStudentId(StudentChangePasswordActivity.this)+"")
                .add("studentPassword",moldpsw).add("newPassword",mnewpsw).build();
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
                    Type mtype = new TypeToken<JsonReturnData<String>>() {
                    }.getType();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    int code = jsonReturnData.getCode();
                    if(code == 300){
                        showResult("请求数据失败!");
                    }
                    else if(code == 202){
                        showResult("密码错误!");
                    }
                    else if(code == 500){
                        showResult("修改失败!");
                    }
                    else if(code == 200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NormalDialog.DialogListener(StudentChangePasswordActivity.this, "提示", "修改成功！", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        setResult(2);
                                        finish();
                                    }
                                });
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentChangePasswordActivity.this,"Login_Student_result",str);
                }
            }
        });
    }
}
