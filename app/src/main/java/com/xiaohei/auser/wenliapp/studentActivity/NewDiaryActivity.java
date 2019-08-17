package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.StudentDao;
import com.xiaohei.auser.wenliapp.Dao.WeekTextDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.utils.Trans;
import com.xiaohei.auser.wenliapp.wenlievent.NetEvent;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.Result;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbWeeksText;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Auser on 2018/4/17.
 * 周记填写提交界面
 */

public class NewDiaryActivity extends SuperActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher {

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;
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
    int study = Trans.ONE_NUM;
    int health = Trans.ONE_NUM;
    int mood = Trans.ONE_NUM;
    int consume = Trans.ZERO_NUM;
    int returnhome = Trans.ZERO_NUM;
    int sleep = Trans.ONE_NUM;
    int love = Trans.ZERO_NUM;

    private Dialog mdialog;
    private String roomid;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdiary);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        roomid = StudentDao.getStudent().getRoomId();
        DbWeeksText weeksText = WeekTextDao.getWeekText(roomid);
        if(weeksText != null){
            initview(weeksText);
        }
        mdialog = XhDialog.showLoadingDialog(NewDiaryActivity.this,"正在加载...");
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
        ly_return.setOnClickListener(this);
        img_send.setOnClickListener(this);
    }

    private void test(RadioButton radioButton, int status) {
        if (status == 1) {
            radioButton.setChecked(true);
        }
    }

    private void initview(DbWeeksText weeksText) {

        if(weeksText.getStudyStatus() == Trans.ONE_NUM){
            diary_study_1.setChecked(true);
        }else if(weeksText.getStudyStatus() == Trans.TWO_NUM){
            diary_study_2.setChecked(true);
        }else if (weeksText.getStudyStatus() == Trans.THREE_NUM){
            diary_study_3.setChecked(true);
        }

        study = weeksText.getStudyStatus();
        if(weeksText.getHealthStatus() == Trans.ONE_NUM){
            diary_heath_1.setChecked(true);
        }else if(weeksText.getHealthStatus() == Trans.TWO_NUM){
            diary_heath_2.setChecked(true);
        }else if(weeksText.getHealthStatus() == Trans.THREE_NUM){
            diary_heath_3.setChecked(true);
        }

        health = weeksText.getHealthStatus();
        if(weeksText.getMoodStatus() == Trans.ONE_NUM){
            diary_mood_1.setChecked(true);
        }else if(weeksText.getMoodStatus() == Trans.TWO_NUM){
            diary_mood_2.setChecked(true);
        }else if(weeksText.getMoodStatus() == Trans.THREE_NUM){
            diary_mood_3.setChecked(true);
        }

        mood = weeksText.getMoodStatus();
        if(weeksText.getConsumeStatus() == Trans.ZERO_NUM){
            diary_consume_1.setChecked(true);
        }else if(weeksText.getConsumeStatus() == Trans.FOUR_NUM){
            diary_consume_2.setChecked(true);
        }else if(weeksText.getConsumeStatus() == Trans.FIVE_NUM){
            diary_consume_3.setChecked(true);
        }

        consume = weeksText.getConsumeStatus();
        if(weeksText.getReturnRoomStatus() == Trans.ZERO_NUM){
            diary_return_1.setChecked(true);
        }else if(weeksText.getReturnRoomStatus() == Trans.FOUR_NUM){
            diary_return_2.setChecked(true);
        }else if(weeksText.getReturnRoomStatus() == Trans.FIVE_NUM){
            diary_return_3.setChecked(true);
        }

        returnhome = weeksText.getReturnRoomStatus();
        if(weeksText.getSleepStatus() == Trans.ONE_NUM){
            diary_sleep_1.setChecked(true);
        }else if(weeksText.getSleepStatus() == Trans.TWO_NUM){
            diary_sleep_2.setChecked(true);
        }else if(weeksText.getSleepStatus() == Trans.THREE_NUM){
            diary_sleep_3.setChecked(true);
        }

        sleep = weeksText.getSleepStatus();
        if(weeksText.getLoveStatus() == Trans.ZERO_NUM){
            diary_lovelose_1.setChecked(true);
        }else if(weeksText.getLoveStatus() == Trans.SEVEN_NUM){
            diary_lovelose_2.setChecked(true);
        }else if(weeksText.getLoveStatus() == Trans.SIX_NUM){
            diary_lovelose_3.setChecked(true);
        }

        love = weeksText.getLoveStatus();
        diary_text.setText(weeksText.getConditionText());
        index = weeksText.getConditionText().length();
        textView_maxnumber.setText(index +"/120");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ly_return) {
            SaveDate();
            finish();
        }
        else if(v.getId() == R.id.img_send){

            XhDialog.DialogWithListener(NewDiaryActivity.this, "提示", "是否确认发送？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sendMes(WenLiURL.SEND_WEEKTEXT);
                }
            });
        }
    }

    private void sendMes(String url) {
        mdialog.show();
        SaveDate();
        String text;
        if(diary_text.getText().toString().trim().equals("")){
            text = "无情况反映";
        }else{
            text = diary_text.getText().toString();
        }
        RequestBody requestBody = new FormBody.Builder()
                .add(WenLiURL.SEND_WEEKTEXT_ROOMID,roomid+"")
                .add(WenLiURL.SEND_WEEKTEXT_STUDYSTATUS,study+"")
                .add(WenLiURL.SEND_WEEKTEXT_MOODSTATUS,mood+"")
                .add(WenLiURL.SEND_WEEKTEXT_HEALTHSTATUS,health+"")
                .add(WenLiURL.SEND_WEEKTEXT_RETURNROOMSTATUS,returnhome+"")
                .add(WenLiURL.SEND_WEEKTEXT_SLEEPSTATUS,sleep+"")
                .add(WenLiURL.SEND_WEEKTEXT_CONSUMESTATUS,consume+"")
                .add(WenLiURL.SEND_WEEKTEXT_LOVESTATUS,love+"")
                .add(WenLiURL.SEND_WEEKTEXT_CONDITIONTEXT,text)
                .add(WenLiURL.SEND_WEEKTEXT_STATUS,0+"")
                .build();

        XhLogUtil.d(text);

        Type mtype = new TypeToken<Result<String>>() {
        }.getType();
        XhOkHttps.PostRequestWithToken(url, requestBody, XhSp.getSharedPreferencesForString(NewDiaryActivity.this, SpConstants.TOKEN) ,mtype, new XhHttpInterface() {
                    @Override
                    public void complied(Result result) {
                        mdialog.dismiss();
                        int code = result.getCode();
                        if(code == 200){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    EventBus.getDefault().post(new NetEvent(true));
                                    XhDialog.DialogNoCancleWithListener(NewDiaryActivity.this, "提示", "发送成功！", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    });
                                }
                            });
                        }else if(code == 888){
                            XhDialog.DialogNoCancleWithListener(NewDiaryActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    exit();
                                    XhSp.setSharedPreferences(NewDiaryActivity.this, SpConstants.TOKEN,"");
                                    IntentUtil.startActivtyWithFinish(NewDiaryActivity.this, LoginActivity.class);
                                }
                            });
                        }else if (code == 500){
                            XhSnackBar.showResult(layout,"发送失败，请重试！");
                        }
                    }

                    @Override
                    public void fail() {
                        mdialog.dismiss();
                        XhSnackBar.showResult(layout,"服务器连接异常!");
                    }
                });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (group.getId()){
            case R.id.diary_study:{
                if(checkedId == R.id.diary_study_1){
                    study = Trans.ONE_NUM;
                }else if(checkedId == R.id.diary_study_2){
                    study = Trans.TWO_NUM;
                }else if(checkedId == R.id.diary_study_3){
                    study = Trans.THREE_NUM;
                }
            }
            break;
            case R.id.diary_heath:{
                if(checkedId == R.id.diary_heath_1){
                    health = Trans.ONE_NUM;
                }else if(checkedId == R.id.diary_heath_2){
                    health = Trans.TWO_NUM;
                }else if(checkedId == R.id.diary_heath_3){
                    health = Trans.THREE_NUM;
                }
            }
            break;
            case R.id.diary_mood:{
                if(checkedId == R.id.diary_mood_1){
                    mood = Trans.ONE_NUM;
                }else if(checkedId == R.id.diary_mood_2){
                    mood = Trans.TWO_NUM;
                }else if(checkedId == R.id.diary_mood_3){
                    mood = Trans.THREE_NUM;
                }
            }
            break;
            case R.id.diary_consume:{
                if(checkedId == R.id.diary_consume_1){
                    consume = Trans.ZERO_NUM;
                }else if(checkedId == R.id.diary_consume_2){
                    consume = Trans.FOUR_NUM;
                }else if(checkedId == R.id.diary_consume_3){
                    consume = Trans.FIVE_NUM;
                }
            }
            break;
            case R.id.diary_return:{
                if(checkedId == R.id.diary_return_1){
                    returnhome = Trans.ZERO_NUM;
                }else if(checkedId == R.id.diary_return_2){
                    returnhome = Trans.FOUR_NUM;
                }else if(checkedId == R.id.diary_return_3){
                    returnhome = Trans.FIVE_NUM;
                }
            }
            break;
            case R.id.diary_sleep:{
                if(checkedId == R.id.diary_sleep_1){
                    sleep = Trans.ONE_NUM;
                }else if(checkedId == R.id.diary_sleep_2){
                    sleep = Trans.TWO_NUM;
                }else if(checkedId == R.id.diary_sleep_3){
                    sleep = Trans.THREE_NUM;
                }
            }
            break;
            case R.id.diary_lovelose:{
                if(checkedId == R.id.diary_lovelose_1){
                    love = Trans.ZERO_NUM;
                }else if(checkedId == R.id.diary_lovelose_2){
                    love = Trans.SEVEN_NUM;
                }else if(checkedId == R.id.diary_lovelose_3){
                    love = Trans.SIX_NUM;
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DbWeeksText weeksText = new DbWeeksText();
                weeksText.setRoomId(roomid);
                weeksText.setStudyStatus(study);
                weeksText.setHealthStatus(health);
                weeksText.setMoodStatus(mood);
                weeksText.setConsumeStatus(consume);
                weeksText.setReturnRoomStatus(returnhome);
                weeksText.setSleepStatus(sleep);
                weeksText.setLoveStatus(love);
                weeksText.setConditionText(diary_text.getText().toString());
                weeksText.setId(1);
                WeekTextDao.saveWeekText(weeksText,roomid);
            }
        });

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
