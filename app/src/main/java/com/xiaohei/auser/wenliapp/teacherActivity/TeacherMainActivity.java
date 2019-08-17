package com.xiaohei.auser.wenliapp.teacherActivity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.Dao.TeacherDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.adapter.StudentSelectAdapter;
import com.xiaohei.auser.wenliapp.basedata.BaseData;
import com.xiaohei.auser.wenliapp.fragment.DiaryReadFragment;
import com.xiaohei.auser.wenliapp.fragment.ManageFragment;
import com.xiaohei.auser.wenliapp.utils.IntentUtil;
import com.xiaohei.auser.wenliapp.login.LoginActivity;
import com.xiaohei.auser.wenliapp.normalActivity.SettingActivity;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/16.
 * 教师的主界面
 */

public class TeacherMainActivity extends SuperActivity implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.list_select)
    ListView list_select;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.group_tea)
    RadioGroup radioGroup;
    @BindView(R.id.rd_manage)
    RadioButton rd_manage;
    @BindView(R.id.rd_diaryread)
    RadioButton rd_diaryread;
    @BindView(R.id.teacher_name)
    TextView text_name;

    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private String[] mFragmentTagList = {"ManageFragment","DialogFragment"};
    private Fragment mCurrentFragmen = null; // 记录当前显示的Fragment
    private DiaryReadFragment diaryReadFragment;
    private ManageFragment managefragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_mian);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
        initData();
    }

    private void initData() {
        managefragement = new ManageFragment();
        diaryReadFragment = new DiaryReadFragment();
        mFragmentList.add(managefragement);
        mFragmentList.add(diaryReadFragment);
        mCurrentFragmen = mFragmentList.get(0);
        // 初始化首次进入时的Fragment
        mFm = getSupportFragmentManager();
        rd_diaryread.setTextColor(Color.BLACK);
        rd_manage.setTextColor(Color.BLUE);
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.add(R.id.fl_show, mCurrentFragmen, mFragmentTagList[0]);
        transaction.commitAllowingStateLoss();
    }

    private void init() {
        toolbar.setTitle("");
        text_name.setText(TeacherDao.getTeacher().getNickName());
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        list_select.setAdapter(new StudentSelectAdapter(TeacherMainActivity.this, BaseData.getSelectListForTeacher()));
        list_select.setOnItemClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
    }

    // 转换Fragment
    private void switchFragment(Fragment to, String tag){
        if(mCurrentFragmen != to){
            FragmentTransaction transaction = mFm.beginTransaction();
            if(!to.isAdded()){
                // 没有添加过:
                // 隐藏当前的，添加新的，显示新的
                transaction.hide(mCurrentFragmen).add(R.id.fl_show, to, tag).show(to);
            }else{
                // 隐藏当前的，显示新的
                transaction.hide(mCurrentFragmen).show(to);
            }
            setColor(tag);
            mCurrentFragmen = to;
            transaction.commitAllowingStateLoss();
        }
    }

    private void setColor(String tag){
        if(mFragmentTagList[0].equals(tag)){
            rd_diaryread.setTextColor(Color.BLACK);
            rd_manage.setTextColor(Color.BLUE);
        }else{
            rd_diaryread.setTextColor(Color.BLUE);
            rd_manage.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:   //我的信息
                IntentUtil.MystartActivity(TeacherMainActivity.this,TeacherInfoActivity.class);
            break;
            case 1:   //修改密码
                IntentUtil.MystartActivity(TeacherMainActivity.this,TeacherChangePasswordActivity.class);
            break;
            case 2:   //退出登录
            {
                XhSp.setSharedPreferences(TeacherMainActivity.this, SpConstants.TOKEN,"");
                IntentUtil.startActivtyWithFinish(TeacherMainActivity.this, LoginActivity.class);
            }
            break;
            case 3://设置界面
                IntentUtil.MystartActivity(TeacherMainActivity.this, SettingActivity.class);
            break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.rd_manage:{
                switchFragment(managefragement,mFragmentTagList[0]);
            }
            break;
            case R.id.rd_diaryread:{
                switchFragment(diaryReadFragment,mFragmentTagList[1]);
            }
            break;
        }
    }

        @Override
        public void onBackPressed() {
            BackExit();
        }
}