// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.teacherActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TeacherInfoActivity_ViewBinding implements Unbinder {
  private TeacherInfoActivity target;

  private View view7f090085;

  private View view7f09011b;

  @UiThread
  public TeacherInfoActivity_ViewBinding(TeacherInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TeacherInfoActivity_ViewBinding(final TeacherInfoActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.img_return, "field 'img_return' and method 'ReturnOnclick'");
    target.img_return = Utils.castView(view, R.id.img_return, "field 'img_return'", ImageView.class);
    view7f090085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ReturnOnclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_return, "field 'tv_return' and method 'ReturnOnclick'");
    target.tv_return = Utils.castView(view, R.id.tv_return, "field 'tv_return'", TextView.class);
    view7f09011b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ReturnOnclick(p0);
      }
    });
    target.studentName = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_name, "field 'studentName'", TextView.class);
    target.studentId = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_id, "field 'studentId'", TextView.class);
    target.studentDepartment = Utils.findRequiredViewAsType(source, R.id.tv_stuinfo_department, "field 'studentDepartment'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TeacherInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_return = null;
    target.tv_return = null;
    target.studentName = null;
    target.studentId = null;
    target.studentDepartment = null;

    view7f090085.setOnClickListener(null);
    view7f090085 = null;
    view7f09011b.setOnClickListener(null);
    view7f09011b = null;
  }
}
