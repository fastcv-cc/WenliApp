// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.studentActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudentChangePasswordActivity_ViewBinding implements Unbinder {
  private StudentChangePasswordActivity target;

  @UiThread
  public StudentChangePasswordActivity_ViewBinding(StudentChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudentChangePasswordActivity_ViewBinding(StudentChangePasswordActivity target,
      View source) {
    this.target = target;

    target.ly_return = Utils.findRequiredViewAsType(source, R.id.ly_return, "field 'ly_return'", RelativeLayout.class);
    target.old_psw = Utils.findRequiredViewAsType(source, R.id.old_psw, "field 'old_psw'", EditText.class);
    target.new_psw = Utils.findRequiredViewAsType(source, R.id.new_psw, "field 'new_psw'", EditText.class);
    target.new_psw2 = Utils.findRequiredViewAsType(source, R.id.new_psw2, "field 'new_psw2'", EditText.class);
    target.bt_sumbit = Utils.findRequiredViewAsType(source, R.id.bt_sumbit, "field 'bt_sumbit'", Button.class);
    target.checkBox = Utils.findRequiredViewAsType(source, R.id.psw_check, "field 'checkBox'", CheckBox.class);
    target.layout = Utils.findRequiredViewAsType(source, R.id.rl_layout, "field 'layout'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudentChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ly_return = null;
    target.old_psw = null;
    target.new_psw = null;
    target.new_psw2 = null;
    target.bt_sumbit = null;
    target.checkBox = null;
    target.layout = null;
  }
}
