package com.xiaohei.auser.wenliapp.teacherActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.dao.TeacherDao;
import com.xiaohei.auser.wenliapp.entity.vo.TeachersVo;
import com.xiaohei.auser.wenliapp.sp.TeacherSpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Auser on 2018/4/23.
 * 用于展示教师的信息
 */

public class TeacherInfoActivity extends Activity {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;

    @BindView(R.id.tv_stuinfo_name)
    TextView studentName;
    @BindView(R.id.tv_stuinfo_id)
    TextView studentId;
    @BindView(R.id.tv_stuinfo_department)
    TextView studentDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherinfo);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        TeachersVo teachersVo = TeacherDao.getTeacher(TeacherInfoActivity.this, TeacherSpUtils.getTeacherId(TeacherInfoActivity.this));
        studentName.setText(teachersVo.getTeacherName());
        studentId.setText(teachersVo.getTeacherCardId());
        studentDepartment.setText(teachersVo.getDepartmentName());

    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
    }

}
