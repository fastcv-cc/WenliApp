package com.xiaohei.auser.wenliapp.normalActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.dao.StudentDao;
import com.xiaohei.auser.wenliapp.dialog.NormalDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.NetEvent;
import com.xiaohei.auser.wenliapp.net.NormalUrl;
import com.xiaohei.auser.wenliapp.sp.StudentSpUtils;
import com.xiaohei.auser.wenliapp.sp.TeacherSpUtils;
import com.xiaohei.auser.wenliapp.studentActivity.NewDiaryActivity;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

import org.greenrobot.eventbus.EventBus;

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
 * Created by Auser on 2018/4/23.
 */

public class IdealActivity extends Activity implements TextWatcher {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.rl_layout)
    RelativeLayout layout;

    @BindView(R.id.ideal_content)
    EditText content;
    @BindView(R.id.ideal_sumbit)
    Button button;
    @BindView(R.id.maxnumber)
    TextView textView_maxnumber;

    private Dialog mdialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myideal);
        ButterKnife.bind(this);
        mdialog = ShowDialog.showLoadingDialog(IdealActivity.this,"正在发送...");
        content.addTextChangedListener(this);
    }

    @OnClick(R.id.ideal_sumbit)
    public void sendIdeal() {
        mdialog.show();
        String mcontent = content.getText().toString();
        int studentid = StudentSpUtils.getStudentId(IdealActivity.this);
        int teacherid = TeacherSpUtils.getTeacherId(IdealActivity.this);
        int id= studentid+teacherid;
        RequestBody requestBody = new FormBody.Builder()
                .add("id",id+"")
                .add("content",mcontent)
                .build();
        Request request = new Request.Builder().url(NormalUrl.sendIdeal()).post(requestBody).build();
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
                    else if(code == 500){
                        showResult("发送失败!");
                    }
                    else if(code == 200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NormalDialog.DialogListener(IdealActivity.this, "提示", "发送成功！", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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
                    Log.d("Login_Student_result",str);
                }
            }
        });
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int index = content.getText().toString().length();
        textView_maxnumber.setText(index +"/120");
    }
}
