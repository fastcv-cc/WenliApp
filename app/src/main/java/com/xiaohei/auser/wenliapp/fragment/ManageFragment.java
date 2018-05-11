package com.xiaohei.auser.wenliapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.ClassExpandableListViewAdapter;
import com.xiaohei.auser.wenliapp.dialog.ShowDialog;
import com.xiaohei.auser.wenliapp.entity.vo.ClassRooms;
import com.xiaohei.auser.wenliapp.intent.IntentUtil;
import com.xiaohei.auser.wenliapp.net.TeacherUrl;
import com.xiaohei.auser.wenliapp.sp.TeacherSpUtils;
import com.xiaohei.auser.wenliapp.teacherActivity.QueryClassRoomInfoActivity;
import com.xiaohei.auser.wenliapp.utils.JsonReturnData;

import java.io.IOException;
import java.lang.reflect.Type;
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
 */

public class ManageFragment extends Fragment {

    private ExpandableListView expandableListView; // expandablelistview控件
    private boolean isOpen = false;
    private Dialog mdialog;
    private View view;
    private List<ClassRooms> list_room;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage, container, false);
        init();
        getDepRoom(TeacherUrl.getDepRoom());
        return view;
    }

    private void init() {
        mdialog = ShowDialog.showLoadingDialog(getActivity(),"正在加载...");
        expandableListView = (ExpandableListView) view
                .findViewById(R.id.ablelist);// 找该控件
        expandableListView
                .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(
                            ExpandableListView expandableListView, View view,
                            int i, long l) {
                        if (!isOpen) {
                            isOpen = true;
                            return false;
                        } else {
                            isOpen = false;
                            return false;
                        }
                    }
                });
        expandableListView
                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(
                            ExpandableListView expandableListView, View view,
                            int i, int i1, long l) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("roomid",list_room.get(i).getRoomsVoList().get(i1).getRoomId());
                        IntentUtil.MystartActivity(getActivity(), QueryClassRoomInfoActivity.class,bundle);
                        return false;
                    }
                });
    }

    private void getDepRoom(String url) {
        mdialog.show();
        RequestBody requestBody = new FormBody.Builder()
                .add("teacherId",TeacherSpUtils.getTeacherId(getActivity())+"").build();
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
                String str= null;
                try {
                    str = response.body().string();
                    Gson gson = new Gson();
                    Log.d("test", "onResponse: ");
                    Type mtype = new TypeToken<JsonReturnData<List<ClassRooms>>>() {
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
                        Log.d("test", "onResponse: 200 ");
                        list_room = (List<ClassRooms>) jsonReturnData.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                expandableListView.setAdapter(new ClassExpandableListViewAdapter(getActivity(),list_room));
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("test", "onResponse: 服务器数据解析异常 ");
                } finally {
                    Log.d("Login_Student_result",str);
                }
            }
        });
    }
}
