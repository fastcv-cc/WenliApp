package com.xiaohei.auser.wenliapp.fragment;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.NoReadWeekAdapter;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.NetEvent;
import com.xiaohei.auser.wenliapp.entity.WeekEvent;
import com.xiaohei.auser.wenliapp.entity.vo.WeeksTextVo;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.net.TeacherUrl;
import com.xiaohei.auser.wenliapp.sp.TeacherSpUtils;
import com.xiaohei.auser.wenliapp.teacherActivity.ReadWeekActivity;
import com.xiaohei.auser.wenliapp.teacherActivity.ShowWeekActivity;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Auser on 2018/4/16.
 * 同于展示未读信息
 */

public class DiaryReadFragment extends Fragment implements Animation.AnimationListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private Animation animation;
    private FloatingActionButton fab;
    private Dialog mdialog;
    private View view;
    private ListView listView;
    private List<WeeksTextVo> weeksTextVos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diaryread, container, false);
        EventBus.getDefault().register(this);
        init();
        getNoReadWeek(TeacherUrl.readWeekNoRead());
        return view;
    }

    public void getNoReadWeek(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder()
                .add("teacherId", TeacherSpUtils.getTeacherId(getActivity())+"").build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mdialog.dismiss();
                Log.d("test", "onFailure: 服务器连接异常!");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mdialog.dismiss();
                weeksTextVos.clear();
                String str= null;
                try {
                    str = response.body().string();
                    Gson gson = new Gson();
                    Log.d("test", "onResponse: ");
                    Type mtype = new TypeToken<JsonReturnData<List<WeeksTextVo>>>() {
                    }.getType();
                    Log.d("test", "onResponse: ");
                    JsonReturnData jsonReturnData = gson.fromJson(str, mtype);
                    Log.d("test", "onResponse: ");
                    int code = jsonReturnData.getCode();
                    if(code == 300){
                        Log.d("test", "onResponse: 300 ");
                    }else if(code == 301){
                        Log.d("test", "onResponse: 301 ");
                    }else if(code == 200){
                        weeksTextVos = (List<WeeksTextVo>) jsonReturnData.getData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("test", "onResponse: 服务器数据解析异常 ");
                } finally {
                    Log.d("Login_Student_result",str);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (animation != null) {
                                fab.clearAnimation();
                            }
                            listView.setAdapter(new NoReadWeekAdapter(getActivity(),weeksTextVos));
                        }
                    });

                }
            }
        });
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(getActivity(),"正在加载...");
        fab = view.findViewById(R.id.fab);
        listView = view.findViewById(R.id.list_noread);
        View v_empty = view.findViewById(R.id.la_empty);
        listView.setEmptyView(v_empty);
        listView.setAdapter(new NoReadWeekAdapter(getActivity(),weeksTextVos));
        listView.setOnItemClickListener(this);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        animation.setAnimationListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {}

    @Override
    public void onAnimationEnd(Animation animation) {}

    @Override
    public void onAnimationRepeat(Animation animation) {}

    @Override
    public void onClick(View v) {
        if (animation != null) {
            fab.startAnimation(animation);
            getNoReadWeek(TeacherUrl.readWeekNoRead());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(WeekEvent event) {
        if(event.isRead()){
            getNoReadWeek(TeacherUrl.readWeekNoRead());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("flag", 2);
        bundle.putSerializable("week", weeksTextVos.get(position));
        IntentUtil.MystartActivity(getActivity(),ShowWeekActivity.class, bundle,3);
    }
}
