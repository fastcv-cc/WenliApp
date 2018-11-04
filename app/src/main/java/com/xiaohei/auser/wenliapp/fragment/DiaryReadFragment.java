package com.xiaohei.auser.wenliapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.TeacherDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.adapter.StudentWeekAdapter;
import com.xiaohei.auser.wenliapp.adapter.TeacherNoReadWeekAdapter;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.wenlientity.NoReadWeekText;
import com.xiaohei.auser.wenliapp.wenlievent.WeekEvent;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.teacherActivity.ShowWeekActivity;
import com.xiaohei.auser.wenliapp.wenlientity.NewWeektext;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Auser on 2018/4/16.
 * 同于展示未读信息
 */

public class DiaryReadFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Animation animation;
    private FloatingActionButton fab;
    private Dialog mdialog;
    private View view;
    private ListView listView;
    private List<NoReadWeekText> weeksTextVos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diaryread, container, false);
        EventBus.getDefault().register(this);
        init();
        getNoReadWeek();

        return view;
    }

    private void init() {
        mdialog = XhDialog.showLoadingDialog(getActivity(),"正在加载...");
        fab = view.findViewById(R.id.fab);
        listView = view.findViewById(R.id.list_noread);
        View v_empty = view.findViewById(R.id.la_empty);
        listView.setEmptyView(v_empty);
        listView.setAdapter(new TeacherNoReadWeekAdapter(getActivity(),weeksTextVos));
        listView.setOnItemClickListener(this);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        fab.setOnClickListener(this);
    }

    public void getNoReadWeek() {
        mdialog.show();
        Type mtype = new TypeToken<Result<List<NoReadWeekText>>>() {}.getType();
        XhOkHttps.GetRequest(WenLiURL.TEACHER_NO_READ_WEEKTEXT+ TeacherDao.getTeacher().getTeacherid(), XhSp.getSharedPreferencesForString(getActivity(), SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                mdialog.dismiss();
                if (fab.getAnimation() != null)
                fab.clearAnimation();
                int code = result.getCode();
                if(code == 200){
                    weeksTextVos = (List<NoReadWeekText>) result.getResult();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(new TeacherNoReadWeekAdapter(getActivity(),weeksTextVos));
                        }
                    });
                }else if(code == 500){

                }
            }

            @Override
            public void fail() {
                if (fab.getAnimation() != null)
                    fab.clearAnimation();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (animation != null) {
            fab.startAnimation(animation);
            getNoReadWeek();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(WeekEvent event) {
        if(event.isRead()){
            getNoReadWeek();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("week", weeksTextVos.get(position));
        IntentUtil.MystartActivity(getActivity(),ShowWeekActivity.class, bundle,3);
    }
}
