package com.xiaohei.auser.wenliapp.studentActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.dao.StudentDao;
import com.xiaohei.auser.wenliapp.entity.vo.StudentsVo;
import com.xiaohei.auser.wenliapp.sp.StudentSpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/16.
 * 用于学生信息的展示
 */

public class StudentInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
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
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        StudentsVo complete = StudentDao.getStudent(this,
                StudentSpUtils.getStudentId(this));
        studentName.setText(complete.getStudentName());
        studentIdcard.setText(complete.getStudentCardId());
        studentDepartment.setText(complete.getDepartmentName());
        studentClasses.setText(complete.getClassName());
        studentBuild.setText(complete.getBuildName());
        studentRoom.setText(complete.getRoomName());
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
