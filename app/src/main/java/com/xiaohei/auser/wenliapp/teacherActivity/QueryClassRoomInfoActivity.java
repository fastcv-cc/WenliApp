package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.xiaohei.auser.wenliapp.adapter.StudentInfoAdapter;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.dialog.NormalDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.Rooms;
import com.xiaohei.auser.wenliapp.entity.vo.StudentsVo;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.net.TeacherUrl;
import com.xiaohei.auser.wenliapp.sp.TeacherSpUtils;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

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
 * 用于展示某个班级某个寝室的成员信息
 */

public class QueryClassRoomInfoActivity extends Activity implements View.OnLongClickListener {

    @BindView(R.id.rl_layout)
    LinearLayout layout;

    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_to)
    TextView tv_to;
    @BindView(R.id.img_to)
    ImageView img_to;

    @BindView(R.id.la_empty)
    View v_empty;


    @BindView(R.id.stuinfolist)
    ListView listView;

    private int headid = 0;
    private int roomid = 0;
    private Dialog mdialog;
    private List<StudentsVo> voList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_room_info);
        roomid = getIntent().getExtras().getInt("roomid");
        ButterKnife.bind(this);
        init();
        getHeadID();
    }

    private void getStudents(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId", roomid+"").build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mdialog.dismiss();
                showResult("服务器连接异常!");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mdialog.dismiss();
                String str= null;
                try {
                    str = response.body().string();
                    Gson gson = new Gson();
                    Type mtype = new TypeToken<JsonReturnData<List<StudentsVo>>>() {
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
                        voList = (List<StudentsVo>) jsonReturnData.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(new StudentInfoAdapter(QueryClassRoomInfoActivity.this,voList,headid));
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(QueryClassRoomInfoActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(QueryClassRoomInfoActivity.this,"正在加载...");
        listView.setEmptyView(v_empty);
        v_empty.setOnLongClickListener(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                NormalDialog.LongDialogListener(QueryClassRoomInfoActivity.this, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            /**
                             * 恢复初始密码
                             */
                            initPsw(TeacherUrl.initPsw(),voList.get(position).getStudentId());

                        } else if(which == 1){
                            /**
                             * 设置寝室长
                             */
                            if(headid == voList.get(position).getStudentId()){
                                showResult("该学生已为寝室长!");
                            }else{
                                setHead(TeacherUrl.setHead(),voList.get(position).getStudentId());
                            }
                        }
                    }
                });
                return false;
            }
        });
    }

    private void setHead(String url, final int studentid) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId", roomid+"")
                .add("studentId",studentid+"").build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mdialog.dismiss();
                showResult("服务器连接异常!");
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
                        showResult("修改失败，服务器异常!");
                    }
                    else if(code == 200){
                        showResult("修改成功!");
                        headid = studentid;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(new StudentInfoAdapter(QueryClassRoomInfoActivity.this,voList,headid));
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(QueryClassRoomInfoActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void initPsw(String url, final int studentid) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId", roomid+"")
                .add("studentId",studentid+"")
                .add("teacherId", TeacherSpUtils.getTeacherId(QueryClassRoomInfoActivity.this)+"")
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mdialog.dismiss();
                showResult("服务器连接异常!");
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
                        showResult("修改失败，服务器异常!");
                    }
                    else if(code == 200){
                        showResult("修改成功!");
                        headid = studentid;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listView.setAdapter(new StudentInfoAdapter(QueryClassRoomInfoActivity.this,voList,headid));
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(QueryClassRoomInfoActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
    }

    @OnClick({R.id.img_to,R.id.tv_to})
    public void ToOnclick(View view){
        Bundle bundle = new Bundle();
        bundle.putInt("roomid",roomid);
        IntentUtil.MystartActivity(QueryClassRoomInfoActivity.this,ReadWeekActivity.class,bundle);
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    private void getHeadID() {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("roomId", roomid+"").build();
        Request request = new Request.Builder().url(StudentUrl.getHeadUrl()).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                        mdialog.dismiss();
                        showResult("服务器连接异常!");
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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(QueryClassRoomInfoActivity.this,"Login_Student_result",str);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getStudents(TeacherUrl.getStudents());
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        getHeadID();
        return true;
    }
}
