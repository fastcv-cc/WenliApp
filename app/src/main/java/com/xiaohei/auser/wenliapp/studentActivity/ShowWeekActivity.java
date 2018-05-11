package com.xiaohei.auser.wenliapp.studentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.entity.WeeksText;
import com.xiaohei.auser.wenliapp.utils.Trans;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Auser on 2018/4/23.
 * 用于学生展示周记界面
 */

public class ShowWeekActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;

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

    private WeeksText week;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_showweek);
        ButterKnife.bind(this);
        week = (WeeksText) getIntent().getExtras().getSerializable("week");
        initview();
    }

    private void initview() {
        week_time.setText(week.getCreateTime());
        week_studycondition.setText(Trans.getStudy(week.getStudy()));
        week_heathcondition.setText(Trans.getHeath(week.getHealth()));
        week_returncondition.setText(Trans.getReturn(week.getReturnHome()));
        week_sleepcondition.setText(Trans.getSleep(week.getSleepCondition()));
        week_moodcondition.setText(Trans.getMood(week.getMood()));
        week_consumecondition.setText(Trans.getConsume(week.getConsume()));
        week_lovelosecondition.setText(Trans.getLoveLose(week.getLoveLose()));
        week_conditiontext.setText(week.getConditionText());
        week_text.setText(week.getTeachersReturnText());
        img_return.setOnClickListener(this);
        tv_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
