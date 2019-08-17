package com.xiaohei.auser.wenliapp.normalActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.xiaohei.auser.wenliapp.Dao.StudentDao;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.dialog.XhDialog;
import com.xiaohei.auser.wenliapp.minterface.XhHttpInterface;
import com.xiaohei.auser.wenliapp.net.WenLiURL;
import com.xiaohei.auser.wenliapp.net.XhOkHttps;
import com.xiaohei.auser.wenliapp.sp.SpConstants;
import com.xiaohei.auser.wenliapp.sp.XhSp;
import com.xiaohei.auser.wenliapp.utils.StatusBarFullTransparentTools;
import com.xiaohei.auser.wenliapp.wenlientity.Result;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Auser on 2018/4/23.
 * 用户意见反馈界面
 */

public class IdealActivity extends Activity implements TextWatcher {

    @BindView(R.id.img_return)
    ImageView img_return;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.rl_layout)
    RelativeLayout layout;

    @BindView(R.id.ideal_content)
    EditText content;
    @BindView(R.id.ideal_sumbit)
    Button button;
    @BindView(R.id.maxnumber)
    TextView textView_maxnumber;

    private Dialog mdialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myideal);

        /**
         * 增加沉浸式
         */
        StatusBarFullTransparentTools.addAll(true,this);

        ButterKnife.bind(this);
        mdialog = XhDialog.showLoadingDialog(IdealActivity.this,"正在发送...");
        content.addTextChangedListener(this);
    }

    @OnClick(R.id.ideal_sumbit)
    public void sendIdeal() {
        mdialog.show();
        String mcontent = content.getText().toString();
        RequestBody requestBody = new FormBody.Builder()
                .add("cardId", StudentDao.getStudent().getCardId())
                .add("content",mcontent)
                .build();
        Type mtype = new TypeToken<Result<String>>() {
        }.getType();

        XhOkHttps.PostRequestWithToken(WenLiURL.SEND_IDEA, requestBody, XhSp.getSharedPreferencesForString(IdealActivity.this, SpConstants.TOKEN), mtype, new XhHttpInterface() {
            @Override
            public void complied(Result result) {
                int code = result.getCode();
               if(code == 500){
                    showResult("发送失败!");
                }
                else if(code == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            XhDialog.DialogNoCancleWithListener(IdealActivity.this,"提示", "发送成功！", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                        }
                        });
                    }
            }

            @Override
            public void fail() {
                mdialog.dismiss();
                showResult("服务器连接异常!");
            }
        });
    }

    private void showResult(String mes) {
        Snackbar.make(layout, mes, Snackbar.LENGTH_SHORT)
                .setDuration(Snackbar.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_return,R.id.tv_return})
    public void ReturnOnclick(View view){
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int index = content.getText().toString().length();
        textView_maxnumber.setText(index +"/120");
    }
}
