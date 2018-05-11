package com.xiaohei.auser.wenliapp.teacherActivity;

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
import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.entity.vo.WeeksTextVo;
import com.xiaohei.auser.wenliapp.net.TeacherUrl;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;
import com.xiaohei.auser.wenliapp.utils.Trans;

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
 * 用于教师展示周记历史的界面
 */

public class ShowWeekActivity extends Activity implements View.OnClickListener, TextWatcher {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.rl_layout)
    RelativeLayout layout;

    @BindView(R.id.week_time)
    TextView week_time;
    @BindView(R.id.week_studycondition)
    TextView week_studycondition;
    @BindView(R.id.week_heathcondition)
    TextView week_heathcondition;
    @BindView(R.id.week_returncondition)
    TextView week_returncondition;
    @BindView(R.id.week_sleepcondition)
    TextView week_sleepcondition;
    @BindView(R.id.week_moodcondition)
    TextView week_moodcondition;
    @BindView(R.id.week_consumecondition)
    TextView week_consumecondition;
    @BindView(R.id.week_lovelosecondition)
    TextView week_lovelosecondition;
    @BindView(R.id.week_conditiontext)
    TextView week_conditiontext;
    @BindView(R.id.week_text)
    EditText week_text;
    @BindView(R.id.img_send)
    ImageView send;
    @BindView(R.id.maxnumber)
    TextView textView_maxnumber;

    private WeeksText week;
    private WeeksTextVo weeksTextVo;
    private Dialog mdialog;
    private int flag;
    private String teachersReturnText;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_showweek);
        ButterKnife.bind(this);
        flag = getIntent().getExtras().getInt("flag");
        mdialog = ShowDialog.showLoadingDialog(ShowWeekActivity.this,"正在发送...");
        week_text.addTextChangedListener(this);
        if(flag == 1){
            week = (WeeksText) getIntent().getExtras().getSerializable("week");
            initview();
        }else if(flag ==2){
            weeksTextVo = (WeeksTextVo) getIntent().getExtras().getSerializable("week");
            initviewvo();
        }
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    private void initviewvo() {
        week_time.setText(weeksTextVo.getCreateTime());
        week_time.setFocusable(true);
        week_time.setFocusableInTouchMode(true);
        week_time.requestFocus();
        week_studycondition.setText(Trans.getStudy(weeksTextVo.getStudy()));
        week_heathcondition.setText(Trans.getHeath(weeksTextVo.getHealth()));
        week_returncondition.setText(Trans.getReturn(weeksTextVo.getReturnHome()));
        week_sleepcondition.setText(Trans.getSleep(weeksTextVo.getSleepCondition()));
        week_moodcondition.setText(Trans.getMood(weeksTextVo.getMood()));
        week_consumecondition.setText(Trans.getConsume(weeksTextVo.getConsume()));
        week_lovelosecondition.setText(Trans.getLoveLose(weeksTextVo.getLoveLose()));
        week_conditiontext.setText(weeksTextVo.getConditionText());
        week_text.setText(weeksTextVo.getTeachersReturnText());
        if(weeksTextVo.getTeachersReturnText() != null){
            index = weeksTextVo.getConditionText().length();
            textView_maxnumber.setText(index +"/120");
            send.setVisibility(View.GONE);
        }else{
            send.setVisibility(View.VISIBLE);
        }
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
    }

    private void initview() {
        week_time.setText(week.getCreateTime());
        week_time.setFocusable(true);
        week_time.setFocusableInTouchMode(true);
        week_time.requestFocus();
        week_studycondition.setText(Trans.getStudy(week.getStudy()));
        week_heathcondition.setText(Trans.getHeath(week.getHealth()));
        week_returncondition.setText(Trans.getReturn(week.getReturnHome()));
        week_sleepcondition.setText(Trans.getSleep(week.getSleepCondition()));
        week_moodcondition.setText(Trans.getMood(week.getMood()));
        week_consumecondition.setText(Trans.getConsume(week.getConsume()));
        week_lovelosecondition.setText(Trans.getLoveLose(week.getLoveLose()));
        week_conditiontext.setText(week.getConditionText());
        week_text.setText(week.getTeachersReturnText());
        if(week.getTeachersReturnText() != null){
            index = week.getConditionText().length();
            textView_maxnumber.setText(index +"/120");
            send.setVisibility(View.GONE);
        }else{
            send.setVisibility(View.VISIBLE);
        }
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @OnClick(R.id.img_send)
    public void Submit(View v){
        teachersReturnText = week_text.getText().toString();
        if(flag == 1){
            sendReturnText(TeacherUrl.sendReturnText(),week.getWeekTextId());
        }else if(flag ==2){
            sendReturnText(TeacherUrl.sendReturnText(),weeksTextVo.getWeekTextId());
        }
    }

    private void sendReturnText(String url,int weekid) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder()
                .add("weekTextId",weekid+"")
                .add("teachersReturnText",teachersReturnText).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mdialog.dismiss();
                MyLog.Log(ShowWeekActivity.this,"test","onFailure: 服务器连接异常!");
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
                    }else if(code == 500){
                        showResult("发送失败!");
                    }else if(code == 200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NormalDialog.DialogListener(ShowWeekActivity.this, "提示", "发送成功!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        setResult(3);
                                        finish();
                                    }
                                });
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MyLog.Log(ShowWeekActivity.this,"test","onResponse: 服务器数据解析异常 ");
                } finally {
                    Log.d("Login_Student_result",str);
                    MyLog.Log(ShowWeekActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        index = week_text.getText().toString().length();
        textView_maxnumber.setText(index +"/120");
    }
}
