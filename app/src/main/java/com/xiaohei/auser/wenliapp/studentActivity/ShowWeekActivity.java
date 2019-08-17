package com.xiaohei.auser.wenliapp.studentActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.SuperActivity;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.utils.Trans;
import com.xiaohei.auser.wenliapp.wenlientity.NewWeektext;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/23.
 * 用于学生展示周记界面
 */

public class ShowWeekActivity extends SuperActivity implements View.OnClickListener {

    @BindView(R.id.ly_return)
    RelativeLayout ly_return;

    @BindView(R.id.week_time)
    TextView week_time;
    @BindView(R.id.week_studycondition)
    TextView week_studycondition;
    @BindView(R.id.week_heathcondition)
    TextView week_heathcondition;
    @BindView(R.id.week_returncondition)
    TextView week_returncondition;
    @BindView(R.id.week_sleepcondition)
    TextView week_sleepcondition;
    @BindView(R.id.week_moodcondition)
    TextView week_moodcondition;
    @BindView(R.id.week_consumecondition)
    TextView week_consumecondition;
    @BindView(R.id.week_lovelosecondition)
    TextView week_lovelosecondition;
    @BindView(R.id.week_conditiontext)
    TextView week_conditiontext;
    @BindView(R.id.week_text)
    EditText week_text;

    private NewWeektext week;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_showweek);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        week = (NewWeektext) getIntent().getExtras().getSerializable("week");
        initview();
    }

    private void initview() {
        week_time.setText(week.getCreateTime());
        week_studycondition.setText(Trans.getStatusName(week.getStudyStatus()));
        week_heathcondition.setText(Trans.getStatusName(week.getHealthStatus()));
        week_returncondition.setText(Trans.getStatusName(week.getReturnRoomStatus()));
        week_sleepcondition.setText(Trans.getStatusName(week.getSleepStatus()));
        week_moodcondition.setText(Trans.getStatusName(week.getMoodStatus()));
        week_consumecondition.setText(Trans.getStatusName(week.getConsumeStatus()));
        week_lovelosecondition.setText(Trans.getStatusName(week.getLoveStatus()));
        week_conditiontext.setText(week.getConditionText());
        week_text.setText(week.getTeacherReturnText());
        ly_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
