package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.wenlievent.WeekEvent;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.Trans;
import com.xiaohei.auser.wenliapp.wenlientity.NewWeektext;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

/**
 * Created by Auser on 2018/4/23.
 * 用于教师展示周记历史的界面
 */

public class ShowWeekActivity extends SuperActivity implements View.OnClickListener, TextWatcher {

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;
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
    @BindView(R.id.img_warn)
    ImageView warn;

    private NewWeektext week;
    private Dialog mdialog;
    private String teachersReturnText;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_showweek);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        week = (NewWeektext) getIntent().getExtras().getSerializable("week");
        init();
    }

    private void init() {
        ly_return.setOnClickListener(this);
        mdialog = XhDialog.showLoadingDialog(ShowWeekActivity.this,"正在发送...");
        week_text.addTextChangedListener(this);
        send.setOnClickListener(this);
        warn.setOnClickListener(this);
        initview();
    }


    private void initview() {
        week_time.setText(week.getCreateTime());
        week_time.setFocusable(true);
        week_time.setFocusableInTouchMode(true);
        week_time.requestFocus();
        week_studycondition.setText(Trans.getStatusName(week.getStudyStatus()));
        week_heathcondition.setText(Trans.getStatusName(week.getHealthStatus()));
        week_returncondition.setText(Trans.getStatusName(week.getReturnRoomStatus()));
        week_sleepcondition.setText(Trans.getStatusName(week.getSleepStatus()));
        week_moodcondition.setText(Trans.getStatusName(week.getMoodStatus()));
        week_consumecondition.setText(Trans.getStatusName(week.getConsumeStatus()));
        week_lovelosecondition.setText(Trans.getStatusName(week.getLoveStatus()));
        week_conditiontext.setText(week.getConditionText());
        if(week.getStatus() == 1 || week.getStatus() == 2){
            week_text.setText(week.getTeacherReturnText());
            index = week.getConditionText().length();
            textView_maxnumber.setText(index +"/120");
            send.setVisibility(View.GONE);
            warn.setVisibility(View.GONE);
        }else{
            send.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_return:
                finish();
                break;
            case R.id.img_send:{
                teachersReturnText = week_text.getText().toString();
                sendReturnText(WenLiURL.TEACHER_RETURN_TEXT,week.getId(),teachersReturnText);
            }
            break;
            case R.id.img_warn:{
                XhDialog.DialogWithListener(ShowWeekActivity.this, "提示", "是否确认发送异常信息！！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SendWarnMes();
                    }
                });
            }
            break;
        }
    }

    private void SendWarnMes() {
        //发送短信
        mdialog.show();
        FormBody requestBody = new FormBody.Builder()
                .add("id",week.getId())
                .build();
        Type mtype = new TypeToken<Result<String>>() {
        }.getType();
        XhOkHttps.PostRequestWithToken(WenLiURL.TEACHER_WARN, requestBody, XhSp.getSharedPreferencesForString(ShowWeekActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if (code == 200){
                    XhSnackBar.showResult(layout,"提交成功！");
                }else if(code == 500){
                    XhSnackBar.showResult(layout,"提交失败！");
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                        XhSnackBar.showResult(layout,"提交失败！");
            }
        });

    }

    WeekEvent weekEvent = new WeekEvent(false);

    private void sendReturnText(String url,String weekid,String teachersReturnText) {
        mdialog.show();
        FormBody requestBody = new FormBody.Builder()
                .add("id",weekid+"")
                .add("teacherReturnText",teachersReturnText)
                .build();
        Type mtype = new TypeToken<Result<String>>() {
        }.getType();
        XhOkHttps.PutRequest(url, XhSp.getSharedPreferencesForString(ShowWeekActivity.this, SpConstants.TOKEN), mtype, requestBody, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
               if(code == 500){
                    XhSnackBar.showResult(layout,"发送失败!");
                }else if(code == 200){
                    weekEvent.setRead(true);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            XhDialog.DialogNoCancleWithListener(ShowWeekActivity.this,"提示", "发送成功!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setResult(3);
                                    finish();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResult(layout,"发送失败!");
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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(weekEvent);
        super.onDestroy();
    }
}
