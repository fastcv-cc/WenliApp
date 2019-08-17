package com.xiaohei.auser.wenliapp.teacherActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.Dao.TeacherDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbTeacher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Auser on 2018/4/23.
 * 用于展示教师的信息
 */

public class TeacherInfoActivity extends SuperActivity {

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

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        DbTeacher teacher = TeacherDao.getTeacher();
        studentName.setText(teacher.getNickName());
        studentId.setText(teacher.getUsername());
        studentDepartment.setText(teacher.getDepartmentName());

    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
    }

}
