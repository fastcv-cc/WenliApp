package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.StudentSelectAdapter;
import com.xiaohei.auser.wenliapp.adapter.StudentWeekAdapter;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.basedata.StuSelectData;
import com.xiaohei.auser.wenliapp.dao.StudentDao;
import com.xiaohei.auser.wenliapp.dialog.ExistDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.NetEvent;
import com.xiaohei.auser.wenliapp.entity.Rooms;
import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.normalActivity.SettingActivity;
import com.xiaohei.auser.wenliapp.receiver.NetReceiver;
import com.xiaohei.auser.wenliapp.sp.StudentSpUtils;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;
import com.xiaohei.auser.wenliapp.utils.NetUtils;
import com.xiaohei.auser.wenliapp.widget.FreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
 * Created by Auser on 2018/4/16.
 * 学生的主界面
 */

public class StudentMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener ,FreshListView.IReflashListener, View.OnLongClickListener {

    @BindView(R.id.ll_layout)
    LinearLayout layout;
    @BindView(R.id.list_select)
    ListView list_select;
    @BindView(R.id.list_week)
    FreshListView listview_week;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.img_add)
    ImageView img_add;
    @BindView(R.id.tv_student_name)
    TextView tv_name;
    @BindView(R.id.la_empty)
    View v_empty;
    @BindView(R.id.net_view_rl)
    RelativeLayout netinfo;

    private Dialog mdialog;

    public List<WeeksText> list_weeks = new ArrayList<>();
    private int headid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        ButterKnife.bind(this);
        init();
        initReceive();
        EventBus.getDefault().register(this);
        getWeek(StudentUrl.getmWeekUrl(),0);
    }

    public void getWeek(String url, final int mode) {
        list_weeks.clear();
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId",StudentDao.getStudentRoomId(StudentMainActivity.this,
                StudentSpUtils.getStudentId(StudentMainActivity.this))+"").build();
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
                    MyLog.Log(StudentMainActivity.this,"Login_Student_result",str);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(mode == 1){
                                listview_week.reflashComplete(true);
                            }
                            listview_week.setAdapter(new StudentWeekAdapter(StudentMainActivity.this, list_weeks));
                        }
                    });
                }
            }
        });

    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    private void initReceive() {
        NetReceiver mReceiver = new NetReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetEvent event) {
        setNetState(event.isNet());
    }

    @Subscribe
    public void onEventFresh(NetEvent event) {
        if(event.isNet()){
            getWeek(StudentUrl.getmWeekUrl(),0);
        }
    }

    public void setNetState(boolean netState) {

        if (netinfo != null) {
            netinfo.setVisibility(netState ? View.GONE : View.VISIBLE);
            netinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    NetUtils.startToSettings(StudentMainActivity.this);
                }
            });
        }
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(StudentMainActivity.this,"正在加载...");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        tv_name.setText(StudentDao.getStudentName(StudentMainActivity.this,StudentSpUtils.getStudentId(StudentMainActivity.this)));
        list_select.setAdapter(new StudentSelectAdapter(StudentMainActivity.this, StuSelectData.getSelectList()));
        list_select.setOnItemClickListener(this);
        listview_week.setInterface(this);
        listview_week.setAdapter(new StudentWeekAdapter(StudentMainActivity.this, list_weeks));
        listview_week.setEmptyView(v_empty);
        listview_week.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("week", list_weeks.get(position-1));
                IntentUtil.MystartActivity(StudentMainActivity.this, ShowWeekActivity.class, bundle);
            }
        });
        v_empty.setOnLongClickListener(this);
        img_add.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:{
                /**
                 * 我的信息  ok
                 */
                IntentUtil.MystartActivity(StudentMainActivity.this,StudentInfoActivity.class);
            }
            break;
            case 1:{
                /**
                 * 新建周记
                 */
                NewDiary();
            }
            break;
            case 2:{
                /**
                 * 修改密码
                 */
                IntentUtil.MystartActivity(StudentMainActivity.this,StudentChangePasswordActivity.class,2);
            }
            break;
            case 3:{
                /**
                 * 查询成绩
                 */
                IntentUtil.startWeb(StudentMainActivity.this);
            }
            break;
            case 4:{
                /**
                 * 退出登录
                 */
                IntentUtil.startActivtyWithFinish(StudentMainActivity.this, LoginActivity.class);
            }
            break;
            case 5:{
                /**
                 * 设置界面
                 */
                IntentUtil.MystartActivity(StudentMainActivity.this, SettingActivity.class);
            }
            break;
            default:{

            }
        }
    }

    @Override
    public void onClick(View v) {
        /**
         * 新建周记
         */
        NewDiary();
    }

    private void NewDiary() {
        getHeadID();
    }

    private void getHeadID() {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId",StudentDao.getStudentRoomId(StudentMainActivity.this,
                StudentSpUtils.getStudentId(StudentMainActivity.this))+"").build();
        Request request = new Request.Builder().url(StudentUrl.getHeadUrl()).post(requestBody).build();
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
                    Type mtype = new TypeToken<JsonReturnData<Rooms>>() {
                    }.getType();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    int code = jsonReturnData.getCode();
                    if(code == 300){
                        showResult("请求数据失败!");
                    }
                    else if(code == 200){
                        Rooms rooms = (Rooms) jsonReturnData.getData();
                        if(rooms != null && rooms.getStudentId() != null)
                        headid = rooms.getStudentId();
                        if(headid == StudentSpUtils.getStudentId(StudentMainActivity.this)){
                            IntentUtil.MystartActivity(StudentMainActivity.this,NewDiaryActivity.class);
                        }else{
                            showResult("您还不是寝室长！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentMainActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    @Override
    public void onReflash() {
        getWeek(StudentUrl.getmWeekUrl(),1);
    }

    @Override
    public boolean onLongClick(View v) {
       getWeek(StudentUrl.getmWeekUrl(),0);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 2 && resultCode == 2){
            IntentUtil.MystartActivity(StudentMainActivity.this,LoginActivity.class);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        ExistDialog.showExist(StudentMainActivity.this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
    }
}