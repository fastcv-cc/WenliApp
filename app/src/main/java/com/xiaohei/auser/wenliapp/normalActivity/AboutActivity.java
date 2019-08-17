package com.xiaohei.auser.wenliapp.normalActivity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Auser on 2018/4/18.
 */

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.photo)
    TextView erweima;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.photo)
    public void showPhoto(View v){
        XhDialog.showErweimaDialog(AboutActivity.this);
    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
    }
}
