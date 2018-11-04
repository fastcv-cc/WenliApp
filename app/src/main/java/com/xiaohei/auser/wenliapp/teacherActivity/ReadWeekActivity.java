package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.adapter.StudentWeekAdapter;
import com.xiaohei.auser.wenliapp.adapter.TeacherWeekAdapter;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.wenlientity.NewWeektext;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/23.
 * 老师展示周记的界面
 */

public class ReadWeekActivity extends SuperActivity implements View.OnLongClickListener, View.OnClickListener {

    @BindView(R.id.rl_layout)
    LinearLayout layout;

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;

    @BindView(R.id.la_empty)
    View v_empty;

    @BindView(R.id.weekreadlist)
    ListView weekreadlist;

    private Dialog mdialog;

    public List<NewWeektext> list_weeks = new ArrayList<>();
    private String roomid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readweek);
        ButterKnife.bind(this);
        roomid = getIntent().getExtras().getString("roomid");
        init();
        getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+ roomid);
    }

    private void init() {
        mdialog = XhDialog.showLoadingDialog(ReadWeekActivity.this,"正在加载...");
        weekreadlist.setAdapter(new StudentWeekAdapter(ReadWeekActivity.this, list_weeks));
        weekreadlist.setEmptyView(v_empty);
        weekreadlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("week", list_weeks.get(position));
                IntentUtil.MystartActivity(ReadWeekActivity.this,ShowWeekActivity.class, bundle,4);
            }
        });
        v_empty.setOnLongClickListener(this);
        ly_return.setOnClickListener(this);
    }

    public void getWeek(String url) {
        list_weeks.clear();
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NewWeektext>>>() {
        }.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(ReadWeekActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 300){
                    XhSnackBar.showResult(layout,"请求数据失败!");
                }
                else if(code == 301){
                    XhSnackBar.showResult(layout,"服务器数据为空!");
                }
                else if(code == 200){
                    list_weeks = (List<NewWeektext>) result.getResult();
                    if(list_weeks.size() == 0)
                        XhSnackBar.showResult(layout,"无周记信息");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        weekreadlist.setAdapter(new TeacherWeekAdapter(ReadWeekActivity.this, list_weeks));
                    }
                });
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResult(layout,"服务器连接异常!");
            }
        });
    }


    @Override
    public boolean onLongClick(View v) {
        getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+ roomid);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 4 && resultCode ==3){
            getWeek(WenLiURL.STUDENT_GET_WEEKTEXT+ roomid);
        }
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
