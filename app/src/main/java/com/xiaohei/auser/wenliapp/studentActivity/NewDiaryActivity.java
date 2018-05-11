package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.dao.StudentDao;
import com.xiaohei.auser.wenliapp.dialog.NormalDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.NetEvent;
import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.sp.StudentSpUtils;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Auser on 2018/4/17.
 * 周记填写提交界面
 */

public class NewDiaryActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.img_send)
    ImageView img_send;
    //1、学习情况
    @BindView(R.id.diary_study)
    RadioGroup diary_study;
    @BindView(R.id.diary_study_1)
    RadioButton diary_study_1;
    @BindView(R.id.diary_study_2)
    RadioButton diary_study_2;
    @BindView(R.id.diary_study_3)
    RadioButton diary_study_3;
    //2、健康情况
    @BindView(R.id.diary_heath)
    RadioGroup diary_heath;
    @BindView(R.id.diary_heath_1)
    RadioButton diary_heath_1;
    @BindView(R.id.diary_heath_2)
    RadioButton diary_heath_2;
    @BindView(R.id.diary_heath_3)
    RadioButton diary_heath_3;
    //3、情绪情况
    @BindView(R.id.diary_mood)
    RadioGroup diary_mood;
    @BindView(R.id.diary_mood_1)
    RadioButton diary_mood_1;
    @BindView(R.id.diary_mood_2)
    RadioButton diary_mood_2;
    @BindView(R.id.diary_mood_3)
    RadioButton diary_mood_3;
    //4、消费情况
    @BindView(R.id.diary_consume)
    RadioGroup diary_consume;
    @BindView(R.id.diary_consume_1)
    RadioButton diary_consume_1;
    @BindView(R.id.diary_consume_2)
    RadioButton diary_consume_2;
    @BindView(R.id.diary_consume_3)
    RadioButton diary_consume_3;
    //5、归寝情况
    @BindView(R.id.diary_return)
    RadioGroup diary_return;
    @BindView(R.id.diary_return_1)
    RadioButton diary_return_1;
    @BindView(R.id.diary_return_2)
    RadioButton diary_return_2;
    @BindView(R.id.diary_return_3)
    RadioButton diary_return_3;
    //6、休息情况
    @BindView(R.id.diary_sleep)
    RadioGroup diary_sleep;
    @BindView(R.id.diary_sleep_1)
    RadioButton diary_sleep_1;
    @BindView(R.id.diary_sleep_2)
    RadioButton diary_sleep_2;
    @BindView(R.id.diary_sleep_3)
    RadioButton diary_sleep_3;
    //7、失恋情况
    @BindView(R.id.diary_lovelose)
    RadioGroup diary_lovelose;
    @BindView(R.id.diary_lovelose_1)
    RadioButton diary_lovelose_1;
    @BindView(R.id.diary_lovelose_2)
    RadioButton diary_lovelose_2;
    @BindView(R.id.diary_lovelose_3)
    RadioButton diary_lovelose_3;
    //8、备注
    @BindView(R.id.diary_text)
    EditText diary_text;
    @BindView(R.id.maxnumber)
    TextView textView_maxnumber;

    @BindView(R.id.ll_layout)
    LinearLayout layout;

    //设置默认值
    int study = 1;
    int health = 1;
    int mood = 1;
    int consume = 1;
    int returnhome = 1;
    int sleep = 1;
    int love = 1;

    private Dialog mdialog;

    int roomid;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdiary);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        roomid = StudentDao.getStudentRoomId(NewDiaryActivity.this,StudentSpUtils.getStudentId(NewDiaryActivity.this));
        WeeksText weeksText = StudentDao.getWeek(NewDiaryActivity.this,roomid);
        if(weeksText != null){
            initview(weeksText);
        }
        mdialog = ShowDialog.showLoadingDialog(NewDiaryActivity.this,"正在加载...");
        diary_study.setFocusable(true);
        diary_study.setFocusableInTouchMode(true);
        diary_study.requestFocus();
        diary_study.setOnCheckedChangeListener(this);
        diary_heath.setOnCheckedChangeListener(this);
        diary_mood.setOnCheckedChangeListener(this);
        diary_consume.setOnCheckedChangeListener(this);
        diary_return.setOnCheckedChangeListener(this);
        diary_sleep.setOnCheckedChangeListener(this);
        diary_lovelose.setOnCheckedChangeListener(this);
        diary_text.addTextChangedListener(this);
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
        img_send.setOnClickListener(this);
    }

    private void initview(WeeksText weeksText) {
        if(weeksText.getStudy() == 1){
            diary_study_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_study_2.setChecked(true);
        }else{
            diary_study_3.setChecked(true);
        }
        study = weeksText.getStudy();
        if(weeksText.getHealth() == 1){
            diary_heath_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_heath_2.setChecked(true);
        }else{
            diary_heath_3.setChecked(true);
        }
        health = weeksText.getHealth();
        if(weeksText.getMood() == 1){
            diary_mood_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_mood_2.setChecked(true);
        }else{
            diary_mood_3.setChecked(true);
        }
        mood = weeksText.getMood();
        if(weeksText.getConsume() == 1){
            diary_consume_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_consume_2.setChecked(true);
        }else{
            diary_consume_3.setChecked(true);
        }
        consume = weeksText.getConsume();
        if(weeksText.getReturnHome() == 1){
            diary_return_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_return_2.setChecked(true);
        }else{
            diary_return_3.setChecked(true);
        }
        returnhome = weeksText.getReturnHome();
        if(weeksText.getSleepCondition() == 1){
            diary_sleep_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_sleep_2.setChecked(true);
        }else{
            diary_sleep_3.setChecked(true);
        }
        sleep = weeksText.getSleepCondition();
        if(weeksText.getLoveLose() == 1){
            diary_lovelose_1.setChecked(true);
        }else if(weeksText.getStudy() == 2){
            diary_lovelose_2.setChecked(true);
        }else{
            diary_lovelose_3.setChecked(true);
        }
        love = weeksText.getLoveLose();
        diary_text.setText(weeksText.getConditionText());
        index = weeksText.getConditionText().length();
        textView_maxnumber.setText(index +"/120");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_return || v.getId() == R.id.img_return) {
            SaveDate();
            finish();
        }
        else if(v.getId() == R.id.img_send){
            sendMes(StudentUrl.sendMesUrl());
        }
    }

    private void sendMes(String url) {
        mdialog.show();
        SaveDate();
        String text;
        if(diary_text.getText().toString().trim().equals("")){
            text = diary_text.getHint().toString();
        }else{
            text = diary_text.getText().toString();
        }
        RequestBody requestBody = new FormBody.Builder().
                add("roomId",StudentDao.getStudentRoomId(NewDiaryActivity.this,StudentSpUtils.getStudentId(NewDiaryActivity.this))+"")
                .add("study",study+"")
                .add("health",health+"")
                .add("returnHome",returnhome+"")
                .add("sleepCondition",sleep+"")
                .add("mood",mood+"")
                .add("consume",consume+"")
                .add("loveLose",love+"")
                .add("conditionText",text)
                .build();
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
                    else if(code == 401){
                        showResult("添加失败!");
                    }
                    else if(code == 200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(new NetEvent(true));
                                NormalDialog.DialogListener(NewDiaryActivity.this,"提示","发送成功！");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(NewDiaryActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (group.getId()){
            case R.id.diary_study:{
                if(checkedId == R.id.diary_study_1){
                    study = 1;
                }else if(checkedId == R.id.diary_study_2){
                    study = 2;
                }else if(checkedId == R.id.diary_study_3){
                    study = 3;
                }
            }
            break;
            case R.id.diary_heath:{
                if(checkedId == R.id.diary_heath_1){
                    health = 1;
                }else if(checkedId == R.id.diary_heath_2){
                    health = 2;
                }else if(checkedId == R.id.diary_heath_3){
                    health = 3;
                }
            }
            break;
            case R.id.diary_mood:{
                if(checkedId == R.id.diary_mood_1){
                    mood = 1;
                }else if(checkedId == R.id.diary_mood_2){
                    mood = 2;
                }else if(checkedId == R.id.diary_mood_3){
                    mood = 3;
                }
            }
            break;
            case R.id.diary_consume:{
                if(checkedId == R.id.diary_consume_1){
                    consume = 1;
                }else if(checkedId == R.id.diary_consume_2){
                    consume = 2;
                }else if(checkedId == R.id.diary_consume_3){
                    consume = 3;
                }
            }
            break;
            case R.id.diary_return:{
                if(checkedId == R.id.diary_return_1){
                    returnhome = 1;
                }else if(checkedId == R.id.diary_return_2){
                    returnhome = 2;
                }else if(checkedId == R.id.diary_return_3){
                    returnhome = 3;
                }
            }
            break;
            case R.id.diary_sleep:{
                if(checkedId == R.id.diary_sleep_1){
                    sleep = 1;
                }else if(checkedId == R.id.diary_sleep_2){
                    sleep = 2;
                }else if(checkedId == R.id.diary_sleep_3){
                    sleep = 3;
                }
            }
            break;
            case R.id.diary_lovelose:{
                if(checkedId == R.id.diary_lovelose_1){
                    love = 1;
                }else if(checkedId == R.id.diary_lovelose_2){
                    love = 2;
                }else if(checkedId == R.id.diary_lovelose_3){
                    love = 3;
                }
            }
            break;
        }
    }

    /**
     * Integer roomId, Byte study, Byte health, Byte returnHome, Byte sleepCondition,
     * Byte mood, Byte consume, Byte loveLose, String conditionText
     */

    public void SaveDate() {
        StudentDao.delWeek(NewDiaryActivity.this,roomid);
        WeeksText weeksText = new WeeksText();
        weeksText.setRoomId(roomid);
        weeksText.setStudy((byte)study);
        weeksText.setHealth((byte)health);
        weeksText.setMood((byte)mood);
        weeksText.setConsume((byte)consume);
        weeksText.setReturnHome((byte)returnhome);
        weeksText.setSleepCondition((byte)sleep);
        weeksText.setLoveLose((byte)love);
        weeksText.setConditionText(diary_text.getText().toString());
        StudentDao.addWeek(NewDiaryActivity.this,weeksText);
    }

    @Override
    public void onBackPressed() {
        SaveDate();
        super.onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        index = diary_text.getText().toString().length();
        textView_maxnumber.setText(index +"/120");
    }
}
