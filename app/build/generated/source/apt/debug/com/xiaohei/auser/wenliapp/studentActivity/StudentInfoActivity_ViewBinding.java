// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.studentActivity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudentInfoActivity_ViewBinding implements Unbinder {
  private StudentInfoActivity target;

  @UiThread
  public StudentInfoActivity_ViewBinding(StudentInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudentInfoActivity_ViewBinding(StudentInfoActivity target, View source) {
    this.target = target;

    target.ly_return = Utils.findRequiredViewAsType(source, R.id.ly_return, "field 'ly_return'", RelativeLayout.class);
    target.studentName = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_name, "field 'studentName'", TextView.class);
    target.studentIdcard = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_id, "field 'studentIdcard'", TextView.class);
    target.studentDepartment = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_department, "field 'studentDepartment'", TextView.class);
    target.studentClasses = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_classes, "field 'studentClasses'", TextView.class);
    target.studentBuild = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_build, "field 'studentBuild'", TextView.class);
    target.studentRoom = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_room, "field 'studentRoom'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudentInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ly_return = null;
    target.studentName = null;
    target.studentIdcard = null;
    target.studentDepartment = null;
    target.studentClasses = null;
    target.studentBuild = null;
    target.studentRoom = null;
  }
}
