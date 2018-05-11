package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.dao.StudentDao;
import com.xiaohei.auser.wenliapp.dialog.NormalDialog;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.Builds;
import com.xiaohei.auser.wenliapp.entity.Classes;
import com.xiaohei.auser.wenliapp.entity.Departments;
import com.xiaohei.auser.wenliapp.entity.Rooms;
import com.xiaohei.auser.wenliapp.entity.vo.StudentsVo;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.net.StudentUrl;
import com.xiaohei.auser.wenliapp.sp.StudentSpUtils;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

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
 * Created by Auser on 2018/4/25.
 * 第一次注册界面
 */

public class StudentRegisterActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.sp_dep)
    Spinner sp_dep;
    @BindView(R.id.sp_classes)
    Spinner sp_classes;
    @BindView(R.id.sp_build)
    Spinner sp_build;
    @BindView(R.id.sp_room)
    Spinner sp_room;

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.img_send)
    ImageView img_send;
    private Dialog mdialog;

    @BindView(R.id.ll_layout)
    LinearLayout layout;

    private List<Departments> weeklist;
    private List<Builds> builds;
    private List<Rooms> rooms;
    private List<Classes> classes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
        getDep(StudentUrl.getDepUrl());
        getBuild(StudentUrl.getBuildUrl());
    }

    private void getDep(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().build();
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
                    Type mtype = new TypeToken<JsonReturnData<List<Departments>>>() {
                    }.getType();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    int code = jsonReturnData.getCode();
                   if(code == 301){
                        showResult("服务器数据为空!");
                    }
                    else if(code == 200){
                       weeklist = (List<Departments>) jsonReturnData.getData();
                       final List<String> strs = new ArrayList<>();
                       strs.add("请选择系部");
                       for (int i=0;i<weeklist.size();i++){
                           strs.add(weeklist.get(i).getDepartmentName());
                       }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this,
                                        android.R.layout.simple_list_item_1, strs);
                                sp_dep.setAdapter(arrayAdapter);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentRegisterActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void getBuild(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().build();
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
                    Type mtype = new TypeToken<JsonReturnData<List<Builds>>>() {
                    }.getType();
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    int code = jsonReturnData.getCode();
                    if(code == 301){
                        showResult("服务器数据为空!");
                    }
                    else if(code == 200){
                        builds = (List<Builds>) jsonReturnData.getData();
                        final List<String> strs = new ArrayList<>();
                        strs.add("请选择楼栋:");
                        for (int i=0;i<builds.size();i++){
                            strs.add(builds.get(i).getBuildName());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this,
                                        android.R.layout.simple_list_item_1, strs);
                                sp_build.setAdapter(arrayAdapter);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentRegisterActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(StudentRegisterActivity.this,"正在加载...");
        sp_build.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    showResult("请选择楼栋");
                }else{
                    getRooms(builds.get(position-1).getBuildId());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp_dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    showResult("请选择班级");
                }else{
                    getClasses(weeklist.get(position-1).getDepartmentId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
        img_send.setOnClickListener(this);
    }

    private void getClasses(Integer departmentId) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("departmentId",departmentId+"").build();
        Request request = new Request.Builder().url(StudentUrl.getClassesUrl()).post(requestBody).build();
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
                    Type mtype = new TypeToken<JsonReturnData<List<Classes>>>() {
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
                        classes = (List<Classes>) jsonReturnData.getData();
                        final List<String> strs = new ArrayList<>();
                        strs.add("请选择班级:");
                        for (int i=0;i<classes.size();i++){
                            strs.add(classes.get(i).getClassName());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this,
                                        android.R.layout.simple_list_item_1, strs);
                                sp_classes.setAdapter(arrayAdapter);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentRegisterActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void getRooms(Integer buildId) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("buildId",buildId+"").build();
        Request request = new Request.Builder().url(StudentUrl.getRoomsUrl()).post(requestBody).build();
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
                    Type mtype = new TypeToken<JsonReturnData<List<Rooms>>>() {
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
                        rooms = (List<Rooms>) jsonReturnData.getData();
                        final List<String> strs = new ArrayList<>();
                        strs.add("请选择寝室号:");
                        for (int i=0;i<rooms.size();i++){
                            strs.add(rooms.get(i).getRoomName());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(StudentRegisterActivity.this,
                                        android.R.layout.simple_list_item_1, strs);
                                sp_room.setAdapter(arrayAdapter);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentRegisterActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_return || v.getId() == R.id.img_return) {
            IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
        }
        else if(v.getId() == R.id.img_send){
            String mes = "你选择的是:\n" +
                    weeklist.get(sp_dep.getSelectedItemPosition()-1).getDepartmentName() +
                    " "+classes.get(sp_classes.getSelectedItemPosition()-1).getClassName() + "\n"+
                    builds.get(sp_build.getSelectedItemPosition()-1).getBuildName() +
                    " "+rooms.get(sp_room.getSelectedItemPosition()-1).getRoomName()+"\n"+
                    "是否确认提交？";
            NormalDialog.DialogOneListener(StudentRegisterActivity.this, "提示", mes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendInfo(StudentUrl.sendInfoUrl());
                    BindTeahcer(StudentUrl.bindTeacher());
                }
            });
        }
    }

    private void BindTeahcer(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder()
                .add("departmentId",weeklist.get(sp_dep.getSelectedItemPosition()-1).getDepartmentId()+"")
                .add("roomId",rooms.get(sp_room.getSelectedItemPosition()-1).getRoomId()+"")
                .add("classId",classes.get(sp_classes.getSelectedItemPosition()-1).getClassId()+"").build();
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
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentRegisterActivity.this,"Login_Student_result",str);
                }
            }
        });
    }

    private void sendInfo(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder().add("studentId",StudentSpUtils.getStudentId(StudentRegisterActivity.this)+"")
                .add("departmentId",weeklist.get(sp_dep.getSelectedItemPosition()-1).getDepartmentId()+"")
                .add("roomId",rooms.get(sp_room.getSelectedItemPosition()-1).getRoomId()+"")
                .add("classId",classes.get(sp_classes.getSelectedItemPosition()-1).getClassId()+"").build();
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
                    else if(code == 500){
                        showResult("服务器错误!");
                    }
                    else if(code == 200){
                        StudentsVo student = StudentDao.getStudent(StudentRegisterActivity.this, StudentSpUtils.getStudentId(StudentRegisterActivity.this));
                        student.setDepartmentId(weeklist.get(sp_dep.getSelectedItemPosition()-1).getDepartmentId());
                        student.setDepartmentName(weeklist.get(sp_dep.getSelectedItemPosition()-1).getDepartmentName());
                        student.setClassId(classes.get(sp_classes.getSelectedItemPosition()-1).getClassId());
                        student.setClassName(classes.get(sp_classes.getSelectedItemPosition()-1).getClassName());
                        student.setBuildId(builds.get(sp_build.getSelectedItemPosition()-1).getBuildId());
                        student.setBuildName(builds.get(sp_build.getSelectedItemPosition()-1).getBuildName());
                        student.setRoomId(rooms.get(sp_room.getSelectedItemPosition()-1).getRoomId());
                        student.setRoomName(rooms.get(sp_room.getSelectedItemPosition()-1).getRoomName());
                        StudentDao.updateStuden(StudentRegisterActivity.this,student);
                        IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this,StudentMainActivity.class);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showResult("服务器数据解析异常!");
                } finally {
                    MyLog.Log(StudentRegisterActivity.this,"Login_Student_result",str);
                }
            }
        });
    }
}
