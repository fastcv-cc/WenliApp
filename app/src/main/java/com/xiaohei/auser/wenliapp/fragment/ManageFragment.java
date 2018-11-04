package com.xiaohei.auser.wenliapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.TeacherDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.ClassExpandableListViewAdapter;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.teacherActivity.QueryClassRoomInfoActivity;
import com.xiaohei.auser.wenliapp.wenlientity.NewClassRooms;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import java.lang.reflect.Type;
import java.util.List;
/**
 * Created by Auser on 2018/4/16.
 * 教师主界面中的周记管理板块
 */

public class ManageFragment extends Fragment {

    private ExpandableListView expandableListView; // expandablelistview控件
    private boolean isOpen = false;
    private Dialog mdialog;
    private View view;

    private List<NewClassRooms> list_cr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage, container, false);
        init();
        getClassRooms(WenLiURL.TEACHER_GET_CLASS_ROOM+ TeacherDao.getTeacher().getTeacherid());
        return view;
    }

    private void init() {
        mdialog = XhDialog.showLoadingDialog(getActivity(),"正在加载...");
        expandableListView = view
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
                        bundle.putString("roomid",list_cr.get(i).getRoomVoList().get(i1).getId());
                        IntentUtil.MystartActivity(getActivity(), QueryClassRoomInfoActivity.class,bundle);
                        return false;
                    }
                });
    }

    private void getClassRooms(String url) {
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NewClassRooms>>>() {}.getType();
        XhOkHttps.GetRequest(url, XhSp.getSharedPreferencesForString(getActivity(), SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                int code = result.getCode();
              if(code == 200){
                  list_cr = (List<NewClassRooms>) result.getResult();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            expandableListView.setAdapter(new ClassExpandableListViewAdapter(getActivity(),list_cr));
                        }
                    });
                }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
            }
        });
    }
}
