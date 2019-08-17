package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.StudentDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.dialog.XhSnackBar;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.utils.XhLogUtil;
import com.xiaohei.auser.wenliapp.wenlientity.NewBuild;
import com.xiaohei.auser.wenliapp.wenlientity.NewClasses;
import com.xiaohei.auser.wenliapp.wenlientity.NewDepartment;
import com.xiaohei.auser.wenliapp.wenlientity.NewRoom;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.wenlientity.Result;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbStudent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

/**
 * Created by Auser on 2018/4/25.
 * 第一次注册界面
 * 功能要求：
 * 1、获取系部、楼栋信息
 * 2、通过上层的选择获取对应的班级、宿舍信息
 */

public class StudentRegisterActivity extends SuperActivity implements View.OnClickListener {

    @BindView(R.id.sp_dep)
    Spinner sp_dep;
    @BindView(R.id.sp_classes)
    Spinner sp_classes;
    @BindView(R.id.sp_build)
    Spinner sp_build;
    @BindView(R.id.sp_room)
    Spinner sp_room;

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;
    @BindView(R.id.img_send)
    ImageView img_send;
    private Dialog mdialog;

    @BindView(R.id.ll_layout)
    LinearLayout layout;

    private List<NewDepartment> weeklist;
    private List<NewBuild> builds;
    private List<NewRoom> rooms;
    private List<NewClasses> classes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
        getDep(WenLiURL.QUERYALL_DEPARTMENT);
        getBuild(WenLiURL.QUERYALL_BUILD);
    }

    //初始化组件信息  ok
    private void init() {
        mdialog = XhDialog.showLoadingDialog(StudentRegisterActivity.this,"正在加载...");
        ly_return.setOnClickListener(this);
        img_send.setOnClickListener(this);
        sp_build.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    XhSnackBar.showResult(layout,"请选择楼栋");
                }else{
                    getRooms(builds.get(position-1).getId());

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
                    XhSnackBar.showResult(layout,"请选择班级");
                }else{
                    getClasses(weeklist.get(position-1).getId());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //得到系部所有信息  ok
    private void getDep(String url) {
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NewDepartment>>>() {}.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 200){
                    weeklist = (List<NewDepartment>) result.getResult();
                    final List<String> strs = new ArrayList<>();
                    strs.add("请选择系部");
                    for (int i=0;i<weeklist.size();i++){
                        strs.add(weeklist.get(i).getTitle());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(StudentRegisterActivity.this,
                                    android.R.layout.simple_list_item_1, strs);
                            sp_dep.setAdapter(arrayAdapter);
                        }
                    });
                }else if(code == 888){
                    XhDialog.DialogNoCancleWithListener(StudentRegisterActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            exit();
                            XhSp.setSharedPreferences(StudentRegisterActivity.this, SpConstants.TOKEN,"");
                            IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
                        }
                    });
                }else if(code == 500){
                    XhSnackBar.showResultWithAction(layout, "服务器跑丢了", "点我追回", new XhSnackBar.Action() {
                        @Override
                        public void actionMethod() {
                            getDep(WenLiURL.QUERYALL_DEPARTMENT);
                            getBuild(WenLiURL.QUERYALL_BUILD);
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
                        getDep(WenLiURL.QUERYALL_DEPARTMENT);
                        getBuild(WenLiURL.QUERYALL_BUILD);
                    }
                });
            }
        });
    }

    //得到所有宿舍楼信息  ok
    private void getBuild(String url) {
        mdialog.show();
        XhLogUtil.d("BUILD_CODE");
        Type mtype = new TypeToken<Result<List<NewBuild>>>() {}.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                XhLogUtil.d("BUILD_CODE"+code);
               if(code == 200){
                    XhLogUtil.d("BUILD_CODE");
                    builds = (List<NewBuild>) result.getResult();
                    final List<String> strs = new ArrayList<>();
                    strs.add("请选择楼栋:");
                    for (int i=0;i<builds.size();i++){
                        strs.add(builds.get(i).getName());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(StudentRegisterActivity.this,
                                    android.R.layout.simple_list_item_1, strs);
                            sp_build.setAdapter(arrayAdapter);
                        }
                    });
                }else if(code == 888){
                   XhDialog.DialogNoCancleWithListener(StudentRegisterActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           exit();
                           XhSp.setSharedPreferences(StudentRegisterActivity.this, SpConstants.TOKEN,"");
                           IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
                       }
                   });
               }else if(code == 500){
                   XhSnackBar.showResultWithAction(layout, "服务器跑丢了", "点我追回", new XhSnackBar.Action() {
                       @Override
                       public void actionMethod() {
                           getDep(WenLiURL.QUERYALL_DEPARTMENT);
                           getBuild(WenLiURL.QUERYALL_BUILD);
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
                        getDep(WenLiURL.QUERYALL_DEPARTMENT);
                        getBuild(WenLiURL.QUERYALL_BUILD);
                    }
                });
            }
        });
    }

    //得到对应系部所有班级信息  ok
    private void getClasses(String departmentId) {
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NewClasses>>>() {}.getType();
        XhOkHttps.GetRequest(WenLiURL.QUERYALL_CLASS+departmentId, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 500){
                    XhSnackBar.showResult(layout,"请求数据失败!");
                }
                else if(code == 200){
                    classes = (List<NewClasses>) result.getResult();
                    final List<String> strs = new ArrayList<>();
                    strs.add("请选择班级:");
                    for (int i=0;i<classes.size();i++){
                        strs.add(classes.get(i).getName());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(StudentRegisterActivity.this,
                                    android.R.layout.simple_list_item_1, strs);
                            sp_classes.setAdapter(arrayAdapter);
                        }
                    });
                }else if(code == 888){
                    XhDialog.DialogNoCancleWithListener(StudentRegisterActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            exit();
                            XhSp.setSharedPreferences(StudentRegisterActivity.this, SpConstants.TOKEN,"");
                            IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
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

    //得到对应宿舍楼所有寝室号信息  ok
    private void getRooms(String buildId) {
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NewRoom>>>() {}.getType();
        XhOkHttps.GetRequest(WenLiURL.QUERYALL_ROOM+buildId, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                if(code == 500){
                }
                else if(code == 200){
                    rooms = (List<NewRoom>) result.getResult();
                    final List<String> strs = new ArrayList<>();
                    strs.add("请选择寝室号:");
                    for (int i=0;i<rooms.size();i++){
                        strs.add(rooms.get(i).getName());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(StudentRegisterActivity.this,
                                    android.R.layout.simple_list_item_1, strs);
                            sp_room.setAdapter(arrayAdapter);
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

    //组件的点击事件
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ly_return) {
            XhSp.setSharedPreferences(StudentRegisterActivity.this, SpConstants.TOKEN,"");
            IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
        }
        else if(v.getId() == R.id.img_send){
            String mes = "你选择的是:\n" +
                    weeklist.get(sp_dep.getSelectedItemPosition()-1).getTitle() +
                    " "+classes.get(sp_classes.getSelectedItemPosition()-1).getName() + "\n"+
                    builds.get(sp_build.getSelectedItemPosition()-1).getName() +
                    " "+rooms.get(sp_room.getSelectedItemPosition()-1).getName()+"\n"+
                    "是否确认提交？";
            XhDialog.DialogWithListener(StudentRegisterActivity.this,"提示", mes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendInfo(WenLiURL.REGISTER_URL);
                }
            });
        }
    }

    //将系部楼栋寝室号与导师挂钩
    private void BindTeahcer(String url) {
        FormBody requestBody = new FormBody.Builder()
                .add(WenLiURL.REGISTER_ID, rooms.get(sp_room.getSelectedItemPosition()-1).getId()+"")
                .add(WenLiURL.REGISTER_STUDENT_ID, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.STUDENT_ID)+"")
                .add(WenLiURL.REGISTER_CLASSID,classes.get(sp_classes.getSelectedItemPosition()-1).getId()+"")
                .add(WenLiURL.REGISTER_DEP_ID,weeklist.get(sp_dep.getSelectedItemPosition()-1).getId()+"")
                .add(WenLiURL.REGISTER_TEACHER_ID,classes.get(sp_classes.getSelectedItemPosition()-1).getTeacherId()+"")
                .add("buildId",builds.get(sp_build.getSelectedItemPosition()-1).getId()+"")
                .build();
        Type mtype = new TypeToken<Result<String>>() {}.getType();
        XhOkHttps.PutRequest(url, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.TOKEN),mtype,requestBody, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                XhLogUtil.d("绑定数据返回码"+code);
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                XhLogUtil.d("绑定异常！！");
            }
        });
    }

    //发送完成之后的信息请求  ok
    private void sendInfo(String url) {
        mdialog.show();
        FormBody requestBody = new FormBody.Builder()
                .add(WenLiURL.REGISTER_ID, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.STUDENT_ID)+"")
                .add(WenLiURL.REGISTER_ROOMID,rooms.get(sp_room.getSelectedItemPosition()-1).getId()+"")
                .add(WenLiURL.REGISTER_CLASSID,classes.get(sp_classes.getSelectedItemPosition()-1).getId()+"")
                .add(WenLiURL.REGISTER_DEP_ID,weeklist.get(sp_dep.getSelectedItemPosition()-1).getId()+"")
                .add("buildId",builds.get(sp_build.getSelectedItemPosition()-1).getId()+"")
                .build();
        Type mtype = new TypeToken<Result<String>>() {}.getType();
        XhOkHttps.PutRequest(url, XhSp.getSharedPreferencesForString(StudentRegisterActivity.this, SpConstants.TOKEN),mtype,requestBody, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
                XhLogUtil.d(code+"");
               if(code == 500){
                    XhSnackBar.showResult(layout,"服务器错误!");
                }
                else if(code == 200){
                    DbStudent student = StudentDao.getStudent();
                    student.setDepartmentId(weeklist.get(sp_dep.getSelectedItemPosition()-1).getId());
                    student.setDepartmentName(weeklist.get(sp_dep.getSelectedItemPosition()-1).getTitle());
                    student.setClassesId(classes.get(sp_classes.getSelectedItemPosition()-1).getId());
                    student.setClassesName(classes.get(sp_classes.getSelectedItemPosition()-1).getName());
                    student.setBuildName(builds.get(sp_build.getSelectedItemPosition()-1).getName());
                    student.setRoomId(rooms.get(sp_room.getSelectedItemPosition()-1).getId());
                    student.setRoomName(rooms.get(sp_room.getSelectedItemPosition()-1).getName());
                    StudentDao.saveStudent(student);
                    BindTeahcer(WenLiURL.BIND_INFOR);
                    IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this,StudentMainActivity.class);
                    XhLogUtil.d(student.toString());

                }else if(code == 888){
                   XhDialog.DialogNoCancleWithListener(StudentRegisterActivity.this, "提示", "账号密码已过期，请重新登录！", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           exit();
                           XhSp.setSharedPreferences(StudentRegisterActivity.this, SpConstants.TOKEN,"");
                           IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
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
    public void onBackPressed() {
        XhSp.setSharedPreferences(StudentRegisterActivity.this, SpConstants.TOKEN,"");
        IntentUtil.startActivtyWithFinish(StudentRegisterActivity.this, LoginActivity.class);
    }
}
