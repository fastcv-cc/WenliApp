package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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
import com.xiaohei.auser.wenliapp.adapter.StudentInfoAdapter;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.NewStudent;
import com.xiaohei.auser.wenliapp.wenlientity.Result;
import com.xiaohei.auser.wenliapp.wenlientity.RoomWIthHeadID;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

/**
 * Created by Auser on 2018/4/23.
 * 用于展示某个班级某个寝室的成员信息
 */

public class QueryClassRoomInfoActivity extends SuperActivity implements View.OnLongClickListener, View.OnClickListener {

    @BindView(R.id.rl_layout)
    LinearLayout layout;

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;
    @BindView(R.id.ly_to)
    LinearLayout ly_to;

    @BindView(R.id.la_empty)
    View v_empty;
    @BindView(R.id.stuinfolist)
    ListView listView;

    private String headid ;
    private String roomid ;
    private Dialog mdialog;
    private List<NewStudent> students = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_room_info);
        roomid = getIntent().getExtras().getString("roomid");
        ButterKnife.bind(this);
        init();
        getHeadID(WenLiURL.STUDENT_GET_HEADID+ roomid);
    }

    private void init() {
        mdialog = XhDialog.showLoadingDialog(QueryClassRoomInfoActivity.this,"正在加载...");
        listView.setEmptyView(v_empty);
        ly_return.setOnClickListener(this);
        ly_to.setOnClickListener(this);
        v_empty.setOnLongClickListener(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                XhDialog.showStudentFunctionDialogListener(QueryClassRoomInfoActivity.this,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {   //恢复初始密码
                            initPsw(WenLiURL.TEACHER_INIT_PASSWORD,students.get(position).getId());
                        } else if(which == 1){   //设置寝室长
                            if(headid.equals(students.get(position).getId())){
                                XhSnackBar.showResult(layout,"该学生已为寝室长!");
                            }else{
                                setHead(WenLiURL.TEACHER_CHANGE_ROOM_HEAD,students.get(position).getId());
                            }
                        }
                    }
                });
                return false;
            }
        });
    }

    private void setHead(String url, final String studentid) {
        mdialog.show();
        FormBody requestBody = new FormBody.Builder()
                .add("id", roomid)
                .add("studentId",studentid)
                .build();
        Type mtype = new TypeToken<Result<String>>() {}.getType();
        XhOkHttps.PutRequest(url, XhSp.getSharedPreferencesForString(QueryClassRoomInfoActivity.this, SpConstants.TOKEN), mtype, requestBody, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 300){
                    XhSnackBar.showResult(layout,"请求数据失败!");
                }
                else if(code == 500){
                    XhSnackBar.showResult(layout,"修改失败，服务器异常!");
                }
                else if(code == 200){
                    XhSnackBar.showResult(layout,"修改成功!");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(new StudentInfoAdapter(QueryClassRoomInfoActivity.this,students,studentid));
                        }
                    });
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResult(layout,"服务器连接异常!");
            }
        });
    }

    private void initPsw(String url, String studentid) {
        mdialog.show();
        FormBody requestBody = new FormBody.Builder()
                .add("id",studentid)
                .build();
        Type mtype = new TypeToken<Result<String>>() {}.getType();
        XhOkHttps.PutRequest(url, XhSp.getSharedPreferencesForString(QueryClassRoomInfoActivity.this, SpConstants.TOKEN), mtype, requestBody, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 200){
                    XhSnackBar.showResult(layout,"修改成功!");
                }else if(code == 500){
                    XhSnackBar.showResult(layout,"修改失败，服务器异常!");
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResult(layout,"服务器连接异常!");
            }
        });
    }

    //得到寝室长ID的请求
    private void getHeadID(String url) {
        mdialog.show();
        Type mtype = new TypeToken<Result<RoomWIthHeadID>>() {
        }.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(QueryClassRoomInfoActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 200){
                    RoomWIthHeadID rooms = (RoomWIthHeadID) result.getResult();
                    headid = rooms.getStudentId();
                }else{
                    headid = null;
                }
                getStudents(WenLiURL.TEACHER_GET_ROOM_STUDENT+roomid);
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhSnackBar.showResult(layout,"服务器连接异常!");
            }
        });
    }


    private void getStudents(String url) {
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NewStudent>>>() {}.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(QueryClassRoomInfoActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 300){
                    XhSnackBar.showResult(layout,"请求数据失败!");
                }
                else if(code == 200){
                    students = (List<NewStudent>) result.getResult();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(new StudentInfoAdapter(QueryClassRoomInfoActivity.this,students,headid));
                        }
                    });
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
    public boolean onLongClick(View v) {
        getHeadID(WenLiURL.STUDENT_GET_HEADID+ roomid);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ly_return: {
                finish();
            }
            break;

            case R.id.ly_to:{
                Bundle bundle = new Bundle();
                bundle.putString("roomid",roomid);
                IntentUtil.MystartActivity(QueryClassRoomInfoActivity.this,ReadWeekActivity.class,bundle);
            }
            break;
        }
    }
}
