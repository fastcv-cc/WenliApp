package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.StudentWeekAdapter;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.WeekEvent;
import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
 * 老师展示周记的界面
 */

public class ReadWeekActivity extends Activity implements View.OnLongClickListener {

    @BindView(R.id.rl_layout)
    LinearLayout layout;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.la_empty)
    View v_empty;

    @BindView(R.id.weekreadlist)
    ListView weekreadlist;

    private Dialog mdialog;
    private int roomid;

    public List<WeeksText> list_weeks = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readweek);
        ButterKnife.bind(this);
        roomid = getIntent().getExtras().getInt("roomid");
        init();
        getWeek(StudentUrl.getmWeekUrl());
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    public void getWeek(String url) {
        list_weeks.clear();
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId", roomid+"").build();
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
                    Type mtype = new TypeToken<JsonReturnData<List<WeeksText>>>() {
                    }.getType();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    int code = jsonReturnData.getCode();
                    if(code == 300){
                        showResult("请求数据失败!");
                    }
                    else if(code == 301){
                        showResult("服务器数据为空!");
                    }
                    else if(code == 200){
                        list_weeks = (List<WeeksText>) jsonReturnData.getData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(ReadWeekActivity.this,"Login_Student_result",str);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            weekreadlist.setAdapter(new StudentWeekAdapter(ReadWeekActivity.this, list_weeks));
                        }
                    });
                }
            }
        });

    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(ReadWeekActivity.this,"正在加载...");
        weekreadlist.setAdapter(new StudentWeekAdapter(ReadWeekActivity.this, list_weeks));
        weekreadlist.setEmptyView(v_empty);
        weekreadlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("flag", 1);
                bundle.putSerializable("week", list_weeks.get(position));
                IntentUtil.MystartActivity(ReadWeekActivity.this,ShowWeekActivity.class, bundle,4);
            }
        });
        v_empty.setOnLongClickListener(this);
    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
    }

    @Override
    public boolean onLongClick(View v) {
        getWeek(StudentUrl.getmWeekUrl());
        return false;
    }

    WeekEvent weekEvent = new WeekEvent(false);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 4 && resultCode ==3){
            weekEvent.setRead(true);
            getWeek(StudentUrl.getmWeekUrl());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(weekEvent);
        super.onDestroy();
    }
}
