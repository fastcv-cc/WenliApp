package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.StudentDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.adapter.StudentSelectAdapter;
import com.xiaohei.auser.wenliapp.adapter.StudentWeekAdapter;
import com.xiaohei.auser.wenliapp.basedata.BaseData;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.wenlievent.NetEvent;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.normalActivity.SettingActivity;
import com.xiaohei.auser.wenliapp.receiver.NetReceiver;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.NetStateUtils;
import com.xiaohei.auser.wenliapp.wenlientity.NewWeektext;
import com.xiaohei.auser.wenliapp.wenlientity.Result;
import com.xiaohei.auser.wenliapp.wenlientity.RoomWIthHeadID;
import com.xiaohei.auser.wenliapp.widget.FreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/16.
 * 学生的主界面
 * 功能要求：
 * 1、可以看到历史周记记录
 * 2、可以发送周记
 * 3、可以查看自己的信息
 * 4、可以修改账户密码
 * 5、可以反馈使用意见
 */

public class StudentMainActivity extends SuperActivity implements AdapterView.OnItemClickListener,
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
    public List<NewWeektext> list_weeks = new ArrayList<>();
    private NetReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
        initReceive();
        EventBus.getDefault().register(this);
        getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),0);
    }

    //初始化组件信息
    private void init() {
        mdialog = XhDialog.showLoadingDialog(StudentMainActivity.this,"正在加载...");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        tv_name.setText(StudentDao.getStudent().getName());
        list_select.setAdapter(new StudentSelectAdapter(StudentMainActivity.this, BaseData.getSelectListForStudent()));
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

    //得到该寝室的所有周记信息  ok
    public void getWeek(String url, final int mode) {
        list_weeks.clear();
        Type mtype = new TypeToken<Result<List<NewWeektext>>>() {
        }.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(StudentMainActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                int code = result.getCode();
                if(code == 200){
                    list_weeks = (List<NewWeektext>) result.getResult();
                    if(list_weeks.size() == 0)
                        XhSnackBar.showResult(layout,"你们寝室真懒，居然还没有写过周记");
                }else if(code == 888){
                     XhDialog.DialogNoCancleWithListener(StudentMainActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             exit();
                             XhSp.setSharedPreferences(StudentMainActivity.this, SpConstants.TOKEN,"");
                             IntentUtil.startActivtyWithFinish(StudentMainActivity.this, LoginActivity.class);
                         }
                     });
                }else if(code == 500){
                    XhSnackBar.showResultWithAction(layout, "服务器跑丢了", "点我追回", new XhSnackBar.Action() {
                        @Override
                        public void actionMethod() {
                            getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),0);
                        }
                    });
                }
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

            @Override
            public void fail() {
                if(mode == 1){
                    listview_week.reflashComplete(true);
                }
                mdialog.dismiss();
                XhSnackBar.showResult(layout,"服务器连接异常!");
            }
        });
    }

    //初始化网络变化监测广播
    private void initReceive() {
        mReceiver = new NetReceiver();
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
            getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),0);
        }
    }

    public void setNetState(boolean netState) {
        if (netinfo != null) {
            netinfo.setVisibility(netState ? View.GONE : View.VISIBLE);
            netinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    NetStateUtils.startToSettings(StudentMainActivity.this);
                }
            });
        }
    }

    //功能选项
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:   //我的信息
                IntentUtil.MystartActivity(StudentMainActivity.this,StudentInfoActivity.class);
            break;
            case 1:   //新建周记
                getHeadID(WenLiURL.STUDENT_GET_HEADID+StudentDao.getStudent().getRoomId());
            break;
            case 2:   //修改密码
                IntentUtil.MystartActivity(StudentMainActivity.this,StudentChangePasswordActivity.class);
            break;
            case 3:   //查询成绩
                IntentUtil.startWeb(StudentMainActivity.this);
            break;
            case 4:   //退出登录
            {
                XhSp.setSharedPreferences(StudentMainActivity.this, SpConstants.TOKEN,"");
                IntentUtil.startActivtyWithFinish(StudentMainActivity.this, LoginActivity.class);
            }
            break;
            case 5:   //设置界面
                IntentUtil.MystartActivity(StudentMainActivity.this, SettingActivity.class);
            break;
        }
    }

    //组件的点击事件
    @Override
    public void onClick(View v) {
        getHeadID(WenLiURL.STUDENT_GET_HEADID+StudentDao.getStudent().getRoomId());
    }

    //得到寝室长ID的请求
    private void getHeadID(String url) {
        mdialog.show();
        Type mtype = new TypeToken<Result<RoomWIthHeadID>>() {
        }.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(StudentMainActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 200){
                    RoomWIthHeadID rooms = (RoomWIthHeadID) result.getResult();
                    if(rooms.getStudentId() != null) {
                        if (rooms.getStudentId().equals(StudentDao.getStudent().getStudentid())) {
                            IntentUtil.MystartActivity(StudentMainActivity.this, NewDiaryActivity.class,1);
                        }else{
                            XhSnackBar.showResult(layout,"您还不是寝室长！");
                        }
                    }else{
                        XhSnackBar.showResult(layout,"您还不是寝室长！");
                    }
                }else if(code == 888){
                    XhDialog.DialogNoCancleWithListener(StudentMainActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            exit();
                            XhSp.setSharedPreferences(StudentMainActivity.this, SpConstants.TOKEN,"");
                            IntentUtil.startActivtyWithFinish(StudentMainActivity.this, LoginActivity.class);
                        }
                    });
                }else if(code == 500){
                    XhSnackBar.showResultWithAction(layout, "服务器跑丢了", "点我追回", new XhSnackBar.Action() {
                        @Override
                        public void actionMethod() {
                            getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),0);
                        }
                    });
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResultWithAction(layout, "服务器跑丢了", "点我追回", new XhSnackBar.Action() {
                    @Override
                    public void actionMethod() {
                        getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),0);
                    }
                });
            }
        });
    }

    //刷新时调用的接口
    @Override
    public void onReflash() {
        getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),1);
    }

    //长按空白屏幕刷新
    @Override
    public boolean onLongClick(View v) {
        getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+StudentDao.getStudent().getRoomId(),0);
        return false;
    }

    //按下返回键的操作
    @Override
    public void onBackPressed() {
        BackExit();
    }

    //注销广播信息
    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}