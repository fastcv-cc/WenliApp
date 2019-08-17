// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.studentActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudentRegisterActivity_ViewBinding implements Unbinder {
  private StudentRegisterActivity target;

  @UiThread
  public StudentRegisterActivity_ViewBinding(StudentRegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudentRegisterActivity_ViewBinding(StudentRegisterActivity target, View source) {
    this.target = target;

    target.sp_dep = Utils.findRequiredViewAsType(source, R.id.sp_dep, "field 'sp_dep'", Spinner.class);
    target.sp_classes = Utils.findRequiredViewAsType(source, R.id.sp_classes, "field 'sp_classes'", Spinner.class);
    target.sp_build = Utils.findRequiredViewAsType(source, R.id.sp_build, "field 'sp_build'", Spinner.class);
    target.sp_room = Utils.findRequiredViewAsType(source, R.id.sp_room, "field 'sp_room'", Spinner.class);
    target.ly_return = Utils.findRequiredViewAsType(source, R.id.ly_return, "field 'ly_return'", RelativeLayout.class);
    target.img_send = Utils.findRequiredViewAsType(source, R.id.img_send, "field 'img_send'", ImageView.class);
    target.layout = Utils.findRequiredViewAsType(source, R.id.ll_layout, "field 'layout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudentRegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_dep = null;
    target.sp_classes = null;
    target.sp_build = null;
    target.sp_room = null;
    target.ly_return = null;
    target.img_send = null;
    target.layout = null;
  }
}
