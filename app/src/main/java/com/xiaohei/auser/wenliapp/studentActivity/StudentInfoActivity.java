package com.xiaohei.auser.wenliapp.studentActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.Dao.StudentDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.wenlientity.dbentity.DbStudent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/16.
 * 用于学生信息的展示
 */

public class StudentInfoActivity extends SuperActivity implements View.OnClickListener {

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;
    @BindView(R.id.tv_stuinfo_name)
    TextView studentName;
    @BindView(R.id.tv_stuinfo_id)
    TextView studentIdcard;
    @BindView(R.id.tv_stuinfo_department)
    TextView studentDepartment;
    @BindView(R.id.tv_stuinfo_classes)
    TextView studentClasses;
    @BindView(R.id.tv_stuinfo_build)
    TextView studentBuild;
    @BindView(R.id.tv_stuinfo_room)
    TextView studentRoom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        DbStudent student = StudentDao.getStudent();
        studentName.setText(student.getName());
        studentIdcard.setText(student.getCardId());
        studentDepartment.setText(student.getDepartmentName());
        studentClasses.setText(student.getClassesName());
        studentBuild.setText(student.getBuildName());
        studentRoom.setText(student.getRoomName());
        ly_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
