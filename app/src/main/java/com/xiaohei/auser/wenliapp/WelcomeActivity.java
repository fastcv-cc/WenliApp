package com.xiaohei.auser.wenliapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.basedata.MyLog;
import com.xiaohei.auser.wenliapp.db.DbOpenHelper;
import com.xiaohei.auser.wenliapp.dialog.ExistDialog;
import com.xiaohei.auser.wenliapp.dialog.UpdateDialog;
import com.xiaohei.auser.wenliapp.entity.Version;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.net.GetClient;
import com.xiaohei.auser.wenliapp.net.NormalUrl;
import com.xiaohei.auser.wenliapp.sp.SetSpUtils;
import com.xiaohei.auser.wenliapp.utils.FileUtil;
import com.xiaohei.auser.wenliapp.utils.VersionUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class WelcomeActivity extends AppCompatActivity {

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    goHome();
                    break;
                case 1:
                    FileUtil.installApk(WelcomeActivity.this);
                    break;
                case 2:
                    showDownload();
                    break;
                case 3:
                {
                    show.setText("正在下载...");
                    progressBar.setProgress(mprogress);
                }
                break;
            }
        }
    };
    private Version version;

    private void showDownload() {
        UpdateDialog.showUpDate(WelcomeActivity.this,version, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                File file = new File(FileUtil.getFilePath(WelcomeActivity.this),"download.apk");
                if (file.exists())
                {
                    handler.sendEmptyMessage(1);
                }else{
                    UpdateDialog.showLoad(WelcomeActivity.this,v);
                    useOkhttpDownFile(version.getUrl());
                }
            }
        });
    }

    private void goHome() {
        IntentUtil.startActivtyWithFinish(WelcomeActivity.this, LoginActivity.class);
    }

    private ProgressBar progressBar;
    private TextView show;
    private View v;

    @BindView(R.id.welcome_tv_version)
    TextView tv_version;

    private int count = 0;
    private int mprogress;

    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        if(FileUtil.verifyStoragePermissions(WelcomeActivity.this)){
            useOkHttpGetVersion(NormalUrl.getVersionUrl());
        }
        CreateDB();
        init();
    }

    private void useOkHttpGetVersion(@NonNull String url){
        startTime = System.currentTimeMillis();
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder().url(url);
        builder.method("GET",null);
        okhttp3.Request request = builder.build();
        MyLog.Log(WelcomeActivity.this,"get version","send request");
        Call call = GetClient.GetOkHttpClient(WelcomeActivity.this).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                JudgeGoHome();
            }
            @Override
            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
                        String str= null;
                        try {
                            str = response.body().string();
                            /**
                             * 解析Json
                             * 确定版本
                             * 判断更新
                             */
                            if(str == null || TextUtils.isEmpty(str)){
                                JudgeGoHome();
                            }else {
                                Gson gson = new Gson();
                                Type type = new TypeToken<Version>() {
                                }.getType();
                                version = gson.fromJson(str, type);
                                Log.d("version", "onResponse: "+version.getVersion());
                                if (!version.getVersion().equals(VersionUtil.getVerName(WelcomeActivity.this))) {
                                    handler.sendEmptyMessage(2);
                                } else {
                                    JudgeGoHome();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            MyLog.Log(WelcomeActivity.this,"get version",str);
                        }
            }
        });
    }

    private void JudgeGoHome(){
        if(System.currentTimeMillis() - startTime <= 3000){
            handler.sendEmptyMessageDelayed(0,3000-(System.currentTimeMillis() - startTime));
        }else{
            handler.sendEmptyMessage(0);
        }
    }

    private void useOkhttpDownFile(@NonNull String url){
        Request request = new Request.Builder().url(url).build();
        GetClient.GetOkHttpClient(WelcomeActivity.this).newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(2);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream inputStream = response.body().byteStream();
                int length = (int) response.body().contentLength();
                FileOutputStream fileOutputStream = null;
                String filepath ="";
                try{
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    }else{
                        filepath = getFilesDir().getAbsolutePath();
                    }
                    File file = new File(filepath,"download.apk");
                    if(null != file){
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        do
                        {
                            int numread = inputStream.read(buffer);
                            count += numread;
                            // 计算进度条位置
                            mprogress = (int) (((float) count / length) * 100);
                            // 更新进度
                            handler.sendEmptyMessage(3);
                            if (numread <= 0)
                            {
                                // 下载完成
                                show.setText("下载完成，等待安装");
                                progressBar.setProgress(100);
                                handler.sendEmptyMessage(1);
                                break;
                            }
                            // 写入文件
                            fileOutputStream.write(buffer, 0, numread);
                        } while (true);// 点击取消就停止下载.
                        fileOutputStream.flush();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    MyLog.Log(WelcomeActivity.this,"download","loading");
                }
            }
        });
    }

    private void init() {
        SetSpUtils.setSharedPreferences(WelcomeActivity.this,"install",false);
        LayoutInflater layout= LayoutInflater.from(WelcomeActivity.this);
        v = layout.inflate(R.layout.download_progress_view, null);
        progressBar= v.findViewById(R.id.progress);
        progressBar.setMax(100);
        show= v.findViewById(R.id.showprogress);
        tv_version.setText("v "+VersionUtil.getVerName(WelcomeActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            FileUtil.delDownLaodFile(WelcomeActivity.this);
            finish();
        }
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    useOkHttpGetVersion(NormalUrl.getVersionUrl());
                } else {
                    // 没有获取到权限，做特殊处理
                    ExistDialog.showUpDate(WelcomeActivity.this, "请手动开启权限或者重新进入开启权限！",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                }
                break;
            default:
                break;
        }
    }

    //创建本地数据库
    private void CreateDB(){
        DbOpenHelper db=new DbOpenHelper(WelcomeActivity.this);
        db.close();
    }

}
