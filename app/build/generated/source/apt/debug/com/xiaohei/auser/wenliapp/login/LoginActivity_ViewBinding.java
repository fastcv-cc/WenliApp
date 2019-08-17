// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.login;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputLayout;
import com.xiaohei.auser.wenliapp.R;
import com.xiaohei.auser.wenliapp.widget.ClearEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.bt_login = Utils.findRequiredViewAsType(source, R.id.bt_login, "field 'bt_login'", Button.class);
    target.layout = Utils.findRequiredViewAsType(source, R.id.activity_main, "field 'layout'", RelativeLayout.class);
    target.tl_username = Utils.findRequiredViewAsType(source, R.id.tl_username, "field 'tl_username'", TextInputLayout.class);
    target.tl_password = Utils.findRequiredViewAsType(source, R.id.tl_password, "field 'tl_password'", TextInputLayout.class);
    target.et_username = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'et_username'", ClearEditText.class);
    target.et_password = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'et_password'", ClearEditText.class);
    target.type_stu = Utils.findRequiredViewAsType(source, R.id.type_stu, "field 'type_stu'", RadioButton.class);
    target.type_tea = Utils.findRequiredViewAsType(source, R.id.type_tea, "field 'type_tea'", RadioButton.class);
    target.type_select = Utils.findRequiredViewAsType(source, R.id.type_select, "field 'type_select'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bt_login = null;
    target.layout = null;
    target.tl_username = null;
    target.tl_password = null;
    target.et_username = null;
    target.et_password = null;
    target.type_stu = null;
    target.type_tea = null;
    target.type_select = null;
  }
}
